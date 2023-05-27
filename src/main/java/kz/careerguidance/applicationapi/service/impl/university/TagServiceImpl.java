package kz.careerguidance.applicationapi.service.impl.university;

import kz.careerguidance.applicationapi.entity.university.Tag;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.repository.university.TagRepository;
import kz.careerguidance.applicationapi.service.university.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tag get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag with id " + id + " doesn't exist"));
    }

    @Override
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Tag create(Tag tag) {
        return repository.save(tag);
    }

    @Override
    @Transactional
    public Tag update(Long id, Tag s) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag doesn't exist"));
        if (null != s.getName()) {
            tag.setName(s.getName());
        }
        return repository.save(tag);
    }

    @Override
    @Transactional
    public Tag delete(Long id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag doesn't exist"));
        repository.deleteById(id);
        return tag;
    }

}
