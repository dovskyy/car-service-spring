package pl.sda.javagda25.spring.service.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Comment has to contain something!")
    private String content;

    @CreationTimestamp
    private LocalDateTime dateAdded;

    @ManyToOne
    private Account account;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    private Set<FixOrder> fixOrders;
}
