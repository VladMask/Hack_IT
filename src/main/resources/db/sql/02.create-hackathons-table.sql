create table hackathons (
  id bigserial primary key,
  name varchar not null,
  description text not null,
  start_date timestamp not null,
  end_date timestamp not null,
  photos_url varchar,
  is_finished bool not null default false,
  registration_start timestamp not null,
  registration_end timestamp not null,
  development_start timestamp not null,
  development_end timestamp not null,
  assessment_start timestamp not null,
  assessment_end timestamp not null,
  result_announcement timestamp not null
);

--rollback drop table hackathons;