--H2 Database
-- CREATE TABLE ADRESSE(ID integer auto_increment primary key, LIGNE1 VARCHAR(255), LIGNE2 VARCHAR(255), CODE_POSTAL integer, VILLE VARCHAR(255));
-- CREATE TABLE CLIENT(ID integer auto_increment primary key, PRENOM VARCHAR(255), NOM VARCHAR(255), ADRESSE_ID integer);
-- CREATE TABLE COMMANDE(ID integer auto_increment primary key, NOMBRE_PRODUITS integer, MONTANT integer, DATE_COMMANDE DATE, CLIENT_ID integer);

-- CREATE TABLE users(
	-- username varchar_ignorecase(50) not null primary key,
	-- password varchar_ignorecase(50) not null,
	-- enabled boolean not null
-- );

-- CREATE TABLE authorities (
	-- username varchar_ignorecase(50) not null,
	-- authority varchar_ignorecase(50) not null,
	-- constraint fk_authorities_users foreign key(username) references users(username)
-- ); 


--Postgres SQL Database
DROP TABLE IF EXISTS ADRESSE;
DROP TABLE IF EXISTS CLIENT;
DROP TABLE IF EXISTS COMMANDE;
CREATE TABLE ADRESSE(ID SERIAL primary key, LIGNE1 VARCHAR(255), LIGNE2 VARCHAR(255), CODE_POSTAL INT, VILLE VARCHAR(255));
CREATE TABLE CLIENT(ID SERIAL primary key, PRENOM VARCHAR(255), NOM VARCHAR(255), ADRESSE_ID INT);
CREATE TABLE COMMANDE(ID SERIAL primary key, NOMBRE_PRODUITS INT, MONTANT INT, DATE_COMMANDE DATE, CLIENT_ID INT);