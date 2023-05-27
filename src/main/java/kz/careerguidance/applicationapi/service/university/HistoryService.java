package kz.careerguidance.applicationapi.service.university;

import kz.careerguidance.applicationapi.entity.History;

import java.util.List;
public interface HistoryService {

    History get(Long id);

    List<History> getAll();

    History add(History s);

    History update(Long id, History s);

    History delete(Long id);

}
