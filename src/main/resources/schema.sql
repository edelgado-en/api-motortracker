------------------------------------------------------------
--MSSQL VERSION

create table TRACKER (
     ID BIGINT IDENTITY NOT NULL,
     SERIALNUMBER VARCHAR(100) NOT NULL,
     CONSTRAINT PK_TRACKER PRIMARY KEY(ID)
)

create table APP_USER (
  ID BIGINT IDENTITY NOT NULL,
  DISPLAYNAME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  UID VARCHAR(100) NOT NULL,
  CONSTRAINT PK_USER PRIMARY KEY(ID)
);

create table CAR (
     ID BIGINT IDENTITY NOT NULL,
     NAME VARCHAR(100) NOT NULL,
     PLATE VARCHAR(40) NOT NULL,
     TRACKER BIGINT NOT NULL,
     APP_USER BIGINT NOT NULL,
     CONSTRAINT PK_CAR PRIMARY KEY(ID),
     CONSTRAINT FK_CAR_TRACKER FOREIGN KEY(TRACKER) REFERENCES TRACKER(ID),
     CONSTRAINT FK_CAR_USER FOREIGN KEY(APP_USER) REFERENCES APP_USER(ID)
);

create table CAR_STATS (
   ID BIGINT IDENTITY NOT NULL,
   TIMESTAMP DATETIME NOT NULL,
   CAR BIGINT NOT NULL,
   COOLANT INT NOT NULL,
   OILTEMP INT NOT NULL,
   AIRTEMP INT NOT NULL,
   BOOSTPRESSURE INT NOT NULL,
   OILPRESSURE INT NOT NULL,
   CONSTRAINT PK_CAR_STATS PRIMARY KEY(ID),
   CONSTRAINT FK_CAR_STATS_CAR FOREIGN KEY(CAR) REFERENCES CAR(ID)
);

--------------------------------------------------------------------
--POSTGRESQL VERSION





create table TRACKER (
 ID serial PRIMARY KEY,
 SERIALNUMBER VARCHAR(100) NOT NULL
);

create table APP_USER (
  ID serial PRIMARY KEY,
  DISPLAYNAME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  UID VARCHAR(100) NOT NULL
);

create table CAR (
 ID serial PRIMARY KEY,
 NAME VARCHAR(100) NOT NULL,
 PLATE VARCHAR(40) NOT NULL,
 TRACKER BIGINT NOT NULL,
 APP_USER BIGINT NOT NULL,
 FOREIGN KEY(TRACKER) REFERENCES TRACKER(ID),
 FOREIGN KEY(APP_USER) REFERENCES APP_USER(ID)
);

create table CAR_STATS (
   ID serial PRIMARY KEY,
   TIMESTAMP TIMESTAMP NOT NULL,
   CAR BIGINT NOT NULL,
   COOLANT INT NOT NULL,
   OILTEMP INT NOT NULL,
   AIRTEMP INT NOT NULL,
   BOOSTPRESSURE DOUBLE PRECISION NOT NULL,
   OILPRESSURE INT NOT NULL,
   FOREIGN KEY(CAR) REFERENCES CAR(ID)
);