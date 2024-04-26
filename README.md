# Challenge Conversor de Moedas
Esse projeto tem como objetivo fazer a conversão de moedas com base na cotação em tempo real, utilizamos a [Exchangerate Api](https://app.exchangerate-api.com/) para nos fornecer os dados atualizados da cotação.

## Como usar
1. O programa começa na página de configuração, nela você terá que digitar o valor númerico que representa a sua moeda de origem ou o código da moeda, em seguida irá pedir o código ou valor da moeda de destino.
2. Após a configuração você podera fazer suas converções ou executar comandos.

### Comandos

1. Escreva apenas valores númericos para a converção da moeda, insira `.` para declarar as casas decimais.

```
/ 1000.55
```

2. Use `invert` para inverter a ordem do calculo, a moeda de origem passa a ser a moeda de destino enquanto a moeda de destino passa a ser a moeda de origem.

```
/ invert
```

3. Use `config` para retornar a configuração da moeda

```
/ config
```
