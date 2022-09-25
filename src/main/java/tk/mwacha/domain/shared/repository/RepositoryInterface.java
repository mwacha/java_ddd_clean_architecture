package tk.mwacha.domain.shared.repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<T> {

  void create(T entity);

  void update(T entity);

  T find(UUID id);

  List<T> findAll();
}
