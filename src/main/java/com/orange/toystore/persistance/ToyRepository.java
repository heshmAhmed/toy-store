package com.orange.toystore.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<ToyEntity, Long> {
}
