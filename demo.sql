drop table USER_TEST;
create table USER_TEST
(
  UUID        VARCHAR2(50),
  UNAME       VARCHAR2(50),
  SEX         VARCHAR2(50),
  AGE         VARCHAR2(50),
  FLAG        VARCHAR2(50),
  CREATE_TIME DATE default SYSDATE
);
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('a1ce8855-371c-416e-982d-9fa0a3ec5956', '张巧转', '2', '23', '1', to_date('14-06-2019 16:58:35', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1ebc0b09-2fb1-4d8f-b227-f5cf56865fb5', '张小小', '1', '34', '0', to_date('20-06-2019 16:31:25', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('22343bc5-713f-4b9f-8c7c-e19ab80268ba', '张晓晓', '2', '21', '1', to_date('14-06-2019 17:28:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('6dc19408-1a35-4f89-81d9-a1ac8c2e62fa', 'cuihui', '1', '28', '1', to_date('20-06-2019 12:11:20', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('8fb3c193-e10c-44f3-8a39-78967e3eb92a', '张小明', '2', '24', '1', to_date('14-06-2019 17:33:31', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('f413935f-8905-499a-912a-c493b58931f2', '江鱼儿', '2', '34', '1', to_date('15-06-2019 01:30:11', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000707', '李孝利', '2', '24', '1', to_date('15-06-2019 10:47:47', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000708', '张文洪', '1', '26', '1', to_date('15-06-2019 10:48:30', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000709', '李铭兴', '1', '34', '1', to_date('15-06-2019 10:48:43', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000710', '报奥', '1', '33', '1', to_date('15-06-2019 10:49:05', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000711', '小明', '1', '8', '1', to_date('15-06-2019 10:49:35', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('1000000712', '小李子', '2', '12', '1', to_date('15-06-2019 10:49:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('cc1a4346-6b43-4f8f-aac2-9f8ec114152c', '杨娜', '2', '28', '1', to_date('16-06-2019 02:37:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('06cb966b-3acb-4263-99e8-0def64573b9a', '崔辉', '1', '30', '1', to_date('14-06-2019 17:38:32', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('58c33847-919f-4e70-9456-9d58b33c1151', '李四', '1', '22', '0', to_date('14-06-2019 17:53:31', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('d6f0114d-4ced-4e0d-999e-673047c97e88', '杨小历', '1', '33', '1', to_date('14-06-2019 22:27:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into User_Test (UUID, UNAME, SEX, AGE, FLAG, CREATE_TIME)
values ('ebb14f47-c587-4a3d-b7dc-88d2c9507360', '转转', '2', '23', '1', to_date('15-06-2019 01:14:26', 'dd-mm-yyyy hh24:mi:ss'));
commit;