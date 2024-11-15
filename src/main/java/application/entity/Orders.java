package application.entity;

import application.common.OrderEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code = UUID.randomUUID().toString();
	private LocalDateTime date;
	@Enumerated(EnumType.STRING)
	private OrderEnum orderStatus;
	private Double totalAmount;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	@JsonManagedReference
	private List<OrderItem> orderItems;

	public String toString(){
		return "order";
	}
}
