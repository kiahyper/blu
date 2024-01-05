package com.blubank.doctorappointment.dto;


import com.blubank.doctorappointment.entity.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean isReserved;

    private PatientEntity patient;
}