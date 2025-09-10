package com.school.dashboard.repository;

import com.school.dashboard.model.EducationalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalDetailRepository extends JpaRepository<EducationalDetail, Long> {
}
