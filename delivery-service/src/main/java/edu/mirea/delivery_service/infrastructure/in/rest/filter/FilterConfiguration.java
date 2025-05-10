package edu.mirea.delivery_service.infrastructure.in.rest.filter;

import edu.mirea.delivery_service.infrastructure.in.rest.filter.filters.LoggableContextClearingFilter;
import edu.mirea.delivery_service.infrastructure.in.rest.filter.filters.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    private static final String API_URL_PATTERN = "/api/*";

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> requestResponseLoggingFilter(){
        var registrationBean = new FilterRegistrationBean<RequestResponseLoggingFilter>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns(API_URL_PATTERN);
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoggableContextClearingFilter> loggableContextClearingFilter(){
        var registrationBean = new FilterRegistrationBean<LoggableContextClearingFilter>();

        registrationBean.setFilter(new LoggableContextClearingFilter());
        registrationBean.addUrlPatterns(API_URL_PATTERN);
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
