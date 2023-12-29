create table customer(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    document varchar(14) not null unique,
    name varchar(200) not null,
    email varchar(255) not null,
    enabled boolean not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null
);