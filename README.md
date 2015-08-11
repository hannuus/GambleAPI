# GambleAPI
Hannuus Gamble API Support

参考的开发环境:</br>
eclipse luna</br>
apache-tomcat-7.0.34</br>
apache-maven-3.0.4</br>
jdk1.8.0_05</br>
mysql 5.5</br>
</br>

初始化数据库和表: </br>
	执行gamble项目根目录下的: gamble_bbs.sql</br>

API返回的错误码可以查询:</br>
	http://localhost:8080/gamble/api/docs/errorCodes.json</br>


项目介绍:</br>
	HannuusDomainModel  项目使用到的domain model, 如果需要解析json, 可以直接使用这个jar</br>
	HannuusDAO 使用到的DAO都在这</br>
	HannuusCore 一些使用频繁的类</br>

</br>
API开发分配计划:</br>
Carson: </br>
【1.3】会员动态统计</br>
【3.1】基本用户信息推送</br>
【3.2】系统推送</br>

JH:</br>
 【1.2】用户设置</br>
 【2.2】论坛交互APIs</br>
 【5.2】运营操作系统</br>
 
Aelns:</br>
【1.1.2】用户关系圈管理</br>
【5.1】虚拟用户、内容</br>
【6.2】业务引擎 </br>

Cuesky: </br>
 【6.1.2】用户权限管理</br>
 【6.3】第三方接口设置</br>
 【6.4】开机画面、广告植入等前端UI设置</br>

待定: </br>
【2.3】内容审核系统</br>
