create table users (
  id bigserial primary key,
  firstname varchar(20) not null,
  lastname varchar(20) not null,
  username varchar(20) unique not null,
  avatar_url varchar(255) default null,
  is_active bool not null default false,
  created_at timestamp not null,
  updated_at timestamp not null
);

--rollback drop table users;