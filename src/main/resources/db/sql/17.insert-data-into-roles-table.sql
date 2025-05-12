insert into roles (name)
values
  ('USER'),
  ('JUDGE'),
  ('HACKATHON_CREATOR'),
  ('ADMIN');

--rollback truncate table roles;