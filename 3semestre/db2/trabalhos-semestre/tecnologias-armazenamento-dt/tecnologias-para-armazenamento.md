# Trabalho BD2 - Tecnologias para armazenamento de dados

## 1) Afinal de contas, qual a necessidade que uma empresa teria de implementar algum nível de RAID em seus servidores?

A principal necessidade de implementar RAID em servidores é garantir disponibilidade e integridade dos dados em caso de falha de hardware. Discos rígidos são componentes físicos com vida útil limitada e sujeitos a falhas a qualquer momento. Dependendo do nível de RAID utilizado, é possível que um disco falhe completamente sem que haja perda de dados ou interrupção do serviço, já que os dados podem estar replicados ou distribuídos entre múltiplos discos. Em ambientes de produção, onde a indisponibilidade representa prejuízo direto, isso se torna praticamente obrigatório.

---

## 2) Ora, mas se a empresa tem uma boa política de backup, as tecnologias RAID não seriam desnecessárias? Afinal de contas, se eu tiver um problema com um servidor ou com um disco, basta eu restaurar o backup

Essa afirmação não está correta. Backup e RAID resolvem problemas diferentes e são complementares. O backup protege contra perda de dados causada por erros humanos, corrupção, ransomware ou desastres, mas a restauração de um backup leva tempo, já que depende do volume de dados, levando horas ou até dias. O RAID resolve o problema de disponibilidade imediata: se um disco falha em um arranjo RAID 1 ou RAID 5, por exemplo, o sistema continua operando normalmente sem nenhuma interrupção. Ou seja, o backup garante a recuperação dos dados, mas não garante continuidade de serviço no momento da falha. Uma boa estratégia de armazenamento precisa dos dois.

---

## 3) Se você não encontrou nos textos lidos, dê uma consultada na internet para escrever o que significa o conceito de Hot Swap com relação às tecnologias RAID

Hot Swap é a capacidade de substituir um disco com defeito em um servidor sem precisar desligar a máquina ou interromper o serviço. Em arranjos RAID que suportam essa funcionalidade, quando um disco falha, o administrador pode simplesmente remover o disco defeituoso e inserir um novo, ainda com o sistema em funcionamento. O próprio controlador RAID detecta o novo disco e inicia automaticamente a reconstrução dos dados. Isso é extremamente relevante em ambientes de produção onde qualquer janela de manutenção representa indisponibilidade e custo.

---

## 4) Virtualização parece ser uma solução perfeita, já que permite que, se compramos uma máquina servidora super poderosa, todos os sistemas instalados nela vão aproveitar ao máximo seus recursos de hardware. Correto? Ou existem desvantagens? Se existem, quais você citaria?

A virtualização tem vantagens claras de consolidação e aproveitamento de hardware, mas não é uma solução perfeita. As principais desvantagens são:

- Overhead do hypervisor: o software de virtualização consome parte dos recursos de CPU, memória e I/O, ou seja, o hardware nunca é aproveitado a 100% pelas VMs.
- Ponto único de falha: se o servidor físico que hospeda as VMs cair, todos os sistemas virtualizados nele ficam indisponíveis simultaneamente, o que aumenta o impacto de uma única falha de hardware.
- Contenção de recursos: em cenários de alta carga, as VMs competem pelos mesmos recursos físicos. Uma VM com pico de consumo pode degradar o desempenho das outras.
- Complexidade de gerenciamento: adiciona uma camada extra de administração, sendo preciso gerenciar tanto o sistema host quanto cada VM individualmente.

De forma geral, a virtualização é uma excelente estratégia quando bem dimensionada, mas exige planejamento de capacidade e alta disponibilidade no nível do host para não criar problemas maiores do que resolve.
