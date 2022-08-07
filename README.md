# FE0222B-BE_progetto_settimana9

Questo è un semplice progetto per prendere dimestichezza con le tecnologie REST fornite da Spring in ausilio dell'application server Wildfly.
E'presente lo strato di persistenza su database PostgreSQL implementato attraverso il framework ORM Hibernate.
Alcune scelte sono state prese per puro scopo didattico, si troveranno maggiori informazioni nelle classi interessate.
Tutti i metodi che l'API espone sono documentati con Swagger la quale documentazione e visibile all'URL /swagger-ui.html.

# Contenuti presenti
- [Quadro generale](#Quadro-generale)
- [Funzionalità principali](#Funzionalità-principali)
- [Utilizzo API - Regista](#Utilizzo-API-Regista)
- [Utilizzo API - Film](#Utilizzo-API-Film)
- [Risorse Aggiuntive](#Risorse-Aggiuntive)

## Quadro generale
L'applicazione espone metodi REST all'utente per la gestione di film e registi

## Funzionalità principali
### Regista
- Inserimento di un regista
- Modifica di un regista
- Cancellazione di un regista
- Recupero di tutti i registi
- Recupero di uno specifico regista

### Film
- Inserimento di un film
- Cancellazione di un film
- Recupero dei film di un regista
- Ricerca dei film di un regista per cognome

Ciascuna di queste chiamate, rende persistenti, modifica, cancella o recupera dati da e sul database Postgres sottostante

## Utilizzo API Regista
### Metodi GET
- **Recupero tramite id**:  
  - /regista/{id_regista}
- **Recupero di tutti i registi**:   
  - /regista/getall

### Metodi POST
- **Inserimento**:
  - /regista/inserisci
```
//request body
{
    "nome": "Steven",
    "cognome": "Spielberg",
    "annoNascita": 1946
}
```
### Metodi DELETE
- **Cancellazione**:
  - /regista/delete/{id_regista}

### Metodi PUT
- **Modifica**:
  - /regista/update
```
 //request body
{
    "id" : 1,
    "nome" : "Stefano",
    "cognome" : "Spielberg",
    "annoNascita": 1982
}
```

## Utilizzo API Film
### Metodi GET
- **Recupero dei film per id del regista**:  
  - /film/getAllFilmsByIdRegista/{id_regista}
- **Recupero dei film per cognome del regista**:
  - /film/searchFilmsBySurname?search={cognome}

### Metodi POST
- **Inserimento**:
  - /film/inserisci
```
//request body
{
    "titolo": "Jurassic Park",
    "anno": 1993,
    "tipo": "Avventura",
    "idRegista": 1,
    "incasso": "1029153882"
}
```
### Metodi DELETE
- **Cancellazione**:
  - /film/delete/{id}

 
 ## Risorse Aggiuntive
 E' fornito il file di esportazione di Postman contentente già la configurazione corretta dei metodi per poterli testare
