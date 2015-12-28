/*
Created		30/05/2007
Modified		30/05/2007
Project		
Model			
Company		
Author		
Version		
Database		PostgreSQL 8 
*/


/* Create Tables */


Create table "materiaprima"
(
	"materiaprimaid" BigSerial NOT NULL,
	"codigo" Varchar,
	"stock" Bigint,
	"tiempofabricacion" Bigint,
 primary key ("materiaprimaid")
) With Oids;

Create table "articulo"
(
	"articuloid" BigSerial NOT NULL,
	"codigo" Varchar,
	"talle" Varchar,
	"descripcion" Varchar,
	"color" Varchar,
	"seccion" Varchar,
	"pvu" Varchar,
	"origen" Varchar,
 primary key ("articuloid")
) With Oids;

Create table "articulomateriaprima"
(
	"articuloid" Bigint NOT NULL,
	"materiaprimaid" Bigint NOT NULL,
	"cantidad" Bigint,
 primary key ("articuloid","materiaprimaid")
) With Oids;

Create table "proveedor"
(
	"proveedorid" BigSerial NOT NULL,
	"razonsocial" Char(20),
 primary key ("proveedorid")
) With Oids;

Create table "ordencompra"
(
	"ordencompraid" BigSerial NOT NULL,
	"proveedorid" Bigint NOT NULL,
	"ordencompraestadoid" Bigint NOT NULL,
	"fecha" Date,
 primary key ("ordencompraid")
) With Oids;

Create table "ordencompraestado"
(
	"ordencompraestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("ordencompraestadoid")
) With Oids;

Create table "ordencompraitem"
(
	"ordencompraitemid" BigSerial NOT NULL,
	"cantidad" Bigint,
	"ordencompraid" Bigint NOT NULL,
	"ordencompraitemestadoid" Bigint NOT NULL,
	"materiaprimaid" Bigint NOT NULL,
 primary key ("ordencompraitemid")
) With Oids;

Create table "ordencompraitemestado"
(
	"ordencompraitemestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("ordencompraitemestadoid")
) With Oids;

Create table "centrodistribucion"
(
	"centrodistribucionid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("centrodistribucionid")
) With Oids;

Create table "solicitudfabricacion"
(
	"solicitudfabricacionid" BigSerial NOT NULL,
	"fecha" Date,
	"centrodistribucionid" Bigint NOT NULL,
	"solicitudfabricacionestadoid" Bigint NOT NULL,
 primary key ("solicitudfabricacionid")
) With Oids;

Create table "solicitudfabricacionestado"
(
	"solicitudfabricacionestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("solicitudfabricacionestadoid")
) With Oids;

Create table "ordenfabricacion"
(
	"ordenfabricacionid" BigSerial NOT NULL,
	"fecha" Date,
	"cantidad" Bigint,
	"articuloid" Bigint NOT NULL,
	"ordenfabricacionestadoid" Bigint NOT NULL,
 primary key ("ordenfabricacionid")
) With Oids;

Create table "ordenfabricacionestado"
(
	"ordenfabricacionestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("ordenfabricacionestadoid")
) With Oids;

Create table "solicitudfabricacionitem"
(
	"solicitudfabricacionitemid" BigSerial NOT NULL,
	"cantidad" Bigint,
	"solicitudfabricacionid" Bigint NOT NULL,
	"articuloid" Bigint NOT NULL,
	"solicitudfabricacionitemestadoid" Bigint NOT NULL,
 primary key ("solicitudfabricacionitemid")
) With Oids;

Create table "solicitudfabricacionitemestado"
(
	"solicitudfabricacionitemestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("solicitudfabricacionitemestadoid")
) With Oids;

Create table "ordenreposicion"
(
	"ordenreposicionid" BigSerial NOT NULL,
	"fecha" Date,
	"comentario" Varchar,
	"ordenreposicionestadoestadoid" Bigint NOT NULL,
	"centrodistribucionid" Bigint NOT NULL,
 primary key ("ordenreposicionid")
) With Oids;

Create table "ordenreposicionestado"
(
	"ordenreposicionestadoestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("ordenreposicionestadoestadoid")
) With Oids;

Create table "ordenreposicionitem"
(
	"ordenreposicionestadoid" BigSerial NOT NULL,
	"ordenreposicionitemestadoid" Bigint NOT NULL,
	"cantidad" Bigint,
	"articuloid" Bigint NOT NULL,
	"ordenreposicionid" Bigint NOT NULL,
 primary key ("ordenreposicionestadoid")
) With Oids;

Create table "ordenreposicionitemestado"
(
	"ordenreposicionitemestadoid" BigSerial NOT NULL,
	"nombre" Varchar,
 primary key ("ordenreposicionitemestadoid")
) With Oids;

Create table "ordenreposicionitemsolicitudfabricacionitem"
(
	"ordenreposicionestadoid" Bigint NOT NULL,
	"solicitudfabricacionitemid" Bigint NOT NULL,
 primary key ("ordenreposicionestadoid","solicitudfabricacionitemid")
) With Oids;



/* Create Alternate Keys */



/* Create Foreign Keys */

Alter table "articulomateriaprima" add  foreign key ("materiaprimaid") references "materiaprima" ("materiaprimaid") on update restrict on delete restrict;

Alter table "ordencompraitem" add  foreign key ("materiaprimaid") references "materiaprima" ("materiaprimaid") on update restrict on delete restrict;

Alter table "articulomateriaprima" add  foreign key ("articuloid") references "articulo" ("articuloid") on update restrict on delete restrict;

Alter table "ordenfabricacion" add  foreign key ("articuloid") references "articulo" ("articuloid") on update restrict on delete restrict;

Alter table "solicitudfabricacionitem" add  foreign key ("articuloid") references "articulo" ("articuloid") on update restrict on delete restrict;

Alter table "ordenreposicionitem" add  foreign key ("articuloid") references "articulo" ("articuloid") on update restrict on delete restrict;

Alter table "ordencompra" add  foreign key ("proveedorid") references "proveedor" ("proveedorid") on update restrict on delete restrict;

Alter table "ordencompraitem" add  foreign key ("ordencompraid") references "ordencompra" ("ordencompraid") on update restrict on delete restrict;

Alter table "ordencompra" add  foreign key ("ordencompraestadoid") references "ordencompraestado" ("ordencompraestadoid") on update restrict on delete restrict;

Alter table "ordencompraitem" add  foreign key ("ordencompraitemestadoid") references "ordencompraitemestado" ("ordencompraitemestadoid") on update restrict on delete restrict;

Alter table "solicitudfabricacion" add  foreign key ("centrodistribucionid") references "centrodistribucion" ("centrodistribucionid") on update restrict on delete restrict;

Alter table "ordenreposicion" add  foreign key ("centrodistribucionid") references "centrodistribucion" ("centrodistribucionid") on update restrict on delete restrict;

Alter table "solicitudfabricacionitem" add  foreign key ("solicitudfabricacionid") references "solicitudfabricacion" ("solicitudfabricacionid") on update restrict on delete restrict;

Alter table "solicitudfabricacion" add  foreign key ("solicitudfabricacionestadoid") references "solicitudfabricacionestado" ("solicitudfabricacionestadoid") on update restrict on delete restrict;

Alter table "ordenfabricacion" add  foreign key ("ordenfabricacionestadoid") references "ordenfabricacionestado" ("ordenfabricacionestadoid") on update restrict on delete restrict;

Alter table "ordenreposicionitemsolicitudfabricacionitem" add  foreign key ("solicitudfabricacionitemid") references "solicitudfabricacionitem" ("solicitudfabricacionitemid") on update restrict on delete restrict;

Alter table "solicitudfabricacionitem" add  foreign key ("solicitudfabricacionitemestadoid") references "solicitudfabricacionitemestado" ("solicitudfabricacionitemestadoid") on update restrict on delete restrict;

Alter table "ordenreposicionitem" add  foreign key ("ordenreposicionid") references "ordenreposicion" ("ordenreposicionid") on update restrict on delete restrict;

Alter table "ordenreposicion" add  foreign key ("ordenreposicionestadoestadoid") references "ordenreposicionestado" ("ordenreposicionestadoestadoid") on update restrict on delete restrict;

Alter table "ordenreposicionitemsolicitudfabricacionitem" add  foreign key ("ordenreposicionestadoid") references "ordenreposicionitem" ("ordenreposicionestadoid") on update restrict on delete restrict;

Alter table "ordenreposicionitem" add  foreign key ("ordenreposicionitemestadoid") references "ordenreposicionitemestado" ("ordenreposicionitemestadoid") on update restrict on delete restrict;


