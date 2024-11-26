package application.model.dto;

import application.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String name;
	private String code;
	private Double price;
	private Integer stockQuantity;
	private String description;
	@JsonIgnore
	private Set<User> users = new HashSet<>();
}
