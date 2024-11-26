package application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dod;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
    @JsonIgnore
    private boolean enabled = true;
    public boolean isSupplier(){
        for(Role role : roles){
            if(role.getName().equals("SUPPLIER"))
                return true;
        }
        return false;
    }
    @OneToMany(mappedBy = "userID" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
}
