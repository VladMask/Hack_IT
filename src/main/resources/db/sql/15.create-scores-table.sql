create table scores (
  solution_id bigint references solutions(id) on delete cascade,
  judge_id bigint references users(id) on delete cascade,
  value smallint not null,
  primary key (solution_id, judge_id)
);

--rollback drop table scores;