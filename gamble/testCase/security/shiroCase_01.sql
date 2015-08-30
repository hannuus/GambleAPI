insert into user(id,user_name,password) values(1,'aelns','admin');
insert into user(id,user_name,password) values(2,'cuesky','admin');
insert into user(id,user_name,password) values(3,'dude','admin');

insert into role(id,name,role_value,available) values(1,'管理员','admin',1);
insert into role(id,name,role_value,available) values(2,'运营人员','oper',1);
insert into role(id,name,role_value,available) values(3,'正常用户','user_strong',1);
insert into role(id,name,role_value,available) values(4,'受限用户','user_weak',1);
insert into role(id,name,role_value,available) values(5,'游客','guest',1);

-- aelns[admin]
-- cuesky[oper,user_strong]
-- dude[user_weak]
insert into user_role(id,user_id,role_id) values(1,1,1);
insert into user_role(id,user_id,role_id) values(2,2,2);
insert into user_role(id,user_id,role_id) values(3,2,3);
insert into user_role(id,user_id,role_id) values(4,3,4);

insert into permission(id,name,permission_value,available) values(1,'所有','*',1);
insert into permission(id,name,permission_value,available) values(2,'新增','create',1);
insert into permission(id,name,permission_value,available) values(3,'删除','delete',1);
insert into permission(id,name,permission_value,available) values(4,'修改','update',1);
insert into permission(id,name,permission_value,available) values(5,'查询','view',1);
insert into permission(id,name,permission_value,available) values(6,'审核','audit',1);

insert into resource(id,name,resource_value,type,available) values(1,'所有','*','model',1);
insert into resource(id,name,resource_value,type,available) values(2,'用户','user','model',1);
insert into resource(id,name,resource_value,type,available) values(3,'贴文','topic','model',1);
insert into resource(id,name,resource_value,type,available) values(4,'其他','other','model',1);

-- aelns roles[admin] perms[*:*]
-- cuesky roles[oper,user_strong] perms[user:* and topic:* and other:*]
-- dude roles[user_weak] perms[topic:create,update,view]
insert into role_resource_permission(id,role_id,resource_id,permission_ids) values(1,1,1,'1');
insert into role_resource_permission(id,role_id,resource_id,permission_ids) values(2,2,2,'1');
insert into role_resource_permission(id,role_id,resource_id,permission_ids) values(3,3,3,'1');
insert into role_resource_permission(id,role_id,resource_id,permission_ids) values(4,3,4,'1');
insert into role_resource_permission(id,role_id,resource_id,permission_ids) values(5,4,3,'2,4,5');

commit;