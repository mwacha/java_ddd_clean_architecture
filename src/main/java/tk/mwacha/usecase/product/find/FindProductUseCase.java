package tk.mwacha.usecase.product.find;

import lombok.RequiredArgsConstructor;
import tk.mwacha.domain.product.repository.ProductRepositoryInterface;

@RequiredArgsConstructor
public class FindProductUseCase {

    private final ProductRepositoryInterface productRepository;

    public OutputFindProductDTO execute(final InputFindProductDTO input) {

        final var product = productRepository.find(input.id());

        return OutputFindProductDTO.from(product);
    }
}
