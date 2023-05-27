package kz.careerguidance.applicationapi.service.university;

import kz.careerguidance.applicationapi.entity.university.Subject;

import java.util.List;

public interface SubjectService {

    Subject get(Long id);

    List<Subject> getAll();

    Subject create(Subject s);

    Subject update(Long id, Subject s);

    Subject delete(Long id);

}
