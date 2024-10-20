package com.deutschmotors.moduleapi.config.persistence;

public class ReplicationDataBaseContextHolder {

    // ThreadLocal을 사용하여 스레드마다 고유한 데이터베이스 타입을 저장
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    // 데이터베이스 타입 설정 (MASTER 또는 SLAVE)
    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    // 현재 스레드에서 데이터베이스 타입을 가져옴
    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }

    // 데이터베이스 타입 설정을 제거 (초기화)
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    // 데이터 소스의 타입을 정의하는 enum
    public enum DataSourceType {
        MASTER,  // 쓰기용 데이터소스
        SLAVE    // 읽기용 데이터소스
    }
}
