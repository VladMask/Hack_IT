create table scores (
  solution_id bigint references solutions(id),
  judge_id bigint references users(id),
  value smallint not null,
  primary key (solution_id, judge_id)
);

--rollback drop table scores;