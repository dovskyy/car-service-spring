package pl.sda.javagda25.spring.service.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String modelName;
    private String description;
    private Integer yearProduced;

    private LocalDateTime dateAdded;

    @ManyToOne
    private Account account;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private List<FixOrder> fixOrders;

}
