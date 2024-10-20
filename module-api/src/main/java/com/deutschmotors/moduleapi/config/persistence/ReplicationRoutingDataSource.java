package com.deutschmotors.moduleapi.config.persistence;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // ReplicationDataBaseContextHolder에서 현재 스레드의 데이터베이스 타입(MASTER 또는 SLAVE)을 반환
        return ReplicationDataBaseContextHolder.getDataSourceType();
    }
}