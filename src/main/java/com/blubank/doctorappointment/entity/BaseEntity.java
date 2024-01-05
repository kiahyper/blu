package com.blubank.doctorappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

}
