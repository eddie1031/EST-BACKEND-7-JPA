drop database if exists `est-hibernate-test`;
create database `est-hibernate-test`;
use `est-hibernate-test`;

-- team 추가
insert into `teams` ( name ) VALUES ('한화_이글스' );
insert into `teams` ( name ) VALUES ('키움_히어로즈' );
insert into `teams` ( name ) VALUES ('롯데_자이언츠' );

-- player 추가
insert into `players` ( name, team_id )
values ( '위니', 1 );

insert into `players` ( name, team_id )
values ( '비니', 1 );

insert into `players` ( name, team_id )
values ( '수리',1 );

insert into `players` ( name, team_id )
values ( '턱돌이',  2 );

insert into `players` ( name, team_id )
values ( '동글이',  2 );

insert into `players` ( name, team_id )
values ( '누리',  3 );

insert into `players` ( name, team_id )
values ( '아라',  3 );
