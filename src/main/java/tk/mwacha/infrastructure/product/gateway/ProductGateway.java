package tk.mwacha.infrastructure.product.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.repository.ProductRepositoryInterface;
import tk.mwacha.infrastructure.product.model.ProductModel;
import tk.mwacha.infrastructure.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductGateway implements ProductRepositoryInterface {

  private final ProductRepository repository;

  @Override
  public void create(Product entity) {
    var product = ProductModel.of(entity);
    this.repository.save(product);
  }

  @Override
  public void update(Product entity) {
    var product = ProductModel.of(entity);
    this.repository.save(product);
  }

  @Override
  public Product find(UUID id) {
    var productModel = repository.findById(id).orElseThrow(RuntimeException::new);

    return ProductModel.to(productModel);
  }

  @Override
  public List<Product> findAll() {
    return repository.findAll().stream().map(ProductModel::to).collect(Collectors.toList());
  }

  public void createAll(List<Product> entities) {
    var products = entities.stream().map(ProductModel::of).collect(Collectors.toList());
    this.repository.saveAll(products);
  }
}
