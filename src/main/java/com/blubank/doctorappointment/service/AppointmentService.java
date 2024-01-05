package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDto;
import com.blubank.doctorappointment.dto.ReserveAppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
public interface AppointmentService {
    void addWorkingDay(@Valid WorkingDayDto workingDayDto);

    List<AppointmentDto> getAllAppointments();

    List<AppointmentDto> getAppointmentsInDate(LocalDate date);

    void deleteAppointment(long id);

    void reserveAnAppointment(@Valid ReserveAppointmentDto dto);

    List<AppointmentDto> getByReserved(boolean isReserved);

    List<AppointmentDto> getReservedAppointmentsByPhone(String phone);
}
