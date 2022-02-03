package academy.ws_work.modules.cars.repository;

import academy.ws_work.modules.cars.domain.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CarsRepository extends JpaRepository <Cars,Integer> {

    Boolean existsByFactoriesId(Integer id);
}
