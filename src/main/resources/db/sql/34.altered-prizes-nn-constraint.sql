alter table prizes
    alter column team_hackathon_id drop not null;

--rollback alter table prizes
--  alter column team_hackathon_id set not null;