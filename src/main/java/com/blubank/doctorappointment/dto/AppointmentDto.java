package com.blubank.doctorappointment.dto;


import com.blubank.doctorappointment.entity.BaseEntity;
import com.blubank.doctorappointment.entity.PatientEntity;
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
@Getter
@Setter
public class AppointmentDto extends BaseEntity {

    private Date startTime;

    private Date endTime;

    private boolean isReserved;

    private PatientEntity patient;
}