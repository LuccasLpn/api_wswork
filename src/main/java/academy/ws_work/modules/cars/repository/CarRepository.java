package academy.ws_work.modules.cars.repository;

import academy.ws_work.modules.cars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository <Car,Integer> {

    Boolean existsByFactoryId(Integer id);

    Optional<Car> findByModelIgnoreCase(String model);
}
