# Currency Conversion Micro Service
Run com.ragas.microservices.currencyconversionservice.CurrencyConversionServiceApplication as a Java Application.

## Resources

- http://localhost:8100/currency-conversion/from/EUR/to/INR/quantity/10

```json
{
id: 10002,
from: "EUR",
to: "INR",
conversionMultiple: 75,
quantity: 10,
totalCalculatedAmount: 750
}
```

### Creating Docker Images

- mvn clean install

### Running Containers

```
docker run --publish 8100:8100 --network currency-network --env CURRENCY_EXCHANGE_URI=http://currency-exchange:8000 cjragas/currency-conversion:1.0.0-SNAPSHOT
```

#### Test API 
- http://localhost:8100/currency-conversion/from/EUR/to/INR/quantity/10

```
docker login
docker push @@@REPO_NAME@@@/currency-conversion:0.0.1-SNAPSHOT
```

#### Environment Variable

```
        env:     #CHANGE
          - name: CURRENCY_EXCHANGE_URI
            valueFrom:
              configMapKeyRef:
                key: CURRENCY_EXCHANGE_URI
                name: currency-exchange-uri-demo
```

### Kubernetes Ingress

To create a global load balancer instead of a load balancer for each service use Ingress deployment. It creates global load balancer and routes traffic based on the URL path more like any of the proxy services. 