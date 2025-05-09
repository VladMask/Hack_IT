create table user_teams (
  user_id bigint references users(id),
  team_id bigint references teams(id),
  is_leader bool not null default false,
  primary key (user_id, team_id)
);

--rollback drop table user_teams;