package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import com.blubank.doctorappointment.entity.AppointmentEntity;
import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public List<AppointmentDto> getAllAppointments(){
        return appointmentMapper.toDtoList(appointmentRepository.findAll());
    }
}
