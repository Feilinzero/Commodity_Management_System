# Commodity_Management_System

####  **介绍** 
 **JavaSE** 和 **JDBC** 的基础应用--基于控制台实现的商品管理系统

#### **使用**

1. 使用mssql数据库
2. 创建数据库Commodity_Management_System
3. 用数据库表结构文件创建数据库表
4. 添加数据库用户root,密码root,拥有管理员权限
5. 确保数据库启动,开启sql身份验证登录
6. sql配置管理中1433端口已开

注:相关数据库连接配置可以在c3p0配置文件中修改

####  **软件架构** 

1. 使用 **JavaSE** 、 **JDBC** 结合 **SQL server** 数据库完成
2. 整个项目简单分为三层开发， **Instance** 层、 **Dao** 层、 **Action** 层
    *  **Instance** 层
        数据库表的实体类管理，包含了数据库表的JavaBean实体类
    *  **Dao** 层
        实现sql数据库的连接，包含了对数据库的CURD（增删查改）操作
    *  **Action** 层
        数据显示层，同时对操作进行响应和调用dao层获取修改数据

####  **目录** 

*  _docs_ --放置项目相关文档
*  _information_ --放置数据库表结构文件
*  _libs_ --放置JDBC与数据库的相关依赖
    * c3p0连接池
    * mssql数据库驱动
*  _src/cn.dalaonp_ --代码源文件
    * action--显示层代码
    * dao--dao层代码
    * db--数据库连接对象
    * instance--数据库表实例
    * test--测试类
    * tools--工具类
     _c3p0-config.xml_ --c3p0连接池配置文件


