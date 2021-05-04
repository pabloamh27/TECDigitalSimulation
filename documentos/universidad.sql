-- crear base de datos

show databases;

-- bases de datos

drop database if exists universidad;
create database universidad;
use universidad;


drop table if exists departamento;
drop table if exists profesor;
drop table if exists estudiante;
drop table if exists tutor;

-- tablas

create table departamento(
	nombre varchar(20),
    edificio varchar(20),
    presupuesto int,
    primary key (nombre)
);

create table curso(
	id int,
    nombre varchar(60),
	creditos int,
    departamento varchar(40),
    primary key (id),
	foreign key (departamento) references departamento(nombre)
);

create table profesor(
	id int primary key,
    nombre varchar(30),
    apellido varchar(30),
	ciudad varchar(100)
);


create table estudiante(
	id int primary key,
    nombre varchar(30),
    apellido varchar(30),
    total_creditos int check (total_creditos > 0)
);

create table tutor(
	estudiante_id int,
    profesor_id int, 
	foreign key(estudiante_id) references estudiante(id),
    foreign key(profesor_id) references profesor(id)
);


-- indices
create index estudianteApellidoIndex on estudiante(apellido);
create index profesorApellidoIndex on profesor(apellido);


-- ingresar datos

insert into departamento(nombre, edificio, presupuesto) values ('Computacion', 'Exactas', 120000);
insert into departamento(nombre, edificio, presupuesto) values ('Biologia', 'Ciencias', 100000);
insert into departamento(nombre, edificio, presupuesto) values ('Quimica', 'Exactas', 110000);
insert into departamento(nombre, edificio, presupuesto) values ('Ing Civil', 'Ingenieria', 120000);
insert into departamento(nombre, edificio, presupuesto) values ('Filosofia', 'Sociales', 0);
insert into departamento(nombre, edificio, presupuesto) values ('Fisica', 'Exactas', 0);

update departamento set presupuesto = 90 where nombre = 'Filosofia';


-- ingresar cursos

insert into curso(id, nombre, creditos, departamento) values (1, 'Bases de datos 1', 4, 'Computacion');
insert into curso(id, nombre, creditos, departamento) values (2, 'Analisis de algoritmos', 4, 'Computacion');
insert into curso(id, nombre, creditos, departamento) values (3, 'POO', 3, 'Computacion');
insert into curso(id, nombre, creditos, departamento) values (4, 'Estructuras de datos', 4, 'Computacion');
insert into curso(id, nombre, creditos, departamento) values (5, 'Inteligencia Artificial', 5, 'Computacion');


-- ingresar profesores

insert into profesor(id, nombre, apellido, ciudad) values(1, 'Martin', 'Flores', 'Heredia');
insert into profesor(id, nombre, apellido, ciudad) values(2, 'Allan', 'Cascante', 'Alajuela');
insert into profesor(id, nombre, apellido, ciudad) values(3, 'Deyton', 'Hernandez', 'Pokeparada');
insert into profesor(id, nombre, apellido, ciudad) values(4, 'Travis', 'Scott', 'Hyrule');
insert into profesor(id, nombre, apellido, ciudad) values(5, 'Costa', 'Rebel', 'Isla del coco');



-- ingresa estudiantes

insert into estudiante(id, nombre, apellido, total_creditos) values(1, 'Steven', 'Alvarado', 8);
insert into estudiante(id, nombre, apellido, total_creditos) values(2, 'Lermith', 'Biarreta', 8);
insert into estudiante(id, nombre, apellido, total_creditos) values(3, 'Maria', 'Biarreta', 8);
insert into estudiante(id, nombre, apellido, total_creditos) values(4, 'Valeria', 'Calderon', 12);
insert into estudiante(id, nombre, apellido, total_creditos) values(5, 'Sebastian', 'Campos', 4);
insert into estudiante(id, nombre, apellido, total_creditos) values(6, 'Josue', 'Castro', 11);
insert into estudiante(id, nombre, apellido, total_creditos) values(7, 'Susana', 'Cen', 16);
insert into estudiante(id, nombre, apellido, total_creditos) values(8, 'Johan', 'Echeverria', 8);
insert into estudiante(id, nombre, apellido, total_creditos) values(9, 'Junior', 'Herrera', 8);
insert into estudiante(id, nombre, apellido, total_creditos) values(10, 'Alejandro', 'Loaiza', 4);

-- tutor

insert into tutor(estudiante_id, profesor_id) values(1, 2);
insert into tutor(estudiante_id, profesor_id) values(8, 2);
insert into tutor(estudiante_id, profesor_id) values(3, 3);



select * from departamento;
select * from profesor;
select * from estudiante;
select * from tutor;

-- alter table
alter table estudiante add column fecha_nacimiento timestamp not null after total_creditos;

-- updates

update estudiante set fecha_nacimiento = '2000-08-08' where id in (2,3,4,5,6,7,8,9, 10);


select nombre, apellido from estudiante where apellido like 'Biarreta';

select 
concat(e.nombre, ' ', e.apellido) as `estudiante`,
concat(p.nombre, ' ', p.apellido) as `profesor`
from 
estudiante as e 
join tutor as t on t.estudiante_id = e.id 
join profesor as p on profesor_id = p.id;


select e.nombre, e.apellido
from 
estudiante e 
join tutor t on e.id = t.estudiante_id
join profesor p on p.id = t.profesor_id
where
p.nombre = 'Allan'
;

select *
from 
estudiante e 
join tutor t on e.id = t.estudiante_id
;

delete from tutor where estudiante_id = 1;
delete from estudiante where id = 1;

select current_date;

select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante where apellido = 'Biarreta';


