package tk.mwacha.usecase.customer.list;

import tk.mwacha.domain.customer.entity.Customer;

import java.util.List;

public record OutputListCustomerDTO(List<Customer> customers) {

    public static OutputListCustomerDTO from(final List<Customer> customers) {
        // Criar um CustomerDTO p/ retornar
        return new OutputListCustomerDTO(customers);
    }
}
