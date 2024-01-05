package com.blubank.doctorappointment;

import com.blubank.doctorappointment.dto.ReserveAppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import com.blubank.doctorappointment.service.AppointmentService;
import com.blubank.doctorappointment.service.AppointmentServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DoctorAppointmentApplicationTests {

	@Autowired
	private AppointmentService service;

	@Test
	@Order(1)
	void should_add_workday_properly() {
		LocalDateTime startTime = LocalDateTime.of(2024, 1, 10, 7, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 1, 10, 23, 0);
		WorkingDayDto workingDayDto = WorkingDayDto.builder()
				.startTime(startTime)
				.endTime(endTime)
				.build();
		service.addWorkingDay(workingDayDto);
		assertEquals(32, service.getAllAppointments().size());
	}
	@Test
	@Order(2)
	void should_add_workday_with_end_time_before_start_time_with_error() {
		LocalDateTime startTime = LocalDateTime.of(2024, 1, 11, 7, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 1, 10, 23, 0);
		WorkingDayDto workingDayDto = WorkingDayDto.builder()
				.startTime(startTime)
				.endTime(endTime)
				.build();
		assertThrows(RuntimeException.class, () -> service.addWorkingDay(workingDayDto));
	}
	@Test
	@Order(3)
	void should_delete_an_appointment() {
		service.deleteAppointment(1);
		assertEquals(31, service.getAllAppointments().size());
	}

	@Test
	@Order(4)
	void should_get_appointments_in_date() {
		assertEquals(31, service.getAppointmentsInDate(LocalDate.of(2024, 1, 10)).size());
	}

	@Test
	@Order(5)
	void should_patient_reserve_an_appointment() {
		ReserveAppointmentDto reserveAppointmentDto = ReserveAppointmentDto.builder()
				.name("Kiarash")
				.phone("09120089249")
				.appointmentId(2L)
				.build();
		service.reserveAnAppointment(reserveAppointmentDto);
		assertEquals(1, service.getReservedAppointmentsByPhone("09120089249").size());
	}
}
