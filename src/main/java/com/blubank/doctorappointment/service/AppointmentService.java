package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import com.blubank.doctorappointment.entity.AppointmentEntity;
import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public void addWorkingDay(WorkingDayDto workingDayDto) {
        if (workingDayDto.getEndTime().isBefore(workingDayDto.getStartTime()))
            throw new RuntimeException("End time cannot be before start time");
        if (workingDayDto.getStartTime().plusMinutes(30).isAfter(workingDayDto.getEndTime()))
            return;
        LocalDateTime appointmentStartTime = workingDayDto.getStartTime();
        ArrayList<AppointmentEntity> appointments = new ArrayList<>();
        while (!appointmentStartTime.plusMinutes(30).isAfter(workingDayDto.getEndTime())) {
            appointments.add(AppointmentEntity.builder()
                    .startTime(appointmentStartTime)
                    .endTime(appointmentStartTime.plusMinutes(30))
                    .patient(null)
                    .isReserved(false)
                    .build());
            appointmentStartTime = appointmentStartTime.plusMinutes(30);
        }
        appointmentRepository.saveAll(appointments);
    }

    public List<AppointmentDto> getAllAppointments() {
        List<AppointmentEntity> result = appointmentRepository.findAll();
        if(result.isEmpty())
            throw new NotFoundException("No appointments found");
        return appointmentMapper.toDtoList(result);
    }

    public List<AppointmentDto> getAppointmentsInDate(LocalDate date){
        LocalDateTime queryStartDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
        LocalDateTime queryEndDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59);
        List<AppointmentEntity> result = appointmentRepository.findAllByStartTimeBetween(queryStartDate, queryEndDate);
        if(result.isEmpty())
            throw new NotFoundException("No appointments found");
        return appointmentMapper.toDtoList(result);
    }

    public void deleteAppointment(long id) {
        if(appointmentRepository.findById(id).isEmpty())
            throw new NotFoundException("Appointment with this id not found");
        appointmentRepository.deleteById(id);
    }
}
