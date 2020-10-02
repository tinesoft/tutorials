
## sfeir-spring-cloud-contract

Companion project for my talk about how to test microservices interoperability with Spring  Cloud Contract.

* [Slides](https://docs.google.com/presentation/d/1uIm86M2pSje7U75JiQrxnuPTRmhjfJoWd8caOARFNgg)  

### Use case

2 microservices communicate with each other:

* [sfeir-scc-producer](sfeir-scc-producer): a **bank server** (the producer) that exposes an API to perform common bank operations like money withdrawal, etc.
* [sfeir-scc-consumer](sfeir-scc-consumer): **an ATM (Automatic Teller Machine) client** (the consumer) that uses server API to allow consumers to withdraw money from their account.
