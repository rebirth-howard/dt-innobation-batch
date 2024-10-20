package com.deutschmotors.moduleapi.config.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReplicationRoutingConfig {

    @Bean
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("replicaDataSource") DataSource replicaDataSource) {

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        // 마스터 및 슬레이브 데이터소스를 매핑
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(ReplicationDataBaseContextHolder.DataSourceType.MASTER, masterDataSource);
        dataSourceMap.put(ReplicationDataBaseContextHolder.DataSourceType.SLAVE, replicaDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);  // 기본 데이터소스는 마스터

        return routingDataSource;
    }
}