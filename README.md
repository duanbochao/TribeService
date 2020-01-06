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
   
   
   
   
   
##2、登录接口的实现
>创建Role类
````
public class Role {
    private Integer id;
    private String name;
}
````
>创建user类实现UserDetails
````
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String userface;
    private String nickname;
    private String email;
    private boolean enabled;
    private Timestamp regTime;
    private List<Role> roles; //注意该类中又Role
    
    //....省去get和set
    
   
   //重点强调的是这个方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;
    }
}
````
>创建UserService实现UserDetailsService
````
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            return new User();
        }

        List<Role> roles = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roles);

        return user;
    }
}
````
>创建AccessManagerHandle
````
//该类的作用是当用户的权限不足的提醒
public class AccessManagerHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("utf-8");
        PrintWriter out = resp.getWriter();
        out.write("权限不足请联系管理员");
        out.flush();
        out.close();
    }
}
````
>创建WebSecurityConfig
````
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            /**
             * @param charSequence 明文
             * @param s 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/category/all").authenticated()
                .antMatchers("/admin/**","/reg").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                        out.flush();
                        out.close();

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}"+e);
                        out.flush();
                        out.close();
                    }
                })
                .and().logout().logoutUrl("/logout")
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AccessManagerHandle();
    }
}
````
>创建Mapper

**Mapper中的内容和service中的接口保持一致就行了**