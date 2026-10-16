package ImageHoster.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * Builds the 'imageHoster' persistence unit on top of a Spring-managed DataSource.
 * Connection settings come from DB_URL / DB_USERNAME / DB_PASSWORD (or the
 * equivalent imagehoster.datasource.* properties) instead of being hard-coded.
 */
@Configuration
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            @Value("${imagehoster.jpa.dialect:org.hibernate.dialect.PostgreSQL10Dialect}") String dialect) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        emfb.setDataSource(dataSource);
        emfb.getJpaPropertyMap().put("hibernate.dialect", dialect);
        return emfb;
    }

    @Bean
    public DataSource dataSource(
            @Value("${imagehoster.datasource.url:${DB_URL:jdbc:postgresql://localhost:5432/imageHoster}}") String url,
            @Value("${imagehoster.datasource.username:${DB_USERNAME:postgres}}") String username,
            @Value("${imagehoster.datasource.password:${DB_PASSWORD:password}}") String password) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
