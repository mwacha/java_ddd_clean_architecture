package tk.mwacha.domain.customer.factory;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.validator.CustomerValidator;
import tk.mwacha.domain.shared.validator.ValidatorInterface;

public class CustomerValidatorFactory {

    public static ValidatorInterface<Customer> create() {
        return new CustomerValidator();
    }
}
