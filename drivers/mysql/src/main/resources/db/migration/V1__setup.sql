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

create table customer_data_removal(
     id bigint auto_increment not null primary key,
     uuid varchar(36) not null unique,
     customer_id bigint not null unique,
     status enum('PENDING', 'ERROR', 'FINISHED') not null,
     requested datetime not null,
     created datetime not null,
     last_updated datetime not null,
     version integer not null
);

create table customer_data_removal_item(
    removal_id bigint not null,
    uuid varchar(36) not null unique,
    application varchar(100) not null,
    status enum('PENDING', 'ERROR', 'FINISHED') not null,
    requested datetime not null,
    foreign key (removal_id) references customer_data_removal(id)
);
