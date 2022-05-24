package ru.max.springcloudtask.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.SimpleTaskAutoConfiguration;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.persistence.EntityManagerFactory;

@AutoConfigureBefore({
        SimpleTaskAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@RequiredArgsConstructor
public class TaskConf {

    @Bean
    @ConfigurationProperties(prefix = "spring.cloud.task.datasource")
    public HikariConfig taskHikariDatasourceConfig() {
        return new HikariConfig();
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    @Primary
    @ConditionalOnMissingBean(TransactionManager.class)
    public PlatformTransactionManager transactionManager(
            ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
        return transactionManager;
    }

    @Bean
    public TaskConfigurer taskConfigurer(HikariConfig taskHikariDatasourceConfig,
                                         ConfigurableApplicationContext context) {
        return new DefaultTaskConfigurer(
                new HikariDataSource(taskHikariDatasourceConfig)
        );
    }
}

