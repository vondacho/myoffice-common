package edu.noia.myoffice.common.rest;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.common.rest.jackson.AuditableEntityMixin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyOfficeCommonRestApplication {

    @Bean
    public Module module() {
        SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(JpaAuditableEntity.class, AuditableEntityMixin.class);
        return module;
    }
}
