# Currency Exchange Micro Service - H2

Run com.ragas.microservices.currencyconversionservice.CurrencyConversionServiceApplicationH2 as a Java Application.

## Resources

- http://localhost:8000/currency-exchange/from/USD/to/INR

```json
{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65.00,
  "environmentInfo": "NA"
}
```

## H2 Console

- http://localhost:8000/h2-console
- Use `jdbc:h2:mem:testdb` as JDBC URL

### Creating Docker Images

- mvn clean install

### Running Container

#### Basic
```
docker container run --publish 8000:8000 cjragas/currency-exchange:1.0.0-SNAPSHOT
```