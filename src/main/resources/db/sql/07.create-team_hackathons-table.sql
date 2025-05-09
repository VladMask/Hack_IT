create table team_hackathons (
  id bigserial unique not null,
  team_id bigint references teams(id),
  hackathon_id bigint references hackathons(id),
  is_winner bool not null default false,
  place int default -1,
  primary key (team_id, hackathon_id)
);

--rollback drop table team_hackathons;