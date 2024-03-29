package com.blubank.doctorappointment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;

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

    private String patientName;

    private String patientPhone;
}