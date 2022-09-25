package tk.mwacha.usecase.customer.find;

import java.util.UUID;

public record InputFindCustomerDTO(UUID id) {

    public static InputFindCustomerDTO with(final UUID id) {
        return new InputFindCustomerDTO(id);
    }
}
