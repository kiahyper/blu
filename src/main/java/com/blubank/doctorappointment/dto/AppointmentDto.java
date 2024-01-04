package com.blubank.doctorappointment.dto;


import com.blubank.doctorappointment.entity.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    private Long id;

    private Date startTime;

    private Date endTime;

    private boolean isReserved;

    private PatientEntity patient;
}