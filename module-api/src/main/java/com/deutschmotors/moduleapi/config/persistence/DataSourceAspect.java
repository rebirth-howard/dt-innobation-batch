package com.deutschmotors.moduleapi.config.persistence;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    /**
     * 트랜잭션 상태에 따라 데이터 소스를 결정
     * 읽기 전용 트랜잭션일 경우 슬레이브 데이터 소스를 사용,
     * 쓰기 트랜잭션일 경우 마스터 데이터 소스를 사용
     */
    @Before("execution(* com.deutschmotors.moduleapi.domain..service..*(..))")
    public void determineDataSource() {
        // 트랜잭션이 읽기 전용인지 여부 확인
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            // 읽기 전용 트랜잭션일 경우 슬레이브 데이터 소스 사용
            ReplicationDataBaseContextHolder.setDataSourceType(ReplicationDataBaseContextHolder.DataSourceType.SLAVE);
            log.debug("Switching to SLAVE DataSource");
        } else {
            // 쓰기 트랜잭션일 경우 마스터 데이터 소스 사용
            ReplicationDataBaseContextHolder.setDataSourceType(ReplicationDataBaseContextHolder.DataSourceType.MASTER);
            log.debug("Switching to MASTER DataSource");
        }
    }
}