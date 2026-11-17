"""
Padrão Observer em Python (versão do material "Observer em Java").

Parte 1: exemplo da aula (Canal + Usuario)
Parte 2: exercício prático (EstacaoMeteorologica + DisplayTemperatura)
Parte 3: extra, desinscrição e observers de tipos diferentes
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from datetime import datetime

# =============================================================================
# Parte 1: exemplo da aula
# =============================================================================

class Observer(ABC):
    """Equivalente à interface Observer.java: o contrato de todo observador."""

    @abstractmethod
    def atualizar(self, mensagem: str) -> None: ...


class Usuario(Observer):
    """Observador concreto (Usuario.java)."""

    def __init__(self, nome: str) -> None:
        self.nome = nome

    def atualizar(self, mensagem: str) -> None:
        print(f"{self.nome} recebeu: {mensagem}")


class Canal:
    """Subject (Canal.java): guarda a lista de observadores e notifica todos.

    A lista é de Observer, não de Usuario: o canal não sabe nem precisa saber
    quem está do outro lado.
    """

    def __init__(self) -> None:
        self._observadores: list[Observer] = []

    def inscrever(self, observador: Observer) -> None:
        self._observadores.append(observador)

    def desinscrever(self, observador: Observer) ->     None:
        # Responde à questão 4 dos slides: como parar de receber notificações
        self._observadores.remove(observador)

    def publicar(self, mensagem: str) -> None:
        print("\nNova mensagem publicada!")
        # Núcleo do padrão: cada observador reage do seu jeito (polimorfismo)
        for observador in self._observadores:
            observador.atualizar(mensagem)


# =============================================================================
# Parte 2: exercício prático, estação meteorológica
# =============================================================================

class ObserverTemperatura(ABC):
    """Observer do exercício: atualizar(double temperatura)."""

    @abstractmethod
    def atualizar(self, temperatura: float) -> None: ...


class DisplayTemperatura(ObserverTemperatura):
    def __init__(self, nome: str) -> None:
        self.nome = nome

    def atualizar(self, temperatura: float) -> None:
        print(f"[{self.nome}] Temperatura atual: {temperatura:.1f} °C")


class EstacaoMeteorologica:
    """Subject do exercício."""

    def __init__(self) -> None:
        self._observadores: list[ObserverTemperatura] = []
        self._temperatura: float | None = None

    def inscrever(self, observador: ObserverTemperatura) -> None:
        self._observadores.append(observador)

    def desinscrever(self, observador: ObserverTemperatura) -> None:
        self._observadores.remove(observador)

    def set_temperatura(self, temperatura: float) -> None:
        print(f"\nEstação: temperatura mudou para {temperatura:.1f} °C")
        self._temperatura = temperatura
        self._notificar()

    def _notificar(self) -> None:
        for observador in self._observadores:
            observador.atualizar(self._temperatura) # type: ignore


# =============================================================================
# Parte 3: extra, outros tipos de observador
# =============================================================================
# Mostra a questão 1 dos slides na prática: novos observers entram sem mexer
# em uma linha de EstacaoMeteorologica.

class AlertaCalor(ObserverTemperatura):
    def __init__(self, limite: float) -> None:
        self.limite = limite

    def atualizar(self, temperatura: float) -> None:
        if temperatura >= self.limite:
            print(f"[Alerta] Calor acima de {self.limite:.0f} °C!")


class LogTemperatura(ObserverTemperatura):
    def __init__(self) -> None:
        self.historico: list[tuple[str, float]] = []

    def atualizar(self, temperatura: float) -> None:
        hora = datetime.now().strftime("%H:%M:%S")
        self.historico.append((hora, temperatura))
        print(f"[Log] {len(self.historico)} leitura(s) registrada(s)")


# =============================================================================
# Cliente (Principal.java)
# =============================================================================

def main() -> None:
    print("=" * 50)
    print("PARTE 1: Canal")
    print("=" * 50)

    canal = Canal()
    u1 = Usuario("Ana")
    u2 = Usuario("Bruno")
    u3 = Usuario("Carlos")

    canal.inscrever(u1)
    canal.inscrever(u2)
    canal.inscrever(u3)

    canal.publicar("Nova aula de Java disponível!")

    canal.desinscrever(u2)  # Bruno sai do canal
    canal.publicar("Nova aula de Python disponível!")

    print("\n" + "=" * 50)
    print("PARTE 2: Exercício, estação meteorológica")
    print("=" * 50)

    estacao = EstacaoMeteorologica()
    display_sala = DisplayTemperatura("Display Sala")
    display_cozinha = DisplayTemperatura("Display Cozinha")

    estacao.inscrever(display_sala)
    estacao.inscrever(display_cozinha)

    estacao.set_temperatura(22.5)
    estacao.set_temperatura(31.0)

    print("\n" + "=" * 50)
    print("PARTE 3: Extra, observers diferentes na mesma estação")
    print("=" * 50)

    estacao.inscrever(AlertaCalor(limite=30))
    estacao.inscrever(LogTemperatura())
    estacao.desinscrever(display_cozinha)

    estacao.set_temperatura(28.0)
    estacao.set_temperatura(35.2)


if __name__ == "__main__":
    main()
