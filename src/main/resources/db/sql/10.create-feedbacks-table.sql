create table feedbacks (
  id bigserial primary key,
  content text not null,
  hackathon_id bigint not null references hackathons(id) on delete cascade,
  judge_id bigint not null references users(id) on delete cascade,
  solution_id bigint not null references solutions(id) on delete cascade,
  created_at timestamp not null,
  updated_at timestamp not null
);

--rollback drop table feedbacks;