create table user_roles (
  id bigserial unique not null,
  role_id bigint references roles(id) on delete cascade,
  user_id bigint references users(id) on delete cascade,
  primary key (role_id, user_id)
);

--rollback drop table user_roles;