create table if not exists "USER"
(
  id serial              not null
    constraint "USER_pkey"
    primary key,
  user_name varchar(255) not null,
  password  varchar(255) not null,
  email     varchar(255)
);

-- create unique index if not exists user_id_uindex
--   on "USER" (id)
-- ;
--
-- create unique index if not exists user_user_name_uindex
--   on "USER" (user_name)
-- ;
--
-- create unique index if not exists user_email_uindex
--   on "USER" (email)
-- ;

create table if not exists car
(
  car_id serial                          not null
    constraint car_pkey
    primary key,
  license_plate_number           varchar(255),
  user_id                        integer not null
    constraint car_user_id_fk
    references "USER",
  vin_number                     bigint,
  insurance_start_date           date,
  insurance_end_date             date,
  technical_examination_end_date date
);

-- create unique index if not exists car_car_id_uindex
--   on car (car_id)
-- ;
--
-- create unique index if not exists car_license_plate_number_uindex
--   on car (license_plate_number)
-- ;

create table if not exists cardetails
(
  car_detail_id serial not null
    constraint car_details_pkey
    primary key,
  car_id       integer not null
    constraint car_details_car_car_id_fk
    references car,
  manufacturer varchar(255),
  model        varchar(255),
  engine       varchar(255),
  color        varchar(255),
  fuel_type    varchar(255),
  engine_power integer,
  doors_number integer,
  mileage      integer,
  equipment    varchar
);

-- create unique index if not exists car_details_car_detail_id_uindex
--   on cardetails (car_detail_id)
-- ;
--
-- create unique index if not exists car_details_car_id_uindex
--   on cardetails (car_id)
-- ;

create table if not exists images
(
  image_id serial      not null
    constraint images_pkey
    primary key,
  date         time,
  image        bytea,
  cardetailsid integer not null
    constraint images_cardetails_car_detail_id_fk
    references cardetails
);

-- create unique index if not exists images_image_id_uindex
--   on images (image_id)
-- ;

create table if not exists holder
(
  pesel_number bigint  not null
    constraint holder_pkey
    primary key,
  firstname    varchar not null,
  surname      varchar not null,
  telephone    bigint,
  car_id       integer not null
    constraint holder_car_car_id_fk
    references car
);

-- create unique index if not exists holder_pesel_number_uindex
--   on holder (pesel_number)
-- ;

create table if not exists repair
(
  repair_id serial    not null
    constraint repair_pkey
    primary key,
  car_id      integer not null
    constraint repair_car_car_id_fk
    references car,
  date        date    not null,
  description varchar
);

-- create unique index if not exists repair_repair_id_uindex
--   on repair (repair_id)
-- ;

create table if not exists company
(
  company_id serial   not null
    constraint company_pkey
    primary key,
  companyname varchar not null,
  website     varchar,
  user_id     integer not null
    constraint company_user_id_fk
    references "USER",
  address     varchar,
  telephone   bigint
);

-- create unique index if not exists company_company_id_uindex
--   on company (company_id)
-- ;

create table if not exists road_accidents
(
  id serial        not null
    constraint road_accidents_pkey
    primary key,
  date        date not null,
  holder_id   integer
    constraint road_accidents_holder_pesel_number_fk
    references holder,
  car_id      integer
    constraint road_accidents_car_car_id_fk
    references car,
  description varchar
);

-- create unique index if not exists road_accidents_id_uindex
--   on road_accidents (id)
-- ;
--
