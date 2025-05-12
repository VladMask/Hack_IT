create table applications (
  id bigserial primary key,
  hackathon_id bigint not null references hackathons(id) on delete cascade,
  team_id bigint not null references teams(id) on delete cascade,
  project_description text not null,
  submitted_at timestamp not null,
  is_accepted bool not null default false
);

--rollback drop table applications;