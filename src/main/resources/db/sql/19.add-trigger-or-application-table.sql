CREATE OR REPLACE FUNCTION check_team_roles_on_application()
RETURNS TRIGGER AS $check_team_roles_on_application$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM user_teams ut
        JOIN user_roles ur ON ut.user_id = ur.user_id
        JOIN roles r ON ur.role_id = r.id
        JOIN user_hackathons uh ON uh.user_role_id = ur.id
        WHERE ut.team_id = NEW.team_id
          AND uh.hackathon_id = NEW.hackathon_id
          AND r.name IN ('JUDGE', 'HACKATHON_CREATOR')
    ) THEN
        RAISE EXCEPTION 'Cannot register: team contains a judge or hackathon creator for this hackathon.';
    END IF;
    RETURN NEW;
END;
$check_team_roles_on_application$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_team_roles
BEFORE INSERT ON applications
FOR EACH ROW
EXECUTE FUNCTION check_team_roles_on_application();

--rollback DROP TRIGGER IF EXISTS trg_check_team_roles ON applications;
--           DROP FUNCTION IF EXISTS check_team_roles_on_application;

