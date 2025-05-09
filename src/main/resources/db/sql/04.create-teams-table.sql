create table teams (
  id bigserial primary key,
  name varchar(255) not null,
  is_active bool not null default false
);

--rollback drop table teams;