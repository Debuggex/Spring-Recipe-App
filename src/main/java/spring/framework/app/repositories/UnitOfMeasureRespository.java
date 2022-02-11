package spring.framework.app.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.framework.app.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRespository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
