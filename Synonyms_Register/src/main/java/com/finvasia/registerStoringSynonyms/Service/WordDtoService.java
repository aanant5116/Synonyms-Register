package com.finvasia.registerStoringSynonyms.Service;

import java.util.List;

import com.finvasia.registerStoringSynonyms.DTO.GivenWordDto;

public interface WordDtoService {

	GivenWordDto retrieveWord(String value);

	List<GivenWordDto> retrieveSynonyms(String value);

	GivenWordDto createWord(GivenWordDto wordDto);

	GivenWordDto updateWord(String oldValue, GivenWordDto wordDto);

	GivenWordDto assignSynonyms(String existingWord, GivenWordDto wordDto);

	void deassignSynonym(String existWord, GivenWordDto wordDto);

	void deleteWord(String value);

}
