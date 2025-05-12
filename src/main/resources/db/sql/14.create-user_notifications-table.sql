create table user_notifications (
  user_id bigint references users(id) on delete cascade,
  notification_id bigint references notifications(id) on delete cascade,
  primary key (user_id, notification_id)
);

--rollback drop table user_notifications;