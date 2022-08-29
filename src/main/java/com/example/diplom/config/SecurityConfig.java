package com.example.diplom.config;//package com.example.diplom.config;
//
//import com.example.diplom.enams.Role;
//import com.example.diplom.entity.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/", "/registration", "/about", "/help").permitAll()
////                .antMatchers(HttpMethod.POST, "/vacancies/").hasAuthority(Permission.USER_READ.getPermissions())
////                .antMatchers(HttpMethod.GET, "/vacancies/").hasAuthority(Permission.USER_READ.getPermissions())
////                .antMatchers(HttpMethod.DELETE, "/vacancies/**").hasAuthority(Permission.USER_WRITE.getPermissions())
////                .anyRequest()
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
////    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http
//               .csrf().disable()
//               .authorizeRequests()
//               .antMatchers("/").permitAll()
//               .antMatchers(HttpMethod.POST, "/").hasRole(Role.ADMIN.name())
//               .antMatchers(HttpMethod.GET, "/").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
//               .antMatchers(HttpMethod.DELETE, "/").hasRole(Role.ADMIN.name())
//               .anyRequest()
//               .authenticated()
//               .and()
//               .httpBasic();
//    }
//
////        public UserDetailsService userDetailsService(){
////        return new InMemoryUserDetailsManager(
////                User.builder()
////                        .username("admin")
////                        .password(passwordEncoder().encode("admin"))
////                        .authorities(Role.ADMIN.name())
////                        .build(),
////                User.builder()
////                        .username("user")
////                        .password(passwordEncoder().encode("user"))
////                        .authorities(Role.USER.name())
////                        .build()
////        );
////    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(
//                org.springframework.security.core.userdetails.User.builder()
//                        .username("admin")
//                        .password("1111")
//                        .roles(Role.ADMIN.name())
//                        .build(),
//                org.springframework.security.core.userdetails.User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles(Role.USER.name())
//                        .build()
//        );
//    }
//
//         @Bean
//    protected PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(12);
//    }
////
//
//}
