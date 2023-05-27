package kz.careerguidance.applicationapi.service.impl.university;

import kz.careerguidance.applicationapi.entity.History;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.repository.university.HistoryRepository;
import kz.careerguidance.applicationapi.service.university.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository repository;

    public HistoryServiceImpl(HistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public History get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("History with id " + id + " doesn't exist"));
    }

    @Override
    public List<History> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public History add(History tag) {
        return repository.save(tag);
    }

    @Override
    @Transactional
    public History update(Long id, History s) {
        History tag = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("History doesn't exist"));
        tag.setName(s.getName());
        repository.save(tag);
        return tag;
    }

    @Override
    public History delete(Long id) {
        History tag = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("History doesn't exist"));
        repository.delete(tag);
        return tag;
    }

}
