package kz.careerguidance.applicationapi.service.impl.university;

import kz.careerguidance.applicationapi.entity.university.Subject;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.repository.university.SubjectRepository;
import kz.careerguidance.applicationapi.service.university.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject with id " + id + " doesn't exist"));
    }

    @Override
    public List<Subject> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Subject create(Subject subject) {
        return repository.save(subject);
    }

    @Override
    @Transactional
    public Subject update(Long id, Subject s) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject doesn't exist"));
        if (null != s.getName()) {
            subject.setName(s.getName());
        }
        return repository.save(subject);
    }

    @Override
    @Transactional
    public Subject delete(Long id) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject doesn't exist"));
        repository.deleteById(id);
        return subject;
    }

}
