package pl.sda.javagda25.spring.service.component;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.javagda25.spring.service.model.*;
import pl.sda.javagda25.spring.service.repository.*;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private AccountRepository accountRepository;
    private AccountRoleRepository accountRoleRepository;
    private CarRepository carRepository;
    private FixOrderRepository fixOrderRepository;
    private CommentRepository commentRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addDefaultRole("ADMIN");
        addDefaultRole("USER");

        addDefaultUser("admin@admin.com", "admin", "John", "Travolta", "0700", "ADMIN", "USER");
        addDefaultUser("user@user.com", "user", "Juan", "Pablo", "2137", "USER");

        addDefaultCar("AUDI", "A6", "Sedan", 2003);
        addDefaultComment("Clutch has to be changed");
    }

    private void addDefaultUser(String email, String password, String name, String surname, String phoneNumber, String... roles) {
        if (!accountRepository.existsByEmail(email)){
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(passwordEncoder.encode(password));
            account.setName(name);
            account.setSurname(surname);
            account.setPhoneNumber(phoneNumber);

            Set<AccountRole> userRoles = findRoles(roles);
            account.setAccountRoles(userRoles);

            accountRepository.save(account);
        }
    }

    private void addDefaultRole(String role) {
        if (!accountRoleRepository.existsByName(role)) {
            AccountRole newRole = new AccountRole();
            newRole.setName(role);

            accountRoleRepository.save(newRole);
        }
    }

    private void addDefaultCar(String brand, String modelName, String description, Integer yearProduced){
        if (!carRepository.existsByBrand(brand)) {
            Car car = new Car();
            car.setBrand(brand);
            car.setModelName(modelName);
            car.setDescription(description);
            car.setYearProduced(yearProduced);

            carRepository.save(car);
        }
    }

    private void addDefaultComment(String content){
        if (!commentRepository.existsByContent(content)) {
            Comment comment = new Comment();
            comment.setContent(content);

            commentRepository.save(comment);
        }

    }

    private void addDefaultFixOrder(String name, String description, OrderStatus status){
        if (!fixOrderRepository.existsByName(name) && !fixOrderRepository.existsByDescription(description)){
            FixOrder fixOrder = new FixOrder();
            fixOrder.setName(name);
            fixOrder.setDescription(description);
            fixOrder.setStatus(status);

            fixOrderRepository.save(fixOrder);
        }
    }

    private Set<AccountRole> findRoles(String[] roles) {
        Set<AccountRole> accountRoles = new HashSet<>();

        for (String role : roles) {
            accountRoleRepository.findByName(role).ifPresent(accountRoles::add);
        }
        return accountRoles;
    }

}
