package com.finvasia.registerStoringSynonyms.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
	@NoArgsConstructor

	public class GivenWordDto {
		
		public GivenWordDto(String value) {
			// TODO Auto-generated constructor stub
			this.value = value;
		}

		String value;

	}
