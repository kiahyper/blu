package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.entity.WorkingDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<WorkingDayEntity, Long> {
}
