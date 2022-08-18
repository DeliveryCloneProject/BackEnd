create table delivery_user (
    id bigint not null auto_increment,
    phone varchar(11) not null,
    nickname varchar(50) unique not null,
    password varchar(500) not null,
    status varchar(10) not null,
    type varchar(10) not null,
    created_at date default sysdate() not null,
     primary key (id)
);