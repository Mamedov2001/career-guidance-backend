package kz.careerguidance.applicationapi.mapper;

import kz.careerguidance.applicationapi.dto.university.SpecialityDto;
import kz.careerguidance.applicationapi.dto.university.UpdateSpecialityDto;
import kz.careerguidance.applicationapi.entity.university.Speciality;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialityMapper {
    SpecialityDto toSpecialityDto(Speciality s);

    UpdateSpecialityDto toUpdateSpecialityDto(Speciality s);

    Speciality toSpecialityEntity(SpecialityDto s);
    Speciality toSpecialityEntity(UpdateSpecialityDto s);
}
