package tk.mwacha.usecase.product.list;

import tk.mwacha.domain.product.entity.Product;

import java.util.List;

public record OutputListProductDTO(List<Product> products) {

    public static OutputListProductDTO from(final List<Product> products) {
        // Criar um CustomerDTO p/ retornar
        return new OutputListProductDTO(products);
    }
}
