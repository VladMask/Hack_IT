create table user_notifications (
  user_id bigint references users(id),
  notification_id bigint references notifications(id),
  primary key (user_id, notification_id)
);

--rollback drop table user_notifications;