package com.blubank.doctorappointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReserveAppointmentDto {
    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private Long appointmentId;
}