package kz.careerguidance.applicationapi.mapper;

import kz.careerguidance.applicationapi.dto.university.UniversityDto;
import kz.careerguidance.applicationapi.dto.university.UpdateUniversityDto;
import kz.careerguidance.applicationapi.entity.university.University;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityDto toUniversityDto(University u);

    University toUniversityEntity(UniversityDto u);

    University toUniversityEntity(UpdateUniversityDto u);

}
