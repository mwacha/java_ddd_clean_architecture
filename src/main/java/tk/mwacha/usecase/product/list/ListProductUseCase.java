package tk.mwacha.usecase.product.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.product.repository.ProductRepositoryInterface;
@Service
@RequiredArgsConstructor
public class ListProductUseCase {

    private final ProductRepositoryInterface productRepository;

    public OutputListProductDTO execute(final InputListProductDTO input) {

        final var products = productRepository.findAll();

        return OutputListProductDTO.from(products);
    }
}
