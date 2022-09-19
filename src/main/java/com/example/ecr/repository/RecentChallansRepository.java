package com.example.ecr.repository;


import com.example.ecr.entity.RecentChallans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentChallansRepository extends JpaRepository<RecentChallans, Long> {
    //Page<RecentChallans> findAll(Pageable pageable);
}
