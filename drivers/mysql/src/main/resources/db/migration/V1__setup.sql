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

create table product(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    name varchar(200) not null,
    description text,
    price numeric(12,2) not null,
    image text not null,
    category enum('COMBO', 'SANDWICH', 'BEVERAGE', 'DESSERT', 'SIDE_DISH') not null,
    enabled boolean not null default true,
    created datetime not null,
    last_updated datetime not null,
    version integer not null
);

create table combo(
    id bigint not null primary key,
    sandwich_id bigint not null,
    beverage_id bigint not null,
    side_dish_id  bigint not null,
    constraint uk_combo_items unique (sandwich_id, beverage_id, side_dish_id),
    constraint fk_combo_id foreign key (id) references product(id),
    constraint fk_sandwich_id foreign key (sandwich_id) references product(id),
    constraint fk_beverage_id foreign key (beverage_id) references product(id),
    constraint fk_side_dish_id foreign key (side_dish_id) references product(id)
);

create table purchase(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    customer_id bigint,
    status enum('PAID', 'MAKING', 'PREPARED', 'DELIVERED', 'FINISHED') not null default 'PAID',
    date date not null default (curdate()),
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_customer_id foreign key (customer_id) references customer(id)
);

create table purchase_item(
    purchase_id bigint not null,
    product_id bigint not null,
    quantity integer not null,
    price numeric(12,2),
    discount numeric(5,2),
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_product_id foreign key (product_id) references product(id),
    constraint fk_purchase_id foreign key (purchase_id) references  purchase(id),
    constraint pk_purchase_item primary key (purchase_id, product_id)
);

create table payment(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    purchase_id bigint not null,
    method enum('MERCADO_PAGO') not null,
    date date not null default (curdate()),
    status enum('CREATED', 'PAID', 'ERROR') not null default 'PAID',
    amount numeric(12,2) not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_payment_purchase_id foreign key (purchase_id) references purchase(id)
);