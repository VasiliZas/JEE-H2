package web.vasilizas.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class SpringHibernateOrmConfig {

    private final DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(@Autowired Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceUnitName("jpa-unit");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("vasilizas.bean");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hbm2ddl.auto", "none");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("connection.pool_size", "20");
        properties.setProperty("hibernate.dbcp.initialSize", "5");
        properties.setProperty("hibernate.dbcp.maxTotal", "20");
        properties.setProperty("hibernate.dbcp.maxIdle", "10");
        properties.setProperty("hibernate.dbcp.minIdle", "5");
        properties.setProperty("hibernate.dbcp.maxWaitMillis", "-1");
        properties.setProperty("hibernate.default_schema", "my");
        return properties;
    }
}
