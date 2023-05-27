package kz.careerguidance.applicationapi.service.university;

import kz.careerguidance.applicationapi.entity.university.Tag;

import java.util.List;

public interface TagService {
    
    Tag get(Long id);

    List<Tag> getAll();

    Tag create(Tag t);

    Tag update(Long id, Tag t);

    Tag delete(Long id);
}
