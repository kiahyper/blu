package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.AppointmentDto;
import com.blubank.doctorappointment.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDto toDto(AppointmentEntity entity);

    AppointmentEntity toEntity(AppointmentDto dto);

    List<AppointmentEntity> toEntityList(List<AppointmentDto> dto);

    List<AppointmentDto> toDtoList(List<AppointmentEntity> entity);
}