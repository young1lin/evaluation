package me.young1lin.evaluation.api.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 可以根据 bean 名称实现多个数据源的
 *
 * @author young1Lin
 */
@Configuration
@MapperScan(basePackages = "me.young1lin.evaluation.api.domain.", sqlSessionTemplateRef = "mainSqlSessionTemplate")
public class MyBatisConfig {

    @Bean("mainDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean("mainDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mainSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mainDatasource") HikariDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("me.young1lin.evaluation.domain");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/**/*.xml"));
        return bean.getObject();
    }

    @Bean("mainSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
