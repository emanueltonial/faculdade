import { Pressable, Text } from 'react-native';

export default function Botao({ type = 'primary', size = 'medium', text, shadow = 'off', funcao }) {
  const cores = {
    primary: '#5e81ac',
    success: '#a3be8c',
    error: '#bf616a',
    secondary: '#4c566a',
  };

  const tamanhos = {
    small: { width: 100, height: 32 },
    medium: { width: 150, height: 40 },
    large: { width: 200, height: 50 },
  };

  return (
    <Pressable
      style={{
        backgroundColor: cores[type],
        width: tamanhos[size].width,
        height: tamanhos[size].height,

        shadowColor: '#000',
        shadowOffset: { width: 3, height: 3 },
        shadowOpacity: shadow == 'on' ? 0.4 : 0,
        shadowRadius: 4,
        elevation: shadow == 'on' ? 5 : 0,

        borderRadius: 10,
        marginVertical: 10,
        alignSelf: 'center',
        justifyContent: 'center',
        alignItems: 'center',
      }}
      onPress={funcao}
    >
      <Text style={{ color: '#fff', fontWeight: 'bold', textAlign: 'center' }}>{text}</Text>
    </Pressable>
  );
}