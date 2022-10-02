package tk.mwacha.infrastructure.customer.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;
import tk.mwacha.infrastructure.customer.model.CustomerModel;
import tk.mwacha.infrastructure.customer.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerGateway implements CustomerRepositoryInterface {

  private final CustomerRepository repository;

  @Override
  public void create(Customer entity) {
    var customer = CustomerModel.of(entity);
    this.repository.saveAndFlush(customer);
  }

  @Override
  public void update(Customer entity) {
    var customer = CustomerModel.of(entity);
    this.repository.save(customer);
  }

  @Override
  public Customer find(UUID id) {
    var customerModel = repository.findById(id).orElseThrow(RuntimeException::new);

    return CustomerModel.to(customerModel);
  }

  @Override
  public List<Customer> findAll() {
    return repository.findAll().stream().map(CustomerModel::to).toList();
  }

  public void createAll(List<Customer> entities) {
    var customers = entities.stream().map(CustomerModel::of).toList();
    this.repository.saveAll(customers);
  }
}
