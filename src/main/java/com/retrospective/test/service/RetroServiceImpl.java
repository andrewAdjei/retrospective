package com.retrospective.test.service;

import java.util.List;

import com.retrospective.test.entity.Retrospective;
import com.retrospective.test.repository.RetrospectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RetroServiceImpl {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Transactional
    public List<Retrospective> findByNameAndSort(String name) {
        return retrospectiveRepository.findByNameAndSort(name,Sort.by("name").ascending());
    }

    @Transactional
    public List<Retrospective> findAllSort() {
        return retrospectiveRepository.findAllSort(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Transactional
    public Retrospective findByName(String name) {
        return retrospectiveRepository.findByName(name);
    }


    @Transactional
    public Page<Retrospective> findAllWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return retrospectiveRepository.findAllWithPagination(pageable);
    }


    @Transactional
    public List<Retrospective> findAll() {
        return retrospectiveRepository.findAll();
    }

    @Transactional
    public Retrospective save(Retrospective retrospective) {
        return retrospectiveRepository.save(retrospective);
    }


}
