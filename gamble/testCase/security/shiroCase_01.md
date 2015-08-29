#参考
- ShiroAPI.xls
- Spring bean "shiroFilter"
- testCase/security/shiroCase_01.sql
- com.huarui.gamble.web.action.TestAction
	
#权限概览
- aelns roles[admin] perms[\*:\*]
- cuesky roles[oper,user_strong] perms[user:\* and topic:\* and other:\*]
	
#测试loginUrl

- 访问任意非"shiroFilter"中标记为anon的url都会直接跳转至loginUrl
	
#测试@RequiresUser

- 访问顺序

1. <http://localhost:8080/gamble/api/test/success>  
		因为没有登录，所以直接跳转至shiroFilter[spring bean]的loginUrl指定的地址"/index.htm"
2. <http://localhost:8080/gamble/api/test/login?userName=cuesky&password=admin>  
		以user角色登录，使用该url成功登录以后可以跳转至"/success"
3. 重新打开一个空页面，再次访问1中url，因为已经login成功，所以可以直接跳转至"/success"  

#测试@RequiresRoles

- 访问顺序

1. <http://localhost:8080/gamble/api/test/login?userName=cuesky&password=admin>  
以user角色登录
2. <http://localhost:8080/gamble/api/test/adminTest>  
因为角色不符合要求，出现errorCode
3. <http://localhost:8080/gamble/api/test/login?userName=aelns&password=admin>  
角色符合要求，跳转至"/success"

#测试@RequiresPermissions

- 访问顺序

1. <http://localhost:8080/gamble/api/test/login?userName=dude&password=admin>  
使用dude登录，perms[topic:create,update,view]
2. <http://localhost:8080/gamble/api/test/operTopic>  
因不符合perms[topic:create,delete]，出现errorCode
3. <http://localhost:8080/gamble/api/test/login?userName=cuesky&password=admin>  
使用cuesky登录，perms[user:\* and topic:\* and other:\*]
4. <http://localhost:8080/gamble/api/test/operTopic>  
符合perms[topic:create,delete]，跳转至"/success"