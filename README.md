# Test_Backend


POST /transactions/
{
"timestamp": 1478221904000,
"amount": 25000.15
}
--
201 Created



GET /statistics
--
200 OK
{
"sum": 25170.91,
"min": 20.00,
"max": 25000.17,
"avg": 8390.30,
"count": 3
}
Onde:
● sum é um double do total da soma das transações dos últimos 60 segundos;
● avg é um double com a média dos valores das transações dos últimos 60 segundos;
● max é um double com o valor máximo da transações dos últimos 60 segundos;
● min é um double com o valor mínimo das transações dos últimos 60 segundos;
● count é um long com o número total das transações que aconteceram nos últimos 60
segundos;
