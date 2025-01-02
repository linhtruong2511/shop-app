package application.entity;

import application.common.OrderEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code = UUID.randomUUID().toString();
	private LocalDateTime date;
	@Enumerated(EnumType.STRING)
	private OrderEnum orderStatus;
	private Double totalAmount;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	@JsonManagedReference
	private List<OrderItem> orderItems;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userID;
	public String toString(){
		return "order";
	}
}
