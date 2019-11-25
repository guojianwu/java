package cn.gjw.myjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidCinfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<String, String>();
        // 设置后台界面的用户名
        map.put("loginUsername", "guoxw");
        //设置后台界面密码
        map.put("loginPassword", "123456");
        // 设置那些ip允许访问，" " 为所有
        map.put("allow", "");
        // 不允许该ip访问
//        map.put("deny","33.32.43.123");
        bean.setInitParameters(map);
        return bean;
    }

    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        Map<String, String> map = new HashMap<String, String>();
        map.put("exclusions", "*.png，*.js");
        bean.setInitParameters(map);
        return bean;
    }


}
