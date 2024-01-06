package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDto;
import com.blubank.doctorappointment.dto.ReserveAppointmentDto;
import com.blubank.doctorappointment.dto.WorkingDayDto;
import com.blubank.doctorappointment.entity.AppointmentEntity;
import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.NotAcceptableStatusException;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
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
                    .isReserved(false)
                    .build());
            appointmentStartTime = appointmentStartTime.plusMinutes(30);
        }
        appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<AppointmentEntity> result = appointmentRepository.findAll();
        return appointmentMapper.toDtoList(result);
    }

    @Override
    public List<AppointmentDto> getAppointmentsInDate(LocalDate date) {
        if (date == null)
            return getAllAppointments();
        LocalDateTime queryStartDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
        LocalDateTime queryEndDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59);
        List<AppointmentEntity> result = appointmentRepository.findAllByStartTimeBetween(queryStartDate, queryEndDate);
        return appointmentMapper.toDtoList(result);
    }

    @Override
    @Transactional
    public void deleteAppointment(long id) {

        Optional<AppointmentEntity> opt = appointmentRepository.findById(id);
        if (opt.isEmpty())
            throw new NotFoundException("Appointment with this id not found");
        if (opt.get().isReserved())
            throw new NotAcceptableStatusException("Reserved appointment cannot be deleted");
        appointmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void reserveAnAppointment(ReserveAppointmentDto dto) {
        Optional<AppointmentEntity> opt = appointmentRepository.findById(dto.getAppointmentId());
        if (opt.isEmpty())
            throw new NotFoundException("Appointment not found");
        AppointmentEntity appointment = opt.get();
        if (appointment.isReserved())
            throw new NotAcceptableStatusException("Appointment is reserved already");
        appointment.setPatientName(dto.getName());
        appointment.setPatientPhone(dto.getPhone());
        appointment.setReserved(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentDto> getByReserved(boolean isReserved) {
        return appointmentMapper.toDtoList(appointmentRepository.findByIsReserved(isReserved));
    }

    @Override
    public List<AppointmentDto> getReservedAppointmentsByPhone(String phone) {
        return appointmentMapper.toDtoList(appointmentRepository.findByPatientPhone(phone));
    }
}
