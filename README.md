# basic_springboot

此项目只是个脚手架，基于springboot 和 dubbo的项目，目前系统只是个架子，里面的内容还有很多都在完善中...
项目soa化，抽出服务core层，admin,app,h5,小程序module里只是controller层，core里是多有service的实现，下面会讲到各个module的介绍。

用到技术栈：
Spring Boot
MySQL
Shiro 
Boostrapt
mybatis
redis
dubbo

编码约定：
系统分为controller、service、dao层，没有model层，也可以加个model层，由service层调用model层，model层调用dao层，所有的异常信息在model层catch住，看自己个人爱好，我认为都可以。 
controller主要负责转发、service主要负责业务逻辑、dao主要是数据库的操作。


controller、service、dao方法名称约定
如果是增加数据操作用insert/add 做前缀。
如果是删除操作用delete/del 做前缀
如果是修改操作用update/edit 做前缀
如果是查询操作用select/get 做前缀
如果是查询多条数据 list 前缀

core里会有针对每个方法的拦截，如果有自定义的annotation 会根据自定义的annotation操作相应的数据库，如果没有注解会根据方法的前缀直接走从库。

数据库读写分离
缓存redis

