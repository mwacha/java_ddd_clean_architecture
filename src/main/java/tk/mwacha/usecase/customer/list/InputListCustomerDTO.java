package tk.mwacha.usecase.customer.list;

import java.util.UUID;

public record InputListCustomerDTO() {

    public static InputListCustomerDTO with() {
        return new InputListCustomerDTO();
    }
}
