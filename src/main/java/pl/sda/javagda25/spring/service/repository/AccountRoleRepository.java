package pl.sda.javagda25.spring.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.javagda25.spring.service.model.AccountRole;

import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    Optional<AccountRole> findByName(String role);

    boolean existsByName(String role);
}
