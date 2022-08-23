package com.example.ecr.repository;


import com.example.ecr.entity.RecentEcr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentEcrRepository extends JpaRepository<RecentEcr, Long> {

    //List<RecentEcr> findByCantask_id(long cantask_id);

}
