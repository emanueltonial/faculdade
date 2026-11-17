import { View, Text, TextInput } from 'react-native';

export default function Campo({
  label,
  valor,
  funcao,
  placeholder = '',
  senha = false,
  teclado = 'default',
  capitalizar = 'sentences',
  maxLength,
  editavel = true,
  aoSair,
}) {
  return (
    <View
      style={{
        flexDirection: 'row',
        alignItems: 'center',
        marginVertical: 6,
      }}
    >
      <Text
        style={{
          flex: 1,
          textAlign: 'left',
          fontSize: 15,
          color: '#2e3440',
          fontWeight: '600',
        }}
      >
        {label}
      </Text>

      
      <TextInput
        style={{
          flex: 2,
          height: 40,
          borderWidth: 1,
          borderColor: '#d8dee9',
          borderRadius: 8,
          paddingHorizontal: 10,
          backgroundColor: editavel ? '#ffffff' : '#eceff4',
          color: '#2e3440',
        }}
        value={valor}
        onChangeText={funcao}
        placeholder={placeholder}
        secureTextEntry={senha}
        keyboardType={teclado}
        autoCapitalize={capitalizar}
        maxLength={maxLength}
        editable={editavel}
        onBlur={aoSair}
      />
    </View>
  );
}