package academy.ws_work.modules.factories.repository;

import academy.ws_work.modules.factories.domain.Factories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactoriesRepository extends JpaRepository<Factories, Integer> {

    Optional<Factories> findByNameIgnoreCase(String name);



}
