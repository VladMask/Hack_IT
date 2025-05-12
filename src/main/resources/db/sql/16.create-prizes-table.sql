create table prizes (
  id bigserial primary key,
  name varchar not null,
  quantity int not null,
  hackathon_id bigint not null references hackathons(id) on delete cascade,
  team_hackathon_id bigint not null references team_hackathons(id) on delete cascade
);

--rollback drop table prizes;