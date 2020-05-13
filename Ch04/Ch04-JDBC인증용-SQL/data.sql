insert into users (username, password) values ('user1', 'password1');
insert into users (username, password) values ('user2', 'password2');

insert into authorities (username, authority)
    values ('user1', 'ROLE_USER');
insert into authorities (username, authority)
    values ('user2', 'ROLE_USER');

commit;