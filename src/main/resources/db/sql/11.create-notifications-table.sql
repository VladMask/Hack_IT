create table notifications (
  id bigserial primary key,
  user_id bigint not null,
  title varchar not null,
  content text not null,
  hackathon_id bigint not null references hackathons(id)  on delete cascade
);

--rollback drop table notifications;