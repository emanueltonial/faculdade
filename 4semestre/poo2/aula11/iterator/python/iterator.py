"""
Padrão de projeto Iterator em Python.

Ideia: percorrer os elementos de uma coleção sem expor a estrutura interna dela.
O cliente só conhece a interface do iterador (tem próximo? me dá o próximo).

Arquivo dividido em 3 partes:
  1. Versão clássica (GoF), com interfaces explícitas
  2. Versão pythônica, usando o protocolo __iter__ / __next__
  3. Versão com generator (a forma mais idiomática no dia a dia)
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Iterator as PyIterator
from dataclasses import dataclass
from typing import Generic, TypeVar

T = TypeVar("T")


# =============================================================================
# 1. Versão clássica (GoF)
# =============================================================================

class Iterator(ABC, Generic[T]):
    """Interface do iterador."""

    @abstractmethod
    def has_next(self) -> bool: ...

    @abstractmethod
    def next(self) -> T: ...


class Aggregate(ABC, Generic[T]):
    """Interface da coleção que sabe criar seu próprio iterador."""

    @abstractmethod
    def create_iterator(self) -> Iterator[T]: ...


@dataclass
class Livro:
    titulo: str
    autor: str


class Estante(Aggregate[Livro]):
    """Coleção concreta. A lista interna fica escondida do cliente."""

    def __init__(self) -> None:
        self._livros: list[Livro] = []

    def adicionar(self, livro: Livro) -> None:
        self._livros.append(livro)

    def __len__(self) -> int:
        return len(self._livros)
    
    def get(self, index: int) -> Livro:
        return self._livros[index]

    def create_iterator(self) -> Iterator[Livro]:
        return EstanteIterator(self)

    def create_reverse_iterator(self) -> Iterator[Livro]:
        return EstanteReverseIterator(self)


class EstanteIterator(Iterator[Livro]):
    """Iterador concreto: percorre do início ao fim."""

    def __init__(self, estante: Estante) -> None:
        self._estante = estante
        self._pos = 0

    def has_next(self) -> bool:
        return self._pos < len(self._estante)

    def next(self) -> Livro:
        if not self.has_next():
            raise StopIteration
        livro = self._estante.get(self._pos)
        self._pos += 1
        return livro


class EstanteReverseIterator(Iterator[Livro]):
    """Outro iterador concreto: mesma coleção, outra estratégia de percurso."""

    def __init__(self, estante: Estante) -> None:
        self._estante = estante
        self._pos = len(estante) - 1

    def has_next(self) -> bool:
        return self._pos >= 0

    def next(self) -> Livro:
        if not self.has_next():
            raise StopIteration
        livro = self._estante.get(self._pos)
        self._pos -= 1
        return livro


# =============================================================================
# 2. Versão pythônica: protocolo de iteração (__iter__ / __next__)
# =============================================================================
# Em Python o padrão já vem embutido na linguagem: qualquer objeto com
# __iter__ funciona em for, list(), sum(), zip(), etc.

@dataclass
class No:
    valor: int
    proximo: No | None = None


class ListaEncadeada:
    """Coleção iterável. O cliente não precisa saber que são nós ligados."""

    def __init__(self, *valores: int) -> None:
        self._head: No | None = None
        for v in reversed(valores):
            self._head = No(v, self._head)

    def __iter__(self) -> ListaEncadeadaIterator:
        # Cada chamada devolve um iterador NOVO, então dá pra iterar várias vezes
        return ListaEncadeadaIterator(self._head)


class ListaEncadeadaIterator:
    """Iterador: guarda o estado do percurso (nó atual)."""

    def __init__(self, inicio: No | None) -> None:
        self._atual = inicio

    def __iter__(self) -> ListaEncadeadaIterator:
        return self

    def __next__(self) -> int:
        if self._atual is None:
            raise StopIteration
        valor = self._atual.valor
        self._atual = self._atual.proximo
        return valor


# =============================================================================
# 3. Versão com generator (forma mais enxuta)
# =============================================================================
# O `yield` cria o iterador pra você e guarda o estado automaticamente.

@dataclass
class Arvore:
    valor: int
    esquerda: Arvore | None = None
    direita: Arvore | None = None

    def __iter__(self) -> PyIterator[int]:
        """Percurso em ordem (in-order), padrão."""
        return self.em_ordem()

    def em_ordem(self) -> PyIterator[int]:
        if self.esquerda:
            yield from self.esquerda.em_ordem()
        yield self.valor
        if self.direita:
            yield from self.direita.em_ordem()

    def pre_ordem(self) -> PyIterator[int]:
        yield self.valor
        if self.esquerda:
            yield from self.esquerda.pre_ordem()
        if self.direita:
            yield from self.direita.pre_ordem()

    def em_largura(self) -> PyIterator[int]:
        """BFS: mostra que a estratégia de percurso fica isolada no iterador."""
        fila: list[Arvore] = [self]
        while fila:
            no = fila.pop(0)
            yield no.valor
            fila.extend(f for f in (no.esquerda, no.direita) if f)


def paginar(total: int, tamanho_pagina: int) -> PyIterator[range]:
    """Iterador preguiçoso (lazy): só gera a página quando alguém pede.
    Útil pra API paginada, leitura de arquivo grande, cursor de banco, etc."""
    for inicio in range(0, total, tamanho_pagina):
        yield range(inicio, min(inicio + tamanho_pagina, total))


# =============================================================================
# Demo
# =============================================================================

def main() -> None:
    print("== 1. Clássico (GoF) ==")
    estante = Estante()
    estante.adicionar(Livro("Clean Code", "Robert C. Martin"))
    estante.adicionar(Livro("Designing Data-Intensive Applications", "Martin Kleppmann"))
    estante.adicionar(Livro("The Go Programming Language", "Donovan & Kernighan"))

    it = estante.create_iterator()
    while it.has_next():
        print("  ->", it.next().titulo)

    print("  (reverso)")
    rit = estante.create_reverse_iterator()
    while rit.has_next():
        print("  <-", rit.next().titulo)

    print("\n== 2. Protocolo __iter__/__next__ ==")
    lista = ListaEncadeada(10, 20, 30, 40)
    print("  for:", [v for v in lista])
    print("  sum:", sum(lista))               # funciona com builtins
    print("  zip:", list(zip(lista, lista)))  # iteradores independentes

    print("\n== 3. Generator ==")
    #        4
    #      /   \
    #     2     6
    #    / \   / \
    #   1   3 5   7
    arvore = Arvore(
        4,
        Arvore(2, Arvore(1), Arvore(3)),
        Arvore(6, Arvore(5), Arvore(7)),
    )
    print("  em ordem:  ", list(arvore))
    print("  pré-ordem: ", list(arvore.pre_ordem()))
    print("  em largura:", list(arvore.em_largura()))

    print("  paginação:")
    for pagina in paginar(total=7, tamanho_pagina=3):
        print("   ", list(pagina))


if __name__ == "__main__":
    main()
