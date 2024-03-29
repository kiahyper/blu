package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findAllByStartTimeBetween(
            LocalDateTime start,
            LocalDateTime end);

    List<AppointmentEntity> findByIsReserved(boolean isReserved);

    List<AppointmentEntity> findByPatientPhone(String phone);
}
