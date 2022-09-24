create table bankomat(
      id         varchar(40),--UUID: ea1b5aeb-ab59-41de-abfa-350bd01e6ece

      name      varchar(255),
      saldo      DECIMAL,
      miasto     varchar(255),
      ulica      varchar(255),
      czy_aktywny   boolean,
      PRIMARY KEY(id)
      );

       create table konto_bankowe(
       id          varchar(255) PRIMARY KEY,
       imie        varchar(255),
       nazwisko    varchar(255),
       saldo       DECIMAL,
       data_ur     timestamp,
       miasto      varchar(255)
       );


