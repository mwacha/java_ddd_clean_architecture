package tk.mwacha.infrastructure.order.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.repository.OrderRepositoryInterface;
import tk.mwacha.infrastructure.order.model.OrderModel;
import tk.mwacha.infrastructure.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderGateway implements OrderRepositoryInterface {

  private final OrderRepository repository;

  @Override
  public void create(Order entity) {
    var order = OrderModel.of(entity);
    this.repository.save(order);
  }

  @Override
  public void update(Order entity) {
    var order = OrderModel.of(entity);
    this.repository.save(order);
  }

  @Override
  public Order find(UUID id) {
    var orderModel = repository.findById(id).orElseThrow(RuntimeException::new);

    return OrderModel.to(orderModel);
  }

  @Override
  public List<Order> findAll() {
    return repository.findAll().stream().map(OrderModel::to).collect(Collectors.toList());
  }

  public void createAll(List<Order> entities) {
    var orders = entities.stream().map(OrderModel::of).collect(Collectors.toList());
    this.repository.saveAll(orders);
  }
}
