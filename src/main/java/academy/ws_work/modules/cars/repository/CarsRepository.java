package academy.ws_work.modules.cars.repository;

import academy.ws_work.modules.cars.domain.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CarsRepository extends JpaRepository <Cars,Integer> {

    Boolean existsByFactoriesId(Integer id);

}
