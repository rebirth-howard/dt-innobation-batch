package com.deutschmotors.moduledata.entity.user;

import com.deutschmotors.moduledata.audit.MutableBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity
public class Role extends MutableBaseEntity {

    @Comment("역할 유형")
    @Column(length = 50, nullable = false)
    private String type;

    @Comment("역할 명")
    @Column(length = 50, nullable = false)
    private String name;

    @Comment("삭제 여부")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Comment("삭제일시")
    private LocalDateTime deletedAt;

    @LastModifiedBy
    @JdbcType(CharJdbcType.class)
    @Comment("삭제자")
    @Column()
    private UUID deletedBy;

}
