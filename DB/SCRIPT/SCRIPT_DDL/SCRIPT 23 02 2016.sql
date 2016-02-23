/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     23/02/2016 9:54:37                           */
/*==============================================================*/


/*==============================================================*/
/* Table: CATALOGO                                              */
/*==============================================================*/
create table CATALOGO (
   ID_CATALOGO          SERIAL               not null,
   DESCRIPCION_CATALOGO TEXT                 null,
   constraint PK_CATALOGO primary key (ID_CATALOGO)
);

/*==============================================================*/
/* Index: CATALOGO_PK                                           */
/*==============================================================*/
create unique index CATALOGO_PK on CATALOGO (
ID_CATALOGO
);

/*==============================================================*/
/* Table: DETALLE_CATALOGO                                      */
/*==============================================================*/
create table DETALLE_CATALOGO (
   ID_DETALLE_CATALOGO  SERIAL               not null,
   ID_CATALOGO          INT4                 null,
   COD_DETALLE_CATALOGO TEXT                 null,
   COD_DEP_CATALOGO     TEXT                 null,
   COD_DEP_DETALLE_CATALOGO TEXT                 null,
   DESC_DET_CATALOGO    TEXT                 null,
   constraint PK_DETALLE_CATALOGO primary key (ID_DETALLE_CATALOGO)
);

/*==============================================================*/
/* Index: DETALLE_CATALOGO_PK                                   */
/*==============================================================*/
create unique index DETALLE_CATALOGO_PK on DETALLE_CATALOGO (
ID_DETALLE_CATALOGO
);

/*==============================================================*/
/* Index: DETALLE_CATALOGO_FK                                   */
/*==============================================================*/
create  index DETALLE_CATALOGO_FK on DETALLE_CATALOGO (
ID_CATALOGO
);

/*==============================================================*/
/* Table: DETALLE_EGRESO                                        */
/*==============================================================*/
create table DETALLE_EGRESO (
   ID_DET_EGRESO        SERIAL               not null,
   ID_EGRESO            INT4                 null,
   COD_PARTIDA          INT4                 null,
   PARTIDA              TEXT                 null,
   PRESUPUESTO          DECIMAL              null,
   constraint PK_DETALLE_EGRESO primary key (ID_DET_EGRESO)
);

/*==============================================================*/
/* Index: DETALLE_EGRESO_PK                                     */
/*==============================================================*/
create unique index DETALLE_EGRESO_PK on DETALLE_EGRESO (
ID_DET_EGRESO
);

/*==============================================================*/
/* Index: DETALLE_EGRESO_FK                                     */
/*==============================================================*/
create  index DETALLE_EGRESO_FK on DETALLE_EGRESO (
ID_EGRESO
);

/*==============================================================*/
/* Table: EGRESO                                                */
/*==============================================================*/
create table EGRESO (
   ID_EGRESO            SERIAL               not null,
   PERIODO              TEXT                 null,
   FACULTAD             TEXT                 null,
   DEPENDENCIA          TEXT                 null,
   DEPARTAMENTO         TEXT                 null,
   constraint PK_EGRESO primary key (ID_EGRESO)
);

/*==============================================================*/
/* Index: EGRESO_PK                                             */
/*==============================================================*/
create unique index EGRESO_PK on EGRESO (
ID_EGRESO
);

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
create table MENU (
   ID_MENU              SERIAL               not null,
   ID_PADRE             INT4                 null,
   NOMBRE_MENU          TEXT                 null,
   URL                  TEXT                 null,
   ESTADO_MENU          TEXT                 null,
   constraint PK_MENU primary key (ID_MENU)
);

/*==============================================================*/
/* Index: MENU_PK                                               */
/*==============================================================*/
create unique index MENU_PK on MENU (
ID_MENU
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL (
   ID_ROL               SERIAL               not null,
   ID_USUARIO           INT4                 null,
   NOMBRE_ROL           TEXT                 null,
   DESCRIPCION_ROL      TEXT                 null,
   ESTADO_ROL           TEXT                 null,
   constraint PK_ROL primary key (ID_ROL)
);

/*==============================================================*/
/* Index: ROL_PK                                                */
/*==============================================================*/
create unique index ROL_PK on ROL (
ID_ROL
);

/*==============================================================*/
/* Index: ROL_FK                                                */
/*==============================================================*/
create  index ROL_FK on ROL (
ID_USUARIO
);

/*==============================================================*/
/* Table: ROL_MENU                                              */
/*==============================================================*/
create table ROL_MENU (
   ID_MENU_ROL          SERIAL               not null,
   ID_ROL               INT4                 null,
   ID_MENU              INT4                 null,
   constraint PK_ROL_MENU primary key (ID_MENU_ROL)
);

/*==============================================================*/
/* Index: ROL_MENU_PK                                           */
/*==============================================================*/
create unique index ROL_MENU_PK on ROL_MENU (
ID_MENU_ROL
);

/*==============================================================*/
/* Index: ROL_MENU_FK                                           */
/*==============================================================*/
create  index ROL_MENU_FK on ROL_MENU (
ID_ROL
);

/*==============================================================*/
/* Index: ROL_MENU_FK2                                          */
/*==============================================================*/
create  index ROL_MENU_FK2 on ROL_MENU (
ID_MENU
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USUARIO           SERIAL               not null,
   USUARIO              TEXT                 null,
   NOMBRE_USUARIO       TEXT                 null,
   CLAVE                TEXT                 null,
   IDENTIFICACION_USUARIO TEXT                 null,
   EMAIL_USUARIO        TEXT                 null,
   ESTADO               TEXT                 null,
   ID_USUARIO_CREACION  INT4                 null,
   FECHA_CREACION       DATE                 null,
   ID_USUARIO_ACTUALIZACION INT4                 null,
   FECHA_ACTUALIZACION  DATE                 null,
   constraint PK_USUARIO primary key (ID_USUARIO)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
ID_USUARIO
);

alter table DETALLE_CATALOGO
   add constraint FK_DETALLE__CATALOGO__CATALOGO foreign key (ID_CATALOGO)
      references CATALOGO (ID_CATALOGO)
      on delete restrict on update restrict;

alter table DETALLE_EGRESO
   add constraint FK_DETALLE__EGRESO_DE_EGRESO foreign key (ID_EGRESO)
      references EGRESO (ID_EGRESO)
      on delete restrict on update restrict;

alter table ROL
   add constraint FK_ROL_USUARIO_R_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table ROL_MENU
   add constraint FK_ROL_MENU_MENU_ROLE_MENU foreign key (ID_MENU)
      references MENU (ID_MENU)
      on delete restrict on update restrict;

alter table ROL_MENU
   add constraint FK_ROL_MENU_ROL_MENUS_ROL foreign key (ID_ROL)
      references ROL (ID_ROL)
      on delete restrict on update restrict;

