drop table if exists client cascade;
drop table if exists mailing cascade;
drop table if exists message cascade;

drop sequence if exists client_sequence;
drop sequence if exists mailing_sequence;
drop sequence if exists message_sequence;

create sequence client_sequence start 1 increment 1;
create sequence mailing_sequence start 1 increment 1;
create sequence message_sequence start 1 increment 1;


create table mailing
(
    id         bigserial,
    code       varchar(255),
    start_date timestamp,
    end_date   timestamp,
    text       varchar(255),
    primary key (id)
);

create table client
(
    id           bigserial,
    code         varchar(255),
    phone_number varchar(255),
    tag          varchar(255),
    timezone     varchar(255),
    primary key (id),
    unique (phone_number)
);

create table message
(
    id         bigserial,
    created_at timestamp,
    client_id  int8,
    mailing_id int8,
    status     varchar(255),
    primary key (id)
);

alter table message
    add constraint FK_message_mailing
        foreign key (mailing_id)
            references mailing;

alter table message
    add constraint FK_message_client
        foreign key (client_id)
            references client;