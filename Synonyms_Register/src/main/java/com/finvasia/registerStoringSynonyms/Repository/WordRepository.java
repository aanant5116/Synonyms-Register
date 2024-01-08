package com.finvasia.registerStoringSynonyms.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finvasia.registerStoringSynonyms.DTO.GivenWordDto;
import com.finvasia.registerStoringSynonyms.Entity.GivenWord;
import com.finvasia.registerStoringSynonyms.Entity.Meaning;
@Repository
public interface WordRepository extends JpaRepository<GivenWord, Long>{
		
		Optional<GivenWord> findByWord(String word);
			
		@Query(value = "select new com.finvasia.registerStoringSynonyms.DTO.GivenWordDto(w.word) from GivenWord w " +
	            "where w.meaning =(select w1.meaning from GivenWord w1 where w1.word = :value and w1.meaning is not null) and w.word <> :value")
	    List<GivenWordDto> findSynonyms(String value);
		
		Long countWordByMeaning(Meaning meaning);

	}
