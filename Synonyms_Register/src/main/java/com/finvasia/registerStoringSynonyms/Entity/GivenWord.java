package com.finvasia.registerStoringSynonyms.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class GivenWord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Word", unique = true)
	private String word;

	@ManyToOne
	@JoinColumn(name = "Meaning_ID")
	private Meaning meaning;
	
	

	public GivenWord() {
		super();
	}

	public GivenWord(Long id, String word, Meaning meaning) {
		super();
		this.id = id;
		this.word = word;
		this.meaning = meaning;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Meaning getMeaning() {
		return meaning;
	}

	public void setMeaning(Meaning meaning) {
		this.meaning = meaning;
	}

	@Override
	public String toString() {
		return "GivenWord [id=" + id + ", word=" + word + ", meaning=" + meaning + "]";
	}
	
	
	
	
	
	
	
	

}
