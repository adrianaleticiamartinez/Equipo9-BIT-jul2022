# Equipo9-BIT-jul2022

La instancia basada en la nube de Elasticsearch es la siguiente:

https://bbva-hackathon.kb.us-central1.gcp.cloud.es.io:9243/
#### Manager
usuario manager: AndArt \
password: qwerty \
Consulta: \
GET info_client_manager/_search
{
  "query": {
    "match": {
      "idCliente": "BF000022378"
    }
  }
}
#### Validador
usuario validador: ENM \
password: nkdk3Cs \
Consulta: \
GET info_client_validador/_search
{
  "query": {
    "match": {
      "idCliente": "BF000022378"
    }
  }
}

##### Restringido 
usuario restringido: RaVen_1 \
password: pass123 \
GET info_client_restringido/_search
{
  "query": {
    "match": {
      "idCliente": "BF000022378"
    }
  }
}
