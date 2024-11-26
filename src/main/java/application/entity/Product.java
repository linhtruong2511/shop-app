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

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Builder
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String code;
	private Double price;
	private Integer stockQuantity = 0;
	private String description;
	private boolean enabled = true;
	@JsonBackReference
	@ManyToMany(mappedBy = "products")
	private Set<User> users = new HashSet<>();
	public String toString(){
		return "";
	}
}
