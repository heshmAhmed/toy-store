package com.orange.toystore;

import com.orange.toystore.persistance.ToyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<ToyEntity, Long> {
}
