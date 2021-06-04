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

alter table estudiante add column fecha_nacimiento timestamp not null after total_creditos;
update estudiante set fecha_nacimiento = '2000-08-08' where id in (1,2,3,4,5,6,7,8,9, 10);
select * from estudiante;

-- PROYECTO 

-- ///////////////////////////////// STUDENT PROCEDURES //////////////////////////////////////////
drop procedure if exists all_students;
delimiter $$
create procedure all_students()
begin
	select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante;
end
$$
delimiter ;




drop procedure if exists student_by_id;
delimiter $$
create procedure student_by_id(in student_id int)
begin
	select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante where id = student_id;
end
$$
delimiter ;



drop procedure if exists add_student;
delimiter $$
create procedure add_student(in student_id int, student_name text, student_apellido text, student_fecha_nacimiento timestamp, student_total_creditos int)
begin
	start transaction;
    insert into estudiante (id, nombre, apellido, fecha_nacimiento, total_creditos) values (student_id, student_name, student_apellido, student_fecha_nacimiento, student_total_creditos);
    commit;
end
$$
delimiter ;




drop procedure if exists update_student;
delimiter $$
create procedure update_student(in student_id int, student_name text, student_apellido text, student_fecha_nacimiento timestamp, student_total_creditos int)
begin
	start transaction;
    update estudiante set nombre = student_name, apellido = student_apellido, fecha_nacimiento = student_fecha_nacimiento, total_creditos = student_total_creditos where id = student_id;
    commit;
end
$$
delimiter ;






drop procedure if exists delete_student;
delimiter $$
create procedure delete_student(in student_id int)
begin
	start transaction;
    delete from estudiante where id = student_id;
    commit;
end
$$
delimiter ;




drop procedure if exists find_by_last_name_student;
delimiter $$
create procedure find_by_last_name_student(in student_apellido text)
begin
	start transaction;
    select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante where apellido = student_apellido;
    commit;
end
$$
delimiter ;




drop procedure if exists order_by_last_name_student;
delimiter $$
create procedure order_by_last_name_student()
begin
	start transaction;
    select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante order by apellido asc;
    commit;
end
$$
delimiter ;

-- ///////////////////////////////// COURSES PROCEDURES //////////////////////////////////////////

drop procedure if exists all_courses;
delimiter $$
create procedure all_courses()
begin
	select id, nombre, creditos, departamento from curso;
end
$$
delimiter ;


drop procedure if exists course_by_id;
delimiter $$
create procedure course_by_id(in course_id int)
begin
	select id, nombre, creditos, departamento from curso where id = course_id;
end
$$
delimiter ;



drop procedure if exists add_course;
delimiter $$
create procedure add_course(in course_id int, course_name text, course_creditos int, course_department text)
begin
	start transaction;
    insert into curso (id, nombre, creditos, departamento) values (course_id, course_name, course_creditos, course_department);
    commit;
end
$$
delimiter ;




drop procedure if exists update_course;
delimiter $$
create procedure update_course(in course_id int, course_name text, course_creditos int, course_department text)
begin
	start transaction;
    update curso set nombre = course_name, creditos = course_creditos, departamento = course_department where id = course_id;
    commit;
end
$$
delimiter ;






drop procedure if exists delete_course;
delimiter $$
create procedure delete_course(in course_id int)
begin
	start transaction;
    delete from curso where id = course_id;
    commit;
end
$$
delimiter ;




drop procedure if exists find_by_department_course;
delimiter $$
create procedure find_by_department_course(in course_departamento text)
begin
    select id, nombre, creditos, departamento from curso where departamento = course_departamento;
end
$$
delimiter ;



-- ///////////////////////////////// PROFESSOR PROCEDURES //////////////////////////////////////////

drop procedure if exists all_professors;
delimiter $$
create procedure all_professors()
begin
	select id, nombre, apellido, ciudad from profesor;
end
$$
delimiter ;


drop procedure if exists professor_by_id;
delimiter $$
create procedure professor_by_id(in professor_id int)
begin
	select id, nombre, apellido, ciudad from profesor where id = professor_id;
end
$$
delimiter ;



drop procedure if exists add_professor;
delimiter $$
create procedure add_professor(in professor_id int, professor_name text, professor_apellido text, professor_ciudad text)
begin
	start transaction;
    insert into profesor (id, nombre, apellido, ciudad) values (professor_id, professor_name, professor_apellido, professor_ciudad);
    commit;
end
$$
delimiter ;




drop procedure if exists update_professor;
delimiter $$
create procedure update_professor(in professor_id int, professor_name text, professor_apellido text, professor_ciudad text)
begin
	start transaction;
    update profesor set nombre = professor_name, apellido = professor_apellido, ciudad = professor_ciudad where id = professor_id;
    commit;
end
$$
delimiter ;






drop procedure if exists delete_professor;
delimiter $$
create procedure delete_professor(in professor_id int)
begin
	start transaction;
    delete from profesor where id = professor_id;
    commit;
end
$$
delimiter ;




drop procedure if exists find_by_city_professor;
delimiter $$
create procedure find_by_city_professor(in professor_ciudad text)
begin
    select id, nombre, apellido, ciudad from profesor where ciudad = professor_ciudad;
end
$$
delimiter ;

