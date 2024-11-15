package application.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;

	@ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Product> products;
}
