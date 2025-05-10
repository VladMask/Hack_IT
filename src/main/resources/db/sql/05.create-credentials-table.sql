create table credentials (
  id bigserial primary key,
  email varchar(60) not null,
  password varchar(256) not null,
  phone_number varchar(20),
  user_id bigint not null references users(id),
  created_at timestamp not null,
  updated_at timestamp not null
);

--rollback drop table credentials;