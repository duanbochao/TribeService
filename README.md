##1、后台框架搭建
###整理项目结构
更改后的目录结构为  
![项目结构.png](https://raw.githubusercontent.com/duanbochao/image/master/01.png "项目结构")
###配置pom.xml文件
>添加依赖
 ```
  <!--security-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>


        <!--druid数据库-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>RELEASE</version>
        </dependency>

        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>RELEASE</version>
        </dependency>


        <!--mysql数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!--codec-->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>

        <!--commons-io-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>RELEASE</version>
        </dependency>

        <!--log4j-->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
 ```
>在pom.xml文件中的build中配置扫描xml文件
 ```
 <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>

            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
 ```
 >配置application.properties信息
  ```
 #配置数据库信息
 spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
 spring.datasource.url=jdbc:mysql:///vueblog2?useUnicode=true&characterEncoding=UTF-8
 spring.datasource.username=root
 spring.datasource.password=xxxxxxxxx
 
 #配置端口
 server.port=8082
 
 #配置日志打印
 logging.level.com.mapper=debug
  ```
 >启动项目
当控制台输出下面信息时说明项目创建没有问题

  ```
 Started StartApplication in 8.223 seconds (JVM running for 10.245)
   ```