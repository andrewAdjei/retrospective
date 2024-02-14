package com.retrospective.test.repository;

import java.util.List;

import com.retrospective.test.entity.Retrospective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RetrospectiveRepository extends JpaRepository<Retrospective, Long> {

    @Query("SELECT t FROM Retrospective t")
    List<Retrospective> findAll();

    @Query("SELECT t FROM Retrospective t")
    List<Retrospective> findAllSort(Sort sort);

    @Query("SELECT t FROM Retrospective t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', ?1,'%'))")
    List<Retrospective> findByNameAndSort(String title, Sort sort);

    @Query("SELECT t FROM Retrospective t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Retrospective findByName(String name);

    @Query("SELECT t FROM Retrospective t")
    Page<Retrospective> findAllWithPagination(Pageable pageable);



}
