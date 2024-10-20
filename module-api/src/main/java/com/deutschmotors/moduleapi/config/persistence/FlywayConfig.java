package com.deutschmotors.moduleapi.config.persistence;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("local")
@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    // spring.flyway.enabled 설정이 true일 때만 Flyway 빈을 생성
    @Bean
    @ConditionalOnProperty(name = "spring.flyway.enabled", havingValue = "true", matchIfMissing = false)
    public Flyway flyway(@Qualifier("masterDataSource") DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .baselineVersion("1")
                .cleanDisabled(false)
                .locations("classpath:flyway")
                .load();
    }

    // flyway가 등록되었을 때만 마이그레이션을 실행
    @Bean
    @ConditionalOnProperty(name = "spring.flyway.enabled", havingValue = "true")
    public ApplicationRunner runFlywayMigration(Flyway flyway) {
        return args -> {
            // Flyway 마이그레이션 수동 실행
            flyway.migrate();
        };
    }
}
