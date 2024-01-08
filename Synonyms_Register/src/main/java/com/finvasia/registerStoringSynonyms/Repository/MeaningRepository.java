package com.finvasia.registerStoringSynonyms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finvasia.registerStoringSynonyms.Entity.Meaning;

@Repository
public interface MeaningRepository extends JpaRepository<Meaning, Long>{

}
