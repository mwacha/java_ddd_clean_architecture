package tk.mwacha.usecase.product.update;

import lombok.RequiredArgsConstructor;
import tk.mwacha.domain.product.repository.ProductRepositoryInterface;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateProductUseCase {

    private final ProductRepositoryInterface productRepository;

    public OutputUpdateProductDTO execute(final InputUpdateProductDTO input) {


        final var product = Optional.of(productRepository.find(input.id())).orElseThrow();

        product.changeName(input.name());
        product.changePrice(input.price());

        return OutputUpdateProductDTO.from(product);
    }
}
