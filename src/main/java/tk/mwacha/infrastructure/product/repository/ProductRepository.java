package tk.mwacha.infrastructure.product.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.infrastructure.product.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {}
