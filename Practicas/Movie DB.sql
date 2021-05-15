drop schema if exists movies;

create schema if not exists movies default character set utf8;
use movies;

drop table if exists movie;
drop table if exists category;
drop table if exists users;
drop table if exists rating;

create table if not exists category(
	id int not null auto_increment,
    category_name varchar(100) not null,
    primary key(id)
);

create unique index category_name_idx on category (category_name asc);


create table if not exists movie(
	id int not null auto_increment,
    title varchar(100) not null,
    release_date datetime,
    category_id int not null,
    primary key(id),
    constraint category_fk foreign key (category_id) references category(id)
);

create unique index title_idx on movie (title asc);


create table if not exists users(
	id int not null auto_increment,
    user_name varchar(100) not null,
    first_name varchar(100),
    last_name varchar(100),
    primary key(id)
);

alter table users add fulltext(first_name);
alter table users add fulltext(last_name);

create table if not exists rating(
	movie_id int not null,
    score int not null,
    review varchar(200),
    user_id int not null,
    constraint movie_fk foreign key (movie_id) references movie(id),
	constraint user_fk foreign key (user_id) references users(id)
);

create index movie_idx on rating (movie_id asc);
create index user_idx on rating (user_id asc);

insert into category(category_name) values ('Fantasia');
insert into category(category_name) values ('Sci-Fi');
insert into category(category_name) values ('Anime/Animada');
insert into category(category_name) values ('Documental');
insert into category(category_name) values ('Belica');

insert into users(user_name, first_name, last_name) values ('Puvlo','Pablo','Munoz');
insert into users(user_name, first_name, last_name) values ('Elgatostream','Max','Hidalgo');
insert into users(user_name, first_name, last_name) values ('Cirilla','La gata','de Pablo');
insert into users(user_name, first_name, last_name) values ('Monstro Dixon','Taylor','Deyton');
insert into users(user_name, first_name, last_name) values ('Rody506','Rodolfo','El Reno');

insert into movie(title, release_date, category_id) values ('Interstellar','2018-01-05',2);
insert into movie(title, release_date, category_id) values ('Pacific Rim','2020-02-07',2);
insert into movie(title, release_date, category_id) values ('Kimetsu No Yaiba Mugen Train','2000-05-24',3);
insert into movie(title, release_date, category_id) values ('Bambi','2015-08-25',3);
insert into movie(title, release_date, category_id) values ('1917','2006-12-30',5);

insert into rating(movie_id, user_id, score, review) values (3,1,5,'The best of the decade');
insert into rating(movie_id, user_id, score, review) values (2,5,3,'Good but not the best');
insert into rating(movie_id, user_id, score, review) values (3,1,4,'Emotional movie, good series');
insert into rating(movie_id, user_id, score, review) values (4,4,2,'Overrated');
insert into rating(movie_id, user_id, score, review) values (5,1,4,'Super cinematography and perfect montage');

-- Punto A:

select c.id, c.category_name from category as c left join movie as m on c.id = m.category_id where m.category_id is null;  

-- Punto B:

select c.id, c.category_name, count(r.movie_id = m.id) from category as c 
inner join movie as m on c.id = m.category_id 
inner join rating as r on m.id = r.movie_id
group by c.id;

-- Punto C:

select category_name from category as c
inner join movie as m on c.id = m.category_id
inner join rating as r on m.id = r.movie_id
where r.user_id = 1;

-- Punto D:

select * from movie as m left join rating as r on m.id = r.movie_id where r.movie_id is null;






