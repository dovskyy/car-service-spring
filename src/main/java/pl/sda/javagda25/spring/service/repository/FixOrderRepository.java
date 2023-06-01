package pl.sda.javagda25.spring.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagda25.spring.service.model.FixOrder;

@Repository
public interface FixOrderRepository extends JpaRepository<FixOrder, Long> {
    boolean existsByName(String name);

    boolean existsByDescription(String description);
}
