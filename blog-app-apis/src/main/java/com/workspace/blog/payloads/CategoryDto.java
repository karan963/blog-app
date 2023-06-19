package com.workspace.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min=5,message="Minimum size of a title must be 5 or more than five")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="Minimum size of Description must be 10 or more than ten")
	private String categoryDescription;
}
