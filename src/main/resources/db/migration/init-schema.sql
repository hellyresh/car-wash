create table if not exists bills
(
    id         bigserial
        primary key,
    date       timestamp      not null,
    offer_name varchar(20),
    order_id   bigint         not null,
    price      numeric(19, 2) not null,
    user_id    bigint         not null
);

alter table bills
    owner to postgres;

create table if not exists boxes
(
    id               bigserial
        primary key,
    close_time       time default '22:00:00'::time without time zone,
    open_time        time default '10:00:00'::time without time zone,
    time_coefficient double precision not null
        constraint boxes_time_coefficient_check
            check (time_coefficient <= (5)::double precision)
);

alter table boxes
    owner to postgres;

create table if not exists offers
(
    id       bigserial
        primary key,
    duration integer        not null,
    name     varchar(30),
    price    numeric(19, 2) not null
);

alter table offers
    owner to postgres;

create table if not exists users
(
    id         bigserial
        primary key,
    first_name varchar(20),
    last_name  varchar(20),
    password   varchar(80),
    role       varchar(10),
    username   varchar(20)
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to postgres;

create table if not exists operators
(
    id           bigserial
        primary key,
    max_discount integer
        constraint operators_max_discount_check
            check ((max_discount <= 100) AND (max_discount >= 0)),
    min_discount integer
        constraint operators_min_discount_check
            check ((min_discount <= 100) AND (min_discount >= 0)),
    box_id       bigint
        constraint fk32uaagbwaw165nhbq5l13y9u2
            references boxes,
    user_id      bigint not null
        constraint fkj7p7gt1xe75lomlqfi0xw3mbx
            references users
);

alter table operators
    owner to postgres;

create unique index if not exists operators_user_id_uindex
    on operators (user_id);

create unique index if not exists operators_box_id_uindex
    on operators (box_id);

create table if not exists orders
(
    id        bigserial
        primary key,
    date_time timestamp,
    discount  integer,
    duration  integer,
    price     numeric(19, 2) not null,
    status    varchar(15),
    box_id    bigint         not null
        constraint fk1cci2xp4x6vjsrgipxr7fa4eu
            references boxes,
    offer_id  bigint         not null
        constraint fkfa6ca42wke9qjplj694yo21wc
            references offers,
    user_id   bigint         not null
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

alter table orders
    owner to postgres;
