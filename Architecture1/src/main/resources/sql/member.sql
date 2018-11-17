-- Create table
create table MEMBER
(
  mid      VARCHAR2(32),
  name     VARCHAR2(32),
  phone    VARCHAR2(32),
  age      NUMBER,
  birthday DATE,
  note     VARCHAR2(256)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table MEMBER
  is '成员表（测试用）';
-- Add comments to the columns
comment on column MEMBER.mid
  is '成员id';
comment on column MEMBER.name
  is '名称';
comment on column MEMBER.phone
  is '电话号码';
comment on column MEMBER.age
  is '年龄';
comment on column MEMBER.birthday
  is '生日';
comment on column MEMBER.note
  is '备注';
