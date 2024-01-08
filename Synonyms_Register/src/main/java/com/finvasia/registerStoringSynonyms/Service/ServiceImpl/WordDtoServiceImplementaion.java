package com.finvasia.registerStoringSynonyms.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finvasia.registerStoringSynonyms.DTO.GivenWordDto;
import com.finvasia.registerStoringSynonyms.Entity.GivenWord;
import com.finvasia.registerStoringSynonyms.Entity.Meaning;
import com.finvasia.registerStoringSynonyms.Exception.WordNotFoundException;
import com.finvasia.registerStoringSynonyms.Repository.MeaningRepository;
import com.finvasia.registerStoringSynonyms.Repository.WordRepository;
import com.finvasia.registerStoringSynonyms.Service.WordDtoService;

import lombok.Data;

@Service
public class WordDtoServiceImplementaion implements WordDtoService {

	private static final String noMeaningfound = "Word '%s' does not exist!";
	
	
		
	@Autowired
	private WordRepository wordRepo;
	
	@Autowired
	private MeaningRepository meaningRepo;
	
	// Nitesh Work
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * Retrieving word from db.
     * @param value of the word object wanted
     * @return WordDTO object
     */
	
	@Override
	public GivenWordDto retrieveWord(String value) {
		// TODO Auto-generated method stub
		GivenWord word = wordRepo.findByWord(value).orElseThrow(
					()-> new WordNotFoundException(String.format(noMeaningfound, value)));
		return new GivenWordDto(word.getWord());
	}

	/**
     * Retrieving word from db.
     * @param value of the word object wanted
     * @return WordDTO object
     */
	
	@Override
	public List<GivenWordDto> retrieveSynonyms(String value) {
		// TODO Auto-generated method stub
		List<GivenWordDto> list = wordRepo.findSynonyms(value);
		return list;
	}
	
	/**
     * Creating new word in the db.
     * @param wordDTO word object
     * @return saved word
     */

	@Override
	public GivenWordDto createWord(GivenWordDto wordDto) {
		// TODO Auto-generated method stub
		wordRepo.findByWord(wordDto.getValue()).orElseGet(()-> saveWord(wordDto));
		return wordDto;
	}
	
	/**
     * Updating existing word with new value.
     * @param oldValue old string value of the word
     * @param wordDTO new word object
     * @return updated word
     */

	@Override
	public GivenWordDto updateWord(String oldValue, GivenWordDto wordDto) {
		// TODO Auto-generated method stub
		GivenWord word = wordRepo.findByWord(oldValue).orElseThrow(()-> new WordNotFoundException(String.format(noMeaningfound, oldValue)));
		word.setWord(wordDto.getValue());
		wordRepo.saveAndFlush(word);
		return wordDto;
	}
	
	/**
     * Assigning a synonym to the existing word.
     * @param existingWordValue word string value
     * @param synonymDTO synonym object (if not exist in db, will be created)
     * @return assigned synonym
     */

	@Override
	public GivenWordDto assignSynonyms(String existingWord, GivenWordDto wordDto) {
		// TODO Auto-generated method stub
		GivenWord word = wordRepo.findByWord(existingWord).orElseThrow(()-> new WordNotFoundException(String.format(noMeaningfound, existingWord)));
		GivenWord synonym = wordRepo.findByWord(wordDto.getValue()).orElseGet(()-> saveWord(wordDto));
		Meaning meaning = Optional.ofNullable(word.getMeaning())
							.orElseGet(()->{
								if(synonym.getMeaning() == null) {
									return new Meaning();
								}else {
									return synonym.getMeaning();
								}
							});
//		handling scenario when word and synonym both have meaning, removing synonym's meaning from db
		if(word.getMeaning() != null) {
			removedMeaning(synonym.getMeaning());
		}
		word.setMeaning(meaning);
		synonym.setMeaning(meaning);
		meaningRepo.save(meaning);
		wordRepo.save(synonym);
		wordRepo.saveAndFlush(word);
		
		return wordDto;
	}
	
	/**
     * Removing relation between word and synonym.
     * @param existingWordValue word string value
     * @param synonymDTO unattached synonym
     */
	
	@Override
	public void deassignSynonym(String existWord, GivenWordDto wordDto) {
		// TODO Auto-generated method stub
		GivenWord word = wordRepo.findByWord(existWord).orElseThrow(
						()-> new WordNotFoundException(String.format(noMeaningfound, existWord)));
		
		GivenWord synonym = wordRepo.findByWord(wordDto.getValue()).orElseThrow(
						()-> new WordNotFoundException(String.format(noMeaningfound, wordDto.getValue())));
		
		if(word.getMeaning()==synonym.getMeaning()) {
			synonym.setMeaning(null);
		}else {
			throw new UnsupportedOperationException(String.format(noMeaningfound, existWord));
		}
		

	}

	/**
     * Deleting a word from db.
     * @param value word string to be deleted
     */
	
	@Override
	public void deleteWord(String value) {
		// TODO Auto-generated method stub
		GivenWord word = wordRepo.findByWord(value).orElseThrow(
					()-> new WordNotFoundException(String.format(noMeaningfound, value)));
		wordRepo.delete(word);
	}
	
	private void removedMeaning(Meaning meaning) {
		// TODO Auto-generated method stub
		if(wordRepo.countWordByMeaning(meaning)==1) {
			meaningRepo.delete(meaning);
		}
	}

	
	private GivenWord saveWord(GivenWordDto wordDto) {
		// TODO Auto-generated method stub
		GivenWord word = new GivenWord();
		word.setWord(wordDto.getValue());
		wordRepo.save(word);
		return word;
	}

}

