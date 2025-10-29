package com.tekton.percentage.api.infraestructure.adapters.persistence.repository;

import com.tekton.percentage.api.infraestructure.adapters.persistence.entity.PercentageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PercentageHistoryJPARepository extends JpaRepository<PercentageHistoryEntity, Long> {
}
