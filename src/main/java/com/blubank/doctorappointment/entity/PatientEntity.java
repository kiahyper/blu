package com.blubank.doctorappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@DynamicUpdate
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
@Entity
@Getter
@Setter
public class PatientEntity extends BaseEntity{

    @Column(name = "start_time_date", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;
}