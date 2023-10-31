create table Person(
                       user_id int GENERATED by default as identity primary key,
                       name varchar unique not null,
                       birth_year int check ( birth_year>1900 )
);

create table Book(
                     book_id int generated by default as identity primary key ,
                     name varchar(128) not null unique ,
                     author varchar(128) not null,
                     user_id int references Person (user_id) on delete set null ,
                     year int not null
);

insert into book (name, author, user_id, year) VALUES ('How to be good in sex','Mahadi',4,2000);
insert into book (name, author, user_id, year) VALUES ('Mahadi - Truly story about the biggest cock','Vazed',4,2012);
insert into book (name, author, user_id, year) VALUES ('Business for beginners ','Biloa',5,1400);
insert into book (name, author, user_id, year) VALUES ('How to dress fancy','Mihail SmuroV',6,1988);


insert into person(name, birth_year) VALUES ('Mihail',2002);
insert into person(name, birth_year) VALUES ('Masha',2002);
insert into person(name, birth_year) VALUES ('OleG',2002);

delete  from  person;
delete from book;

select * from person;

select * from book ;

drop  table book;
drop table person;