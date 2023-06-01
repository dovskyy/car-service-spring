package pl.sda.javagda25.spring.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagda25.spring.service.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByBrand(String name);
}
