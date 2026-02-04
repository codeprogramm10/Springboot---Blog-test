package com.learncode.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	
	@NotBlank(message="The Category Title cannot be empty.")
	@Size(min=4,max = 20, message ="Category Title size can be minimum 4 and maximum 20 characters.")
	private String categoryTitle;
	
	@NotBlank(message="The Category Description cannot be empty.")
	@Size(min=4,max = 30, message ="Category Description size can be minimum 4 and maximum 30 characters.")
	private String categoryDescription;

}
