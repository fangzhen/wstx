# wstx--Web Service Transaction Implementation
The project is intend to give a simple implementation of Web Service 
Transaction Standards (WS-Coordination, WS-AtomicTransaction,
WS-BusinessActivity).

## Build

```bash
mvn install
```
The tests use jetty as servlet container which need port 8080 on localhost,
so make sure the port is not occupied.

## Architecture

Basically, A transaction (or activity) involves coordinators and participants. 
The coordinator coordinates the transaction by interactive with participant 
under certain protocols. These protocols are typically defined by 
WS-AtomicTransaction and WS-BusinessActivity. All protocol operations are 
provided by web services.


Therefore, architecture of coordinators and participants are described blow
respectively.

### Coordinator Side

<code>CoordinatorManager</code> is the Coordinator that manages all transaction
related with the site it resides at. As a result, it is singleton. It holds 
references of all transactions indexed by id. 

For transaction instances, <code>ActivityCoordinatorContext</code> and its
subclasses are in charge. They are responsible for activation and registration
of different coordination types and protocols.

### Participant Side


#### Initiator of WSAT
Similar with <code>CoordinatorManager</code>, <code>WsatTxManager</code>
manages all transactions involved in the site it resides at. 
<code>WsatTransaction</code> is for transaction instances.

