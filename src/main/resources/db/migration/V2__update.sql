insert into mailing(id, code, start_date, end_date, text)
values (nextval('mailing_sequence'), '+7', '04-04-2022', '05-04-2022', 'hello');

insert into client(id, code, phone_number, tag, timezone)
values (nextval('client_sequence'), '+7', '78005553535', '+7', 'Africa');

insert into message(id, created_at, client_id, mailing_id, status)
values (nextval('message_sequence'), '04-04-2022', 1, 1, 'OK');