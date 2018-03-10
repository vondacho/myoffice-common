package edu.noia.myoffice.common.data.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan(basePackageClasses = {JpaEventPublication.class, com.vladmihalcea.hibernate.type.json.JsonStringType.class})
@Configuration
public class DataComponentTestConfig {
}
