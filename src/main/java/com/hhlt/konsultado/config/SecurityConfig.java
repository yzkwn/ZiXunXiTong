package com.hhlt.konsultado.config;


import com.hhlt.konsultado.Filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenFilter tokenFilter;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// 基于token，所以不需要session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
				.antMatchers("/", "/*.html", "/favicon.ico","/layuis/**", "/css/**",  "/js/**","/fonts/**", "/js1/**",
						"/img/**","/mobileTerminal/fonts/**", "/layui/**", "/images/**",
						"/v2/api-docs/**", "/swagger-resources/**", "/webjars/**", "/pages/**", "/druid/**",
						"/statics/**","/templates/**","/static/**",
						"/consult/list","/consult/listSearch",	"/consult/add","/consult/addData","/consult/deleteOne","/consult/update","/consult/get","/consult/updateData",
						"/download/exportFile","/download/exportSpendFile","/api/spend/selectSpendList","/api/spend/saveSpend","/api/spend/jumpAnalysisList",
						"/api/spend/consultAnalysisList","/api/spend/export","/api/count/jumpChannel","/api/count/jumpPlatformBusiness","/api/count/jumpChannelBusiness",
						"/api/count/countChannelBusiness","/api/count/countPlatformBusiness","/api/count/exportPlatformBusiness","/api/count/consultCount","/api/count/exportChannel",
						"/api/rate/jumpRateList","/api/rate/selectPlat","/api/rate/savePlat","/api/spend/list","/uploads/downloadFileAction","/uploads/upload1","/api/spend/jumpSpendUpdate",
						"/api/spend/selectById","/api/spend/updateSpend",
						"/deal/add","/deal/uploads","/deal/list","/deal/listSearch",
						"/api/callback/msg","/api/callback/businesscard","/api/callback/selectVisitor",
						"/api/spend/selectSpendListSearch","/api/spend/jumpSaveSpend",
						"/channelSpend/list","/channelSpend/listSearch","/channelSpend/add","/channelSpend/addData",
						"/spendFenXi/view","/api/spend/selectSpendGrouByTimeSearch","/api/spend/selectSpendGroupByList","/api/spend/selectSpendGrouByChannelIdSearch","/api/spend/selectSpendGrouByTypeSearch",
						"/api/count/costdatashow","/channelSpend/Popout","/channelSpend/PopoutSerach","/SpendSon/list","/SpendSon/listSerach","/api/callback/liaotian"

						)
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
				.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
		// 解决不允许显示在iframe的问题
		http.headers().frameOptions().disable();
		http.headers().cacheControl();

		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
