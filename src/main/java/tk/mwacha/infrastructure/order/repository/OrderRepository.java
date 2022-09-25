package tk.mwacha.infrastructure.order.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.infrastructure.order.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {}
