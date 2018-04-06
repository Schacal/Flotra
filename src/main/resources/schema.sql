DROP TABLE IF EXISTS ASDFFF;
DROP TABLE IF EXISTS "USER";

CREATE TABLE ASDFFF (
  ID INT PRIMARY KEY NOT NULL
);

create table if not exists "USER"
(
  id serial              not null
    constraint "USER_pkey"
    primary key,
  user_name varchar(100) not null,
  password  varchar(200) not null,
  email     varchar(100) not null
);
