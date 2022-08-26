package com.example.ecr.repository;


import com.example.ecr.entity.RecentEcr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentEcrRepository extends JpaRepository<RecentEcr, Long> {

    Page<RecentEcr> findAll(Specification<RecentEcr> specification, Pageable pageable);

}
