package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.ReserveAppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import com.blubank.doctorappointment.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RequestMapping("/appointment")
@RestController
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @Operation(description = "Adding working day by doctor")
    @PostMapping
    public void addWorkingDay(@RequestBody WorkingDayDto dto) {
        service.addWorkingDay(dto);
    }

    @Operation(description = "Get appointments in specific date or all of them (if date is null)")
    @GetMapping
    public void getAppointments(@RequestBody LocalDate date) {
        service.getAppointmentsInDate(date);
    }

    @Operation(description = "Reserve an appointment")
    @PostMapping("reserve")
    public void getAppointments(@RequestBody ReserveAppointmentDto dto) {
        service.reserveAnAppointment(dto);
    }

    @Operation(description = "Get appointments by phone number")
    @GetMapping("patient-appointments")
    public void getPatientAppointmentsByPhoneNumber(@RequestParam("phone") String phone) {
        service.getReservedAppointmentsByPhone(phone);
    }

    @Operation(description = "Get appointments by whether they are reserved or not")
    @GetMapping("reserved")
    public void getAppointmentsByIsReserved(@RequestParam("reserved") boolean isReserved) {
        service.getByReserved(isReserved);
    }
}
