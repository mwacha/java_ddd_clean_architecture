package tk.mwacha.usecase.product.find;

import java.util.UUID;

public record InputFindProductDTO(UUID id) {

    public static InputFindProductDTO with(final UUID id) {
        return new InputFindProductDTO(id);
    }
}
