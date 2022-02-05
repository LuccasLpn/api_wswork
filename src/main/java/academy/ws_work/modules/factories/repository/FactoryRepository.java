package academy.ws_work.modules.factories.repository;

import academy.ws_work.modules.factories.domain.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer> {

    Optional<Factory> findByNameIgnoreCase(String name);



}
