package me.izac.groupdebtmanager.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("group-debt-manager-public")
                .pathsToMatch("/api/users/**", "/api/debts/**", "/api/groups/**")
                .build();
    }
}
