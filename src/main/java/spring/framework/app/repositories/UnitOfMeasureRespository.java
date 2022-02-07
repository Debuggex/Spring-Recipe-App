package spring.framework.app.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.framework.app.domain.UnitOfMeasure;

public interface UnitOfMeasureRespository extends CrudRepository<UnitOfMeasure, Long> {
}
