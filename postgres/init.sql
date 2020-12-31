SET CLIENT_ENCODING TO 'UTF8';

CREATE TABLE USER_BV (
	USER_ID SERIAL NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	SURNAME VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	PASSWORD_HASH VARCHAR(100) NOT NULL,
	IS_VALID BOOLEAN NOT NULL,
	PRIMARY KEY (USER_ID)
);

CREATE TABLE PLACE_BV (
	PLACE_ID SERIAL NOT NULL,
	TITLE VARCHAR(1000),
	ADDRESS_LABEL VARCHAR(1000),
	CITY VARCHAR(1000),
	LAT VARCHAR(1000),
	LNG VARCHAR(1000),
	PHONES VARCHAR(1000),
	WEB_PAGES VARCHAR(1000),
	EMAILS VARCHAR(1000),
	WORKING_TIME VARCHAR(1000),
	INFO VARCHAR(1000),
	PICTURE VARCHAR(1000),
	TRIP_ID INTEGER,
	PRIMARY KEY (PLACE_ID)
);

CREATE TABLE ROUTE_BV (
	ROUTE_ID SERIAL NOT NULL,
	TOTAL_DISTANCE VARCHAR(1000),
	TOTAL_TIME VARCHAR(1000),
	FARE VARCHAR(1000),
	INSTRUCTIONS VARCHAR(10000),
	POINTS VARCHAR(10000),
	ORIGIN_LAN VARCHAR(1000),
	ORIGIN_LNG VARCHAR(1000),
	DESTINATION_LAN VARCHAR(1000),
	DESTINATION_LNG VARCHAR(1000),
	ORIGIN_TITLE VARCHAR(1000),
	ORIGIN_ADDRESS_LABEL VARCHAR(1000),
	PRIMARY KEY (ROUTE_ID)
);

CREATE TABLE TRIP_BV (
	TRIP_ID SERIAL NOT NULL,
	USER_ID SERIAL NOT NULL,
	PLACE_ID SERIAL NOT NULL,
	ROUTE_ID SERIAL NOT NULL,
	TRIP_TYPE VARCHAR(50) NOT NULL,
	CREATED_AT DATE NOT NULL,
	PRIMARY KEY (TRIP_ID),
	FOREIGN KEY (USER_ID) REFERENCES USER_BV (USER_ID) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (PLACE_ID) REFERENCES PLACE_BV (PLACE_ID) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (ROUTE_ID) REFERENCES ROUTE_BV (ROUTE_ID) ON UPDATE CASCADE ON DELETE CASCADE
);
