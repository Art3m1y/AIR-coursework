package ru.Art3m1y.delivery_service.infrastructure.in.rest.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.Art3m1y.delivery_service.infrastructure.in.rest.filter.filters.RequestResponseLoggingFilter;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> requestResponseLoggingFilter(){
        var registrationBean = new FilterRegistrationBean<RequestResponseLoggingFilter>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
