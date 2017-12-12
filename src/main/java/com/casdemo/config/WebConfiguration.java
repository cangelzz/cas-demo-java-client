package com.casdemo.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Value("${cas.login.url}")
    private String loginUrl;

    @Value("${cas.url.prefix}")
    private String urlPrefix;

    @Value("${app.server.name}")
    private String serverName;

    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new SingleSignOutFilter());
        bean.addInitParameter("casServerUrlPrefix", loginUrl);
        return bean;
    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new AuthenticationFilter());
        bean.addInitParameter("casServerLoginUrl", loginUrl);
        bean.addInitParameter("serverName", serverName);
        return bean;
    }


    @Bean
    public FilterRegistrationBean casValidationFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        bean.addInitParameter("casServerUrlPrefix", urlPrefix);
        bean.addInitParameter("serverName", serverName);
        bean.addInitParameter("redirectAfterValidation", "true");
        bean.addInitParameter("useSession", "true");
        bean.addInitParameter("authn_method", "mfa-duo");
        return bean;
    }


    @Bean
    public FilterRegistrationBean requestWrapperFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new HttpServletRequestWrapperFilter());
        return bean;
    }

    @Bean
    public FilterRegistrationBean threadLocalFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new AssertionThreadLocalFilter());
        return bean;
    }
}
