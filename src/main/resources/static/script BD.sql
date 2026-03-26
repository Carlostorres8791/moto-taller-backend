use moto_taller_db;

select * from taller;

use moto_taller_db;
-- ===================================
-- TABLA ROLES
-- ===================================
create table rol(
	id int auto_increment primary key,
    nombre varchar(50) not null unique
);

-- ==========================================
-- Tabla Taller
-- ==========================================
create table taller (
	id int auto_increment primary key,
    nombre varchar(150) not null,
    direccion varchar(200) not null,
    telefono varchar(50) not null,
    activo boolean default true,
    fecha_registro timestamp default current_timestamp
);

alter table taller rename column ind to id;

-- ============================================
-- Tabla Usuario
-- ============================================
create table usuario (
	id int auto_increment primary key,
    nombre varchar(150) not null,
    email varchar(150) not null,
    password varchar(255) not null,
    activo boolean default true,
    rol_id int not null,
    taller_id int not null,
    unique(email, taller_id),
    foreign key(rol_id) references rol(id),
    foreign key(taller_id) references taller(id)
);

-- ============================================
-- Tabla Cliente
-- ============================================
create table cliente(
	id int auto_increment primary key,
    nombre varchar(150) not null,
    telefono varchar(50) not null,
    documento varchar(50) not null,
    taller_id int not null,
    foreign key (taller_id) references taller(id)
);

-- =============================================
-- Tabla Moto
-- =============================================
create table moto(
	id int auto_increment primary key,
    placa varchar(20) not null,
    marca varchar(100),
    modelo varchar(100),
    cliente_id int not null,
    taller_id int not null,
    foreign key (cliente_id) references cliente(id),
    foreign key (taller_id) references taller(id),
    unique(placa, taller_id)
);

-- ================================================
-- Tabla Servicio
-- ================================================
create table servicio(
	id int auto_increment primary key,
    nombre varchar(150) not null,
    descripcion varchar(255),
    precio_base decimal(10,2) not null,
    activo boolean default true,
    taller_id int not null,
    foreign key (taller_id) references taller(id)
);

-- =================================================
-- Tabla Repuesto
-- =================================================
create table repuesto(
	id int auto_increment primary key,
    nombre varchar(150) not null,
    precio_unitario decimal(10,2)not null,
    activo boolean default true,
    taller_id int not null,
    foreign key (taller_id) references taller(id)
);

-- ================================================
-- Tabla Estado_Orden
-- ================================================
create table estado_orden(
	id int auto_increment primary key,
    nombre varchar(50) not null unique
);

insert into estado_orden (nombre) values ('PENDIENTE'),('EN_PROCESO'),('LISTA'),('ENTREGADA'),('CANCELADA');

-- ===============================================
-- Tabla Orden_Trabajo
-- ===============================================
create table orden_trabajo(
	id int auto_increment primary key,
    codigo varchar(50) not null unique,
    fecha_ingreso timestamp default current_timestamp,
    fecha_actualizacion timestamp default current_timestamp on update current_timestamp,
    total_final decimal(10,2) default 0,
    moto_id int not null,
    tecnico_id int not null,
    estado_id int not null,
    taller_id int not null,
    foreign key (moto_id) references moto(id),
    foreign key (tecnico_id) references usuario(id),
    foreign key (estado_id) references estado_orden(id),
    foreign key (taller_id) references taller(id)
);

 -- ==================================================
 -- Tabla Orden_Servicio
 -- ==================================================
create table orden_servicio(
	id int auto_increment primary key,
    orden_id int not null,
    servicio_id int not null,
    precio_aplicado decimal(10,2) not null,
    foreign key (orden_id) references orden_trabajo(id),
    foreign key (servicio_id) references servicio(id)
);

-- ====================================================
-- Tabla Orden_Repuesto
-- ====================================================
create table orden_repuesto(
	id int auto_increment primary key,
    orden_id int not null,
    repuesto_id int not null,
    cantidad int not null,
    precio_aplicado decimal(10,2) not null,
    foreign key (orden_id) references orden_trabajo(id),
    foreign key (repuesto_id) references repuesto(id)
);

-- ====================================================
-- INDICES
-- ====================================================
create index idx_usuario_taller on usuario(taller_id);
CREATE INDEX idx_cliente_taller ON cliente(taller_id);
CREATE INDEX idx_moto_taller ON moto(taller_id);
CREATE INDEX idx_servicio_taller ON servicio(taller_id);
CREATE INDEX idx_repuesto_taller ON repuesto(taller_id);
CREATE INDEX idx_orden_taller ON orden_trabajo(taller_id);
CREATE INDEX idx_orden_estado ON orden_trabajo(estado_id);

-- CLIENTE
CREATE UNIQUE INDEX idx_cliente_doc_taller
ON cliente(documento, taller_id);

-- ORDEN
CREATE UNIQUE INDEX idx_orden_codigo
ON orden_trabajo(codigo);

-- FILTROS FRECUENTES
CREATE INDEX idx_orden_taller_estado
ON orden_trabajo(taller_id, estado_id);

show tables ;
select * from rol;
select * from taller;
use moto_taller_db;
select * from usuario;
select * from cliente;
select * from orden_trabajo;

select * from rol;
select * from cliente;
show tables;

insert into rol (nombre) values ('SUPER_ADMIN'), ('ADMIN_TALLER'), ('TECNICO'), ('RECEPCIONISTA'), ('CLIENTE');

alter table usuario modify taller_id int null;
insert into usuario (nombre, email, password, activo, rol_id, taller_id) values ('Super Admin', 'escolta87@hotmail.com', '$2a$10$PODSq7NXbYGO6NDEJ3vEbOKjBgsKoIeCT8Xshd8FihiaF2ecMU/my', true, 1, NULL);