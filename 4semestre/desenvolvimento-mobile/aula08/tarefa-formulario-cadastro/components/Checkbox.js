import { Pressable, View, Text } from 'react-native';

export default function Checkbox({ label, marcado = false, funcao, cor = '#5e81ac' }) {
  return (
    <Pressable
      style={{ flexDirection: 'row', alignItems: 'center', marginVertical: 10 }}
      onPress={() => funcao(!marcado)}
    >
      <View
        style={{
          width: 22,
          height: 22,
          borderWidth: 2,
          borderColor: cor,
          borderRadius: 4,
          backgroundColor: marcado ? cor : 'transparent',
          justifyContent: 'center',
          alignItems: 'center',
          marginRight: 10,
        }}
      >
        {marcado && <Text style={{ color: '#fff', fontWeight: 'bold' }}>✓</Text>}
      </View>
      <Text style={{ flex: 1, color: '#2e3440' }}>{label}</Text>
    </Pressable>
  );
}