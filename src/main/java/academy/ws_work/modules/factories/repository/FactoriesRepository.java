package academy.ws_work.modules.factories.repository;

import academy.ws_work.modules.factories.domain.Factories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoriesRepository extends JpaRepository<Factories, Integer> {

    List<Factories> findByNameIgnoreCaseContaining(String name);


}
