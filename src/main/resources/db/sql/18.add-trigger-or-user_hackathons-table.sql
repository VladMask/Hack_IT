CREATE FUNCTION verify_user_role_in_user_hackathons()
RETURNS TRIGGER AS $verify_user_role_in_user_hackathons$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM user_roles
        WHERE user_id = NEW.user_id AND role_id = NEW.role_id
    ) THEN
        RAISE EXCEPTION 'User % does not have role %', NEW.user_id, NEW.role_id;
    END IF;

    RETURN NEW;
END;
$verify_user_role_in_user_hackathons$ LANGUAGE plpgsql;

CREATE TRIGGER trg_verify_user_role
BEFORE INSERT ON user_hackathons
FOR EACH ROW
EXECUTE FUNCTION verify_user_role_in_user_hackathons();


--rollback DROP TRIGGER IF EXISTS trg_verify_user_not_judge_or_creator ON team_hackathons;
--rollback DROP FUNCTION IF EXISTS verify_user_role_in_user_hackathons();
