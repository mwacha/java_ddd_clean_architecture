package tk.mwacha.domain.customer.validator;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.shared.notification.Error;
import tk.mwacha.domain.shared.validator.ValidatorInterface;

public class CustomerValidator implements ValidatorInterface<Customer> {
    @Override
    public void validate(Customer entity) {
        if (entity.getId() == null) {
            entity.getNotification().addError(Error.with("customer", "Id is required"));
        }

        if (entity.getId() == null || entity.getName().isEmpty()) {
            entity.getNotification().addError(Error.with("customer", "Name is required"));
        }
    }
}
