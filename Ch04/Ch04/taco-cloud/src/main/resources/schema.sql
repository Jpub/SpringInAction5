drop table if exists users;
drop table if exists authorities;
drop index if exists ix_auth_username;

create table if not exists users(
    username varchar2(50) not null primary key,
    password varchar2(50) not null,
    enabled char(1) default '1');

create table if not exists authorities (
    username varchar2(50) not null,
    authority varchar2(50) not null,
    constraint fk_authorities_users
        foreign key(username) references users(username));

create unique index ix_auth_username 
    on authorities (username, authority);
