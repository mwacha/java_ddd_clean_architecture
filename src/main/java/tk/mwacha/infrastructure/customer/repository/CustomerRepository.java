package tk.mwacha.infrastructure.customer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.infrastructure.customer.model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {}
