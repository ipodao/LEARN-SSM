配置：

1.mybatis：generatorConfig.xml（数据库表对应，）

2.数据库：datasource.properties(数据库地址，用户名密码)

3.spring:applicationContext,applicationContext-datasource.xml

4.springMVC:webapp/WEB-INF/dispatcher-servlet.xml

5.日志：logback.xml（日志文件路径，运行机器的完整物理路径,根据日期自动打包）

6.ftp:mmall.properties（ftp服务器的地址，用户名密码）



插件：

mybatis-generator：自动对应数据库生成实体类、dao、mappers