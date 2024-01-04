package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

}
