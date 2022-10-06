package tk.mwacha.usecase.product.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.factory.ProductFactory;
import tk.mwacha.domain.product.repository.ProductRepositoryInterface;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepositoryInterface productRepository;

    public OutputCreateProductDTO execute(final InputCreateProductDTO input) {

        final var product = ProductFactory.create("a", input.name(), input.price());

        productRepository.create((Product) product);

        return OutputCreateProductDTO.from((Product) product);
    }
}
