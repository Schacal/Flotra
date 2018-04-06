create table if not exists "USER"
(
  id        serial       not null
    constraint "USER_pkey"
    primary key,
  user_name varchar(255) not null,
  password  varchar(255) not null,
  email     varchar(255)
);

-- create unique index user_id_uindex
--   on "USER" (id);

-- create unique index   user_user_name_uindex
--   on "USER" (user_name);

-- create unique index  user_email_uindex
--   on "USER" (email);