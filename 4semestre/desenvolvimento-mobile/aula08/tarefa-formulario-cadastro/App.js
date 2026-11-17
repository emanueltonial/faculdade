import { useState } from 'react';
import { ScrollView, View, Text, StyleSheet } from 'react-native';

import Campo from './components/Campo';
import Botao from './components/Botao';
import Checkbox from './components/Checkbox';

export default function App() {
  // dados pessoais
  const [nome, setNome] = useState('');
  const [sobrenome, setSobrenome] = useState('');
  const [email, setEmail] = useState('');
  const [telefone, setTelefone] = useState('');

  // endereço
  const [cep, setCep] = useState('');
  const [estado, setEstado] = useState('');
  const [cidade, setCidade] = useState('');
  const [bairro, setBairro] = useState('');
  const [rua, setRua] = useState('');
  const [numero, setNumero] = useState('');
  const [complemento, setComplemento] = useState('');

  // acesso
  const [username, setUsername] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');
  const [termos, setTermos] = useState(false);

  // feedback
  const [mensagem, setMensagem] = useState('');
  const [erro, setErro] = useState(false);

  function mostrar(texto, ehErro = true) {
    setMensagem(texto);
    setErro(ehErro);
  }

  // Aula 11/11: busca do CEP na API ViaCEP
  async function buscarCep() {
    const cepLimpo = cep.replace(/\D/g, '');
    if (cepLimpo.length !== 8) {
      mostrar('CEP deve ter 8 dígitos');
      return;
    }

    try {
      const url = `https://viacep.com.br/ws/${cepLimpo}/json`;
      const resposta = await fetch(url);
      const dados = await resposta.json();

      if (dados.erro) {
        mostrar('CEP não encontrado');
        return;
      }

      setEstado(dados.uf);
      setCidade(dados.localidade);
      setBairro(dados.bairro);
      setRua(dados.logradouro);
      mostrar('Endereço preenchido!', false);
    } catch (e) {
      mostrar('Erro ao buscar o CEP');
    }
  }

  function cadastrar() {
    const obrigatorios = [nome, sobrenome, email, cep, estado, cidade, bairro, rua, numero, username, senha];

    if (obrigatorios.some((valor) => valor.trim() === '')) return mostrar('Preencha todos os campos obrigatórios (*)');
    if (!/^\S+@\S+\.\S+$/.test(email)) return mostrar('E-mail inválido');
    if (senha.length < 6) return mostrar('A senha deve ter pelo menos 6 caracteres');
    if (senha !== confirmarSenha) return mostrar('As senhas não conferem');
    if (!termos) return mostrar('Você precisa concordar com os termos');

    console.log({ nome, sobrenome, email, telefone, cep, estado, cidade, bairro, rua, numero, complemento, username });
    mostrar(`Usuário ${username} cadastrado!`, false);
  }

  function limpar() {
    setNome('');
    setSobrenome('');
    setEmail('');
    setTelefone('');
    setCep('');
    setEstado('');
    setCidade('');
    setBairro('');
    setRua('');
    setNumero('');
    setComplemento('');
    setUsername('');
    setSenha('');
    setConfirmarSenha('');
    setTermos(false);
    setMensagem('');
  }

  return (
    <ScrollView style={styles.tela} contentContainerStyle={styles.conteudo}>
      <Text style={styles.titulo}>Cadastro de Usuário</Text>

      <Text style={styles.secao}>Dados pessoais</Text>
      <Campo label="Nome *" valor={nome} funcao={setNome} placeholder="João" />
      <Campo label="Sobrenome *" valor={sobrenome} funcao={setSobrenome} placeholder="Silva" />
      <Campo label="E-mail *" valor={email} funcao={setEmail} placeholder="joao@email.com" teclado="email-address" capitalizar="none" />
      <Campo label="Telefone" valor={telefone} funcao={setTelefone} placeholder="(54) 99999-9999" teclado="phone-pad" />

      <Text style={styles.secao}>Endereço</Text>
      <Campo label="CEP *" valor={cep} funcao={setCep} placeholder="99700-000" teclado="numeric" maxLength={9} aoSair={buscarCep} />
      <Botao type="secondary" size="small" text="Buscar CEP" funcao={buscarCep} />
      <Campo label="Estado *" valor={estado} funcao={setEstado} placeholder="RS" maxLength={2} capitalizar="characters" />
      <Campo label="Cidade *" valor={cidade} funcao={setCidade} placeholder="Erechim" />
      <Campo label="Bairro *" valor={bairro} funcao={setBairro} placeholder="Centro" />
      <Campo label="Rua *" valor={rua} funcao={setRua} placeholder="Av. Sete de Setembro" />
      <Campo label="Número *" valor={numero} funcao={setNumero} placeholder="123" teclado="numeric" />
      <Campo label="Complemento" valor={complemento} funcao={setComplemento} placeholder="Apto 101" />

      <Text style={styles.secao}>Acesso</Text>
      <Campo label="Username *" valor={username} funcao={setUsername} placeholder="joaosilva" capitalizar="none" />
      <Campo label="Senha *" valor={senha} funcao={setSenha} senha capitalizar="none" />
      <Campo label="Confirmar senha *" valor={confirmarSenha} funcao={setConfirmarSenha} senha capitalizar="none" />

      <Checkbox label="Li e concordo com os termos de uso e a políxtica de privacidade" marcado={termos} funcao={setTermos} />

      {mensagem !== '' && (
        <Text style={[styles.mensagem, { color: erro ? '#bf616a' : '#4f7a3a' }]}>{mensagem}</Text>
      )}

      <View style={styles.botoes}>
        <Botao type="error" text="Limpar" funcao={limpar} />
        <Botao type="success" text="Cadastrar" shadow="on" funcao={cadastrar} />
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  tela: {
    flex: 1,
    backgroundColor: '#f5f7fa',
  },
  conteudo: {
    padding: 20,
    paddingTop: 50,
  },
  titulo: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#2e3440',
    marginBottom: 10,
  },
  secao: {
    fontSize: 17,
    fontWeight: 'bold',
    color: '#5e81ac',
    marginTop: 20,
    marginBottom: 6,
    borderBottomWidth: 1,
    borderBottomColor: '#d8dee9',
    paddingBottom: 4,
  },
  mensagem: {
    textAlign: 'center',
    fontWeight: '600',
    marginTop: 10,
  },
  botoes: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
});