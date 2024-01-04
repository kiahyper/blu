package com.blubank.doctorappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @CreatedDate
    @Column(
            name = "CREATED_ON",
            nullable = false,
            updatable = false
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime createdOn;
    @LastModifiedDate
    @Column(
            name = "MODIFIED_ON",
            nullable = false
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime modifiedOn;
    @Version
    @Column(
            name = "OPT_LOCK",
            nullable = false,
            columnDefinition = "integer DEFAULT 0"
    )
    private Long version;

}
