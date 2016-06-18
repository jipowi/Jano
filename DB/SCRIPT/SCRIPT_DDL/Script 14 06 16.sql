/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     14/06/2016 18:05:52                          */
/*==============================================================*/


/*==============================================================*/
/* Table: AFECTACION                                            */
/*==============================================================*/
create table AFECTACION (
   ID_AFECTACION        SERIAL               not null,
   DESC_AFECTACION      TEXT                 null,
   ID_FACULTAD          INT4                 null,
   ID_DEPENDENCIA       INT4                 null,
   constraint PK_AFECTACION primary key (ID_AFECTACION)
);

/*==============================================================*/
/* Index: AFECTACION_PK                                         */
/*==============================================================*/
create unique index AFECTACION_PK on AFECTACION (
ID_AFECTACION
);

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
/* Index: CATALOGO_DETALLECATALOGO_FK                           */
/*==============================================================*/
create  index CATALOGO_DETALLECATALOGO_FK on DETALLE_CATALOGO (
ID_CATALOGO
);

/*==============================================================*/
/* Table: DETALLE_EGRESO                                        */
/*==============================================================*/
create table DETALLE_EGRESO (
   ID_DET_EGRESO        SERIAL               not null,
   ID_EGRESO            INT4                 null,
   ID_PARTIDA           INT4                 null,
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
/* Index: EGRESO_DETALLE_FK                                     */
/*==============================================================*/
create  index EGRESO_DETALLE_FK on DETALLE_EGRESO (
ID_EGRESO
);

/*==============================================================*/
/* Index: PARTIDA_EGRESO_FK                                     */
/*==============================================================*/
create  index PARTIDA_EGRESO_FK on DETALLE_EGRESO (
ID_PARTIDA
);

/*==============================================================*/
/* Table: DETALLE_INGRESO                                       */
/*==============================================================*/
create table DETALLE_INGRESO (
   ID_DET_INGRESO       SERIAL               not null,
   ID_INGRESO           INT4                 null,
   ID_PARTIDA           INT4                 null,
   PRESUPUESTO_INGRESO  DECIMAL              null,
   constraint PK_DETALLE_INGRESO primary key (ID_DET_INGRESO)
);

/*==============================================================*/
/* Index: DETALLE_INGRESO_PK                                    */
/*==============================================================*/
create unique index DETALLE_INGRESO_PK on DETALLE_INGRESO (
ID_DET_INGRESO
);

/*==============================================================*/
/* Index: PARTIDA_INGRESO_FK                                    */
/*==============================================================*/
create  index PARTIDA_INGRESO_FK on DETALLE_INGRESO (
ID_PARTIDA
);

/*==============================================================*/
/* Index: INGRESO_DETALLE_FK                                    */
/*==============================================================*/
create  index INGRESO_DETALLE_FK on DETALLE_INGRESO (
ID_INGRESO
);

/*==============================================================*/
/* Table: EGRESO                                                */
/*==============================================================*/
create table EGRESO (
   ID_EGRESO            SERIAL               not null,
   ID_AFECTACION        INT4                 null,
   PERIODO              TEXT                 null,
   constraint PK_EGRESO primary key (ID_EGRESO)
);

/*==============================================================*/
/* Index: EGRESO_PK                                             */
/*==============================================================*/
create unique index EGRESO_PK on EGRESO (
ID_EGRESO
);

/*==============================================================*/
/* Index: AFECTACION_EGRESO_FK                                  */
/*==============================================================*/
create  index AFECTACION_EGRESO_FK on EGRESO (
ID_AFECTACION
);

/*==============================================================*/
/* Table: GASTOS                                                */
/*==============================================================*/
create table GASTOS (
   ID_GASTOS            SERIAL               not null,
   ID_AFECTACION        INT4                 null,
   ID_PARTIDA           INT4                 null,
   COMPROBANTE_GASTO    TEXT                 null,
   BENEFICIARIO_GASTO   TEXT                 null,
   FECHA_GASTO          DATE                 null,
   PERIODO_GASTO        TEXT                 null,
   OBS_GASTO            TEXT                 null,
   VALOR_GASTO          DECIMAL              null,
   CODIGO_GASTO			TEXT				 null,
   constraint PK_GASTOS primary key (ID_GASTOS)
);

/*==============================================================*/
/* Index: GASTOS_PK                                             */
/*==============================================================*/
create unique index GASTOS_PK on GASTOS (
ID_GASTOS
);

/*==============================================================*/
/* Index: AFECTACION_GASTO_FK                                   */
/*==============================================================*/
create  index AFECTACION_GASTO_FK on GASTOS (
ID_AFECTACION
);

/*==============================================================*/
/* Index: PARTIDA_GASTO_FK                                      */
/*==============================================================*/
create  index PARTIDA_GASTO_FK on GASTOS (
ID_PARTIDA
);

/*==============================================================*/
/* Table: INGRESO                                               */
/*==============================================================*/
create table INGRESO (
   ID_INGRESO           SERIAL               not null,
   ID_AFECTACION        INT4                 null,
   PERIODO_INGRESO      TEXT                 null,
   constraint PK_INGRESO primary key (ID_INGRESO)
);

/*==============================================================*/
/* Index: INGRESO_PK                                            */
/*==============================================================*/
create unique index INGRESO_PK on INGRESO (
ID_INGRESO
);

/*==============================================================*/
/* Index: AFECTACION_INGRESO_FK                                 */
/*==============================================================*/
create  index AFECTACION_INGRESO_FK on INGRESO (
ID_AFECTACION
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
/* Table: PARTIDA                                               */
/*==============================================================*/
create table PARTIDA (
   ID_PARTIDA           SERIAL               not null,
   PARTIDA              TEXT                 null,
   TIPO_PARTIDA         TEXT                 null,
   constraint PK_PARTIDA primary key (ID_PARTIDA)
);

/*==============================================================*/
/* Index: PARTIDA_PK                                            */
/*==============================================================*/
create unique index PARTIDA_PK on PARTIDA (
ID_PARTIDA
);

/*==============================================================*/
/* Table: RECAUDACION                                           */
/*==============================================================*/
create table RECAUDACION (
   ID_RECAUDACION       SERIAL               not null,
   ID_PARTIDA           INT4                 null,
   ID_AFECTACION        INT4                 null,
   COMPROBANTE          TEXT                 null,
   BENEFICIARIO         TEXT                 null,
   FECHA_RECAUDACION    DATE                 null,
   PERIDO_RECAUDACION   TEXT                 null,
   OBSERVACION          TEXT                 null,
   VALOR_RECAUDACION    DECIMAL              null,
   CODIGO_INGRESO		TEXT				 null,	
   constraint PK_RECAUDACION primary key (ID_RECAUDACION)
);

/*==============================================================*/
/* Index: RECAUDACION_PK                                        */
/*==============================================================*/
create unique index RECAUDACION_PK on RECAUDACION (
ID_RECAUDACION
);

/*==============================================================*/
/* Index: PARTIDA_RECAUDACION_FK                                */
/*==============================================================*/
create  index PARTIDA_RECAUDACION_FK on RECAUDACION (
ID_PARTIDA
);

/*==============================================================*/
/* Index: AFECTACION_RECAUDACION_FK                             */
/*==============================================================*/
create  index AFECTACION_RECAUDACION_FK on RECAUDACION (
ID_AFECTACION
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
/* Index: USUARIO_ROL_FK                                        */
/*==============================================================*/
create  index USUARIO_ROL_FK on ROL (
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
/* Index: ROL_MENUS_FK                                          */
/*==============================================================*/
create  index ROL_MENUS_FK on ROL_MENU (
ID_ROL
);

/*==============================================================*/
/* Index: MENU_ROLES_FK                                         */
/*==============================================================*/
create  index MENU_ROLES_FK on ROL_MENU (
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

alter table DETALLE_EGRESO
   add constraint FK_DETALLE__PARTIDA_E_PARTIDA foreign key (ID_PARTIDA)
      references PARTIDA (ID_PARTIDA)
      on delete restrict on update restrict;

alter table DETALLE_INGRESO
   add constraint FK_DETALLE__INGRESO_D_INGRESO foreign key (ID_INGRESO)
      references INGRESO (ID_INGRESO)
      on delete restrict on update restrict;

alter table DETALLE_INGRESO
   add constraint FK_DETALLE__PARTIDA_I_PARTIDA foreign key (ID_PARTIDA)
      references PARTIDA (ID_PARTIDA)
      on delete restrict on update restrict;

alter table EGRESO
   add constraint FK_EGRESO_AFECTACIO_AFECTACI foreign key (ID_AFECTACION)
      references AFECTACION (ID_AFECTACION)
      on delete restrict on update restrict;

alter table GASTOS
   add constraint FK_GASTOS_AFECTACIO_AFECTACI foreign key (ID_AFECTACION)
      references AFECTACION (ID_AFECTACION)
      on delete restrict on update restrict;

alter table GASTOS
   add constraint FK_GASTOS_PARTIDA_G_PARTIDA foreign key (ID_PARTIDA)
      references PARTIDA (ID_PARTIDA)
      on delete restrict on update restrict;

alter table INGRESO
   add constraint FK_INGRESO_AFECTACIO_AFECTACI foreign key (ID_AFECTACION)
      references AFECTACION (ID_AFECTACION)
      on delete restrict on update restrict;

alter table RECAUDACION
   add constraint FK_RECAUDAC_AFECTACIO_AFECTACI foreign key (ID_AFECTACION)
      references AFECTACION (ID_AFECTACION)
      on delete restrict on update restrict;

alter table RECAUDACION
   add constraint FK_RECAUDAC_PARTIDA_R_PARTIDA foreign key (ID_PARTIDA)
      references PARTIDA (ID_PARTIDA)
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

