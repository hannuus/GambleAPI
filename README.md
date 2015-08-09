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
	http://localhost:8080/gamble/api/docs/errorCodes</br>


项目介绍:</br>
	HannuusDomainModel  项目使用到的domain model, 如果需要解析json, 可以直接使用这个jar</br>
	HannuusDAO 使用到的DAO都在这</br>
	HannuusCore 一些使用频繁的类</br>
