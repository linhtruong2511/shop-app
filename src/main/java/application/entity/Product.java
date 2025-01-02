package application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.util.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Builder
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String code;
	private Double price;
	private Integer stockQuantity = 0;
	private String description;
	private boolean enabled = true;
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	private List<Image> images = new ArrayList<>();
	@JsonIgnore
	@JsonBackReference
	@ManyToMany(mappedBy = "products")
	private Set<User> users = new HashSet<>();
}
