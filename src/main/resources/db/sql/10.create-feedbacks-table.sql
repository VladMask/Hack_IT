create table feedbacks (
  id bigserial primary key,
  content text not null,
  hackathon_id bigint not null references hackathons(id),
  judge_id bigint not null references users(id),
  solution_id bigint not null references solutions(id),
  created_at timestamp not null,
  updated_at timestamp not null
);

--rollback drop table feedbacks;