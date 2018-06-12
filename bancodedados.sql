create database agenda_reuniao;



use agenda_reuniao;


CREATE TABLE agenda_reuniao(

id bigint auto_increment,

nome varchar(255),

dataMesAno DATE,

horaInicio TIME,

horaFinal TIME,

sala varchar(255),

observacao varchar(255),

primary key(id)
);



create table horario(

horarioInicio time,

horarioFinal time,

primary key(horarioInicio)
);





insert into horario values ('7:00', '7:30');

insert into horario values ('7:30', '8:00');

insert into horario values ('8:00', '8:30');

insert into horario values ('8:30', '9:00');

insert into horario values ('9:00', '9:30');

insert into horario values ('9:30', '10:00');

insert into horario values ('10:00', '10:30');

insert into horario values ('10:30', '11:00');

insert into horario values ('11:00', '11:30');

insert into horario values ('11:30', '12:00');

insert into horario values ('12:00', '12:30');

insert into horario values ('12:30', '13:00');

insert into horario values ('13:00', '13:30');

insert into horario values ('13:30', '14:00');

insert into horario values ('14:00', '14:30');

insert into horario values ('14:30', '15:00');

insert into horario values ('15:00', '15:30');

insert into horario values ('15:30', '16:00');

insert into horario values ('16:00', '16:30');

insert into horario values ('16:30', '17:00');

insert into horario values ('17:00', '17:30');

insert into horario values ('17:30', '18:00');

insert into horario values ('18:00', '18:30');

insert into horario values ('18:30', '19:00');

insert into horario values ('19:00', '19:30');

insert into horario values ('19:30', '20:00');

insert into horario values ('20:00', '20:30');

insert into horario values ('20:30', '21:00');



