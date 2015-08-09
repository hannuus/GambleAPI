# GambleAPI
Hannuus Gamble API Support

参考的开发环境:</br>
eclipse luna
apache-tomcat-7.0.34
apache-maven-3.0.4
jdk1.8.0_05
mysql 5.5


初始化数据库和表: 执行gamble项目根目录下的: gamble_bbs.sql

API返回的错误码可以查询:
http://localhost:8080/gamble/api/docs/errorCodes


项目介绍:
HannuusDomainModel  项目使用到的domain model, 如果需要解析json, 可以直接使用这个jar
HannuusDAO 使用到的DAO都在这
HannuusCore 一些使用频繁的类
