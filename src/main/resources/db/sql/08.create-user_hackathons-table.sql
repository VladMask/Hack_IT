create table user_hackathons (
  user_role_id bigint references user_roles(id),
  hackathon_id bigint references hackathons(id),
  primary key (user_role_id, hackathon_id)
);

--rollback drop table user_hackathons;