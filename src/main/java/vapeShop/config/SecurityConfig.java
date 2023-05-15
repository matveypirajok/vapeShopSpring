//package vapeShop.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.firewall.DefaultHttpFirewall;
//import vapeShop.entity.users.Role;
//
//@Configuration
//@EnableWebSecurity
//
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.httpFirewall(new DefaultHttpFirewall());
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//
//                .antMatchers("/users").hasAuthority(Role.ADMIN.name())
//                .antMatchers("/users/new").permitAll()
//                .antMatchers("/*").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
////                .loginPage("/login")
////                .failureUrl("/login-error")
////                .loginProcessingUrl("/auth")
//                .permitAll()
//                .defaultSuccessUrl("/", true)
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//
//                .and()
//                .csrf().disable();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
