package com.finvasia.registerStoringSynonyms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvasia.registerStoringSynonyms.DTO.GivenWordDto;
import com.finvasia.registerStoringSynonyms.Service.ServiceImpl.WordDtoServiceImplementaion;

@RestController
@RequestMapping("/v3/word")
public class WordController {

	@Autowired
	private WordDtoServiceImplementaion wordService;

	@PostMapping("/")
	public GivenWordDto createWord(@RequestBody GivenWordDto GivenWordDto) {
		return wordService.createWord(GivenWordDto);
	}

	@GetMapping("/{value}")
	public ResponseEntity<GivenWordDto> fetchWord(@PathVariable String value) {
		GivenWordDto word = wordService.retrieveWord(value);
		return new ResponseEntity<>(word, HttpStatus.OK);
	}

	@GetMapping("/{value}/synonyms")
	public ResponseEntity<List<GivenWordDto>> fetchAllSynonyms(@PathVariable String value) {
		List<GivenWordDto> synonymlist = wordService.retrieveSynonyms(value);
		return new ResponseEntity<>(synonymlist, HttpStatus.OK);
	}

	@PutMapping("/{value}/assign")
	public ResponseEntity<GivenWordDto> assignWord(@PathVariable String value, @RequestBody GivenWordDto GivenWordDto) {
		GivenWordDto word = wordService.assignSynonyms(value, GivenWordDto);
		return new ResponseEntity<>(word, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{value}/update")
	public ResponseEntity<GivenWordDto> update(@PathVariable String value, @RequestBody GivenWordDto GivenWordDto) {
		GivenWordDto word = wordService.updateWord(value, GivenWordDto);
		return new ResponseEntity<>(word, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{value}")
	public ResponseEntity<Void> delete(@PathVariable String value) {
		wordService.deleteWord(value);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{value}/deassign")
	public ResponseEntity<Void> deassignWord(@PathVariable String value, GivenWordDto GivenWordDto) {
		wordService.deassignSynonym(value, GivenWordDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
