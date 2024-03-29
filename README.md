# Progetto per l'esame di OOP

In questa repository è presente il progetto sviluppato per il corso Programmazione ad Oggetti, relativo all'appello di luglio 2019 da parte degli studenti Perozzi Matteo e Zamponi Ettore. Per ulteriori informazioni, cosnultare la sezione "Wiki".

***

## Descrizione

Dopo esserci stato assegnato un dataset CSV con all'interno molti valori, abbiamo realizzato un applicativo Java in grado di effettuare il download del file stesso dalla sorgente, effettuare il parsing dei dati ed immagazzinarli in un'apposita struttura (ArrayList). Questa sarà poi manipolabile dall'utente mediante filtri appositi per andar a visualizzare alcuni valori specificati dall'utente piuttosto che altri, il tutto tramite interrogazioni API REST.

Lo sviluppo è stato effettuato mediante il linguaggio Java e l'IDE IntelliJ IDEA e il framework Spring. Le richieste dell'utente, invece, sono state applicate tramite Postman. Altri software sono stati Maven e naturalmente GitHub. 

***

## Struttura del progetto

Abbiamo strutturato il nostro progetto come segue per renderlo più comprensibile possibile e facilitarne la manutenzione:

* com.company.projectoop --> include la classe che contiene il main da cui si avvia l'applicazione
* com.company.projectoop.controller --> pacchetto che include il controller dell'applicazione
* com.company.projectoop.model --> pacchetto che include le classi che mappano le strutture dati di base
* com.company.projectoop.parsing --> pacchetto con i metodi per effettuare il parsing dei dati
* com.company.projectoop.table --> pacchetto che include la struttura che incapsula i metadati e la classe che definisce la riga della tabella dei dati

***


## Diagrammi UML

Tramite il linguaggio UML di modellazione specifico per la programmazione ad oggetti abbiamo realizzato i seguenti diagrammi. 

### Diagrammi delle classi

![Package progectoop e controller](https://user-images.githubusercontent.com/33483944/61475900-18e9c580-a98c-11e9-8f31-dc1ce873dcca.jpg)

![Package table](https://user-images.githubusercontent.com/33483944/61476049-70883100-a98c-11e9-881a-e6a916989117.jpg)

![Package parsing](https://user-images.githubusercontent.com/33483944/61476101-90b7f000-a98c-11e9-863a-8372fdba6df3.jpg)


![Package model](https://user-images.githubusercontent.com/33483944/61476143-a88f7400-a98c-11e9-862a-e9a0be49ddbf.jpg)


***


### Diagramma dei Casi d'uso

![Casi D'uso](https://user-images.githubusercontent.com/33483944/61475600-7cbfbe80-a98b-11e9-8aa5-0c28c6fe0d2d.jpg)


***


## Tabella dei rooth path per API REST GET

* `/getalldata` --> Per restituire un JSON con tutti i dati del dataset organizzati
* `/getmetadata` --> Per restituire un JSON con tutti i metadati
* `/logicalfilter/operatore?field1="primo campo del dataset sul quale applicare il filtro"&value1="valore del primo campo"&field2="secondo campo del dataset sul quale applicare il filtro"&value2="valore del secondo campo"` --> Per applicare i filtri logici
* `/conditionalfilter/operatore?field=campo numerico del dataset&value=valore sul quale effettuare l'operazione` --> Per applicare i filtri condizionali maggiore, maggiore uguale, minore e minore uguale
* `/conditionalfilter/operatore?field=campo numerico del dataset&value=valore sul quale effettuare l'operazione&value2=altro valore sul quale effettuare l'operazione` --> Per applicare il filtro condizionale compreso tra i due valori inseriti
* `/stats/campo numerico del dataset sul quale effettuare la statistica` --> Per ottenere le statistiche complete di un determinato campo
* `/singlestats/campo sul quale effettuare la statistica/statistica desiderata` ---> Per ottenere la singola statistica di un determinato campo
* `/get/campo del dataset` --> Per ottenere i valori di un determinato campo della tabella
* `/countocc/campo del dataset/valore del campo che si vuole contare` -->  Per contare quante occorrenze di quel determinato valore ci sono all'interno di un campo

***

## Statistiche

`http://localhost:8080/stats/ver`

    {
        "count: ": 96,
        "devstd: ": 0.2927490938741712,
        "avg: ": 1.4052085,
        "max: ": 2.2,
        "min: ": 1.2,
        "field: ": "ver",
        "sum: ": 134.90001
    }


***

`http://localhost:8080/singlestats/ver/avg`


    {
        "avg: ": 1.4052085,
        "field: ": "ver"
    }


***

`http://localhost:8080/countocc/indicator_type_code/"COMMON_RES"`


    {
        "countocc: ": 94,
        "field: ": "indicator_type_code"
    }


***

## Filtri implementati

### Filtri logici

* AND : `$and`
* OR : `$or`
* NOT : `$not`

### Filtri condizionali

* MAGGIORE (>) : `$gt`
* MAGGIORE UGUALE (>=) : `$gte`
* MINORE (<) : `$lt`
* MINORE UGUALE (<=) : `$lte`
* COMPRESO TRA (>=value<=) : `$bt`

***

## Esempi di filtri

### Filtri logici

`http://localhost:8080/logicalfilter/$not?field1=indicator_type_code&value1="COMMON_RES"`

    {
        "ms": "\"PT\"",
        "ms_name": "\"Portugal\"",
        "cci": "\"2014PT16M2OP004\"",
        "ver": 1.6,
        "title": "\"Azores - ERDF/ESF\"",
        "fund": "\"ESF\"",
        "indicator_type_code": "\"OESF\"",
        "ind_code": "\"CO17\"",
        "ind_group_code": "\"CO17\"",
        "indicator_short_name": "\"Other disadvantaged\"",
        "indicator_long_name": "\"Other disadvantaged\"",
        "to": 9,
        "to_short": "\"Social Inclusion\"",
        "priority_code": "\"9\"",
        "investment_priority": "\"9i\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"VOID\"",
        "target_value": 0,
        "measurement_unit": "\"Number\"",
        "nominator": 0,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2017.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"773\"",
        "implemented_nominator": "\"773\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"ES\"",
        "ms_name": "\"Spain\"",
        "cci": "\"2014ES06RDRP009\"",
        "ver": 2.1,
        "title": "\"Cataluña  - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"SPECIFIC_RES\"",
        "ind_code": "\"%\"",
        "ind_group_code": "\"%\"",
        "indicator_short_name": "\"%\"",
        "indicator_long_name": "\"%\"",
        "to": 3,
        "to_short": "\"Competitiveness of SMEs\"",
        "priority_code": "\"P3\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"3A\"",
        "target_value": 342,
        "measurement_unit": "\"Nº de operaciones\"",
        "nominator": 342,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "\"2\"",
        "forecast_nominator": "\"2\"",
        "forecast_denominator": "",
        "implemented_value": "",
        "implemented_nominator": "",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    }


***


`http://localhost:8080/logicalfilter/$and?field1=fund&value1="ESF"&field2=ind_code&value2="CO17"`

    {
        "ms": "\"PT\"",
        "ms_name": "\"Portugal\"",
        "cci": "\"2014PT16M2OP004\"",
        "ver": 1.6,
        "title": "\"Azores - ERDF/ESF\"",
        "fund": "\"ESF\"",
        "indicator_type_code": "\"OESF\"",
        "ind_code": "\"CO17\"",
        "ind_group_code": "\"CO17\"",
        "indicator_short_name": "\"Other disadvantaged\"",
        "indicator_long_name": "\"Other disadvantaged\"",
        "to": 9,
        "to_short": "\"Social Inclusion\"",
        "priority_code": "\"9\"",
        "investment_priority": "\"9i\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"VOID\"",
        "target_value": 0,
        "measurement_unit": "\"Number\"",
        "nominator": 0,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2017.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"773\"",
        "implemented_nominator": "\"773\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    }


***

`http://localhost:8080/logicalfilter/$or?field1=ver&value1=2&field2=ms&value2="UK"`


    {
        "ms": "\"UK\"",
        "ms_name": "\"United Kingdom\"",
        "cci": "\"2014UK06RDRP002\"",
        "ver": 2.0,
        "title": "\"Northern Ireland - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 29500,
        "measurement_unit": "\"participants\"",
        "nominator": 29500,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.0,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": "\"Y\"",
        "finance_coverage": "\"Y\"",
        "ind_group_name": "\"Y\""
    },
    {
        "ms": "\"UK\"",
        "ms_name": "\"United Kingdom\"",
        "cci": "\"2014UK06RDRP004\"",
        "ver": 1.3,
        "title": "\"Wales - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 13000,
        "measurement_unit": "\"participants\"",
        "nominator": 13000,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": "\"Y\"",
        "finance_coverage": "\"Y\"",
        "ind_group_name": "\"Y\""
    }


***

### Filtri condizionali

`http://localhost:8080/conditionalfilter/$gt?field=ver&value=2.1`

    {
        "ms": "\"DE\"",
        "ms_name": "\"Germany\"",
        "cci": "\"2014DE06RDRP019\"",
        "ver": 2.2,
        "title": "\"Sachsen - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 4500,
        "measurement_unit": "\"participants\"",
        "nominator": 4500,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.0,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"FR\"",
        "ms_name": "\"France\"",
        "cci": "\"2014FR06RDRP073\"",
        "ver": 2.2,
        "title": "\"Midi-Pyrénées - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 30450,
        "measurement_unit": "\"participants\"",
        "nominator": 30450,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"IT\"",
        "ms_name": "\"Italy\"",
        "cci": "\"2014IT06RDRP003\"",
        "ver": 2.2,
        "title": "\"Emilia-Romagna - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 19245,
        "measurement_unit": "\"participants\"",
        "nominator": 19245,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"LV\"",
        "ms_name": "\"Latvia\"",
        "cci": "\"2014LV06RDNP001\"",
        "ver": 2.2,
        "title": "\"Latvia - National Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 53300,
        "measurement_unit": "\"participants\"",
        "nominator": 53300,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    }


***

`http://localhost:8080/conditionalfilter/$bt?field=ver&value=2&value2=2.5`

    {
        "ms": "\"DE\"",
        "ms_name": "\"Germany\"",
        "cci": "\"2014DE06RDRP003\"",
        "ver": 2.1,
        "title": "\"Baden-Württemberg - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 2355,
        "measurement_unit": "\"participants\"",
        "nominator": 2355,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"DE\"",
        "ms_name": "\"Germany\"",
        "cci": "\"2014DE06RDRP007\"",
        "ver": 2.1,
        "title": "\"Berlin / Brandenburg - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 13800,
        "measurement_unit": "\"participants\"",
        "nominator": 13800,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"DE\"",
        "ms_name": "\"Germany\"",
        "cci": "\"2014DE06RDRP019\"",
        "ver": 2.2,
        "title": "\"Sachsen - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 4500,
        "measurement_unit": "\"participants\"",
        "nominator": 4500,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.0,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"ES\"",
        "ms_name": "\"Spain\"",
        "cci": "\"2014ES06RDRP009\"",
        "ver": 2.1,
        "title": "\"Cataluña  - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 18900,
        "measurement_unit": "\"participants\"",
        "nominator": 18900,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"FI\"",
        "ms_name": "\"Finland\"",
        "cci": "\"2014FI06RDRP001\"",
        "ver": 2.1,
        "title": "\"Mainland Finland - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 20000,
        "measurement_unit": "\"participants\"",
        "nominator": 20000,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.2,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"FR\"",
        "ms_name": "\"France\"",
        "cci": "\"2014FR06RDRP073\"",
        "ver": 2.2,
        "title": "\"Midi-Pyrénées - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 30450,
        "measurement_unit": "\"participants\"",
        "nominator": 30450,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"FR\"",
        "ms_name": "\"France\"",
        "cci": "\"2014FR06RDRP091\"",
        "ver": 2.1,
        "title": "\"Languedoc-Roussillon - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 1675,
        "measurement_unit": "\"participants\"",
        "nominator": 1675,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"IT\"",
        "ms_name": "\"Italy\"",
        "cci": "\"2014IT06RDRP003\"",
        "ver": 2.2,
        "title": "\"Emilia-Romagna - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 19245,
        "measurement_unit": "\"participants\"",
        "nominator": 19245,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"LV\"",
        "ms_name": "\"Latvia\"",
        "cci": "\"2014LV06RDNP001\"",
        "ver": 2.2,
        "title": "\"Latvia - National Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"COMMON_RES\"",
        "ind_code": "\"RI_1C_nb_part_train\"",
        "ind_group_code": "\"RI_1C_nb_part_train\"",
        "indicator_short_name": "\"Training\"",
        "indicator_long_name": "\"Participants trained\"",
        "to": 10,
        "to_short": "\"Educational & Vocational Training\"",
        "priority_code": "\"P1\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"1C\"",
        "target_value": 53300,
        "measurement_unit": "\"participants\"",
        "nominator": 53300,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "",
        "forecast_nominator": "",
        "forecast_denominator": "",
        "implemented_value": "\"0\"",
        "implemented_nominator": "\"0\"",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    },
    {
        "ms": "\"ES\"",
        "ms_name": "\"Spain\"",
        "cci": "\"2014ES06RDRP009\"",
        "ver": 2.1,
        "title": "\"Cataluña  - Rural Development\"",
        "fund": "\"EAFRD\"",
        "indicator_type_code": "\"SPECIFIC_RES\"",
        "ind_code": "\"%\"",
        "ind_group_code": "\"%\"",
        "indicator_short_name": "\"%\"",
        "indicator_long_name": "\"%\"",
        "to": 3,
        "to_short": "\"Competitiveness of SMEs\"",
        "priority_code": "\"P3\"",
        "investment_priority": "\"VOID\"",
        "measure_code": "\"VOID\"",
        "focus_area_code": "\"3A\"",
        "target_value": 342,
        "measurement_unit": "\"Nº de operaciones\"",
        "nominator": 342,
        "denominator": "",
        "is_division": "\"N\"",
        "year": 2015,
        "ir_ver": 2015.1,
        "forecast_value": "\"2\"",
        "forecast_nominator": "\"2\"",
        "forecast_denominator": "",
        "implemented_value": "",
        "implemented_nominator": "",
        "implemented_denominator": "",
        "visualize_by_to": "\"Y\"",
        "visualize_by_ms": "\"Y\"",
        "ms_op_coverage": null,
        "finance_coverage": null,
        "ind_group_name": null
    }

***

## Per accedere alla documentazione del codice:
1. Aprire la directory javaDOC
2. Aprire la directory html
@@ -16,12 +19,29 @@ RIFERIMENTO ALL FILE HTML:  https://github.com/mperozzi/Progetto_OOP/blob/master
