create table solutions (
  id bigserial primary key,
  hackathon_id bigint not null references hackathons(id),
  team_id bigint not null references teams(id),
  submitted_at timestamp not null,
  is_git_repository bool not null,
  repository_url varchar,
  files_url varchar,
  total_score smallint default 0,
  checked_at timestamp
);

--rollback drop table solutions;