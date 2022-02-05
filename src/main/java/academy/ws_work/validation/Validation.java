package academy.ws_work.validation;

import academy.ws_work.modules.cars.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Validation {

    private final CarRepository carRepository;

    public Boolean existsByFactorieId(Integer categoryId) {
        return carRepository.existsByFactoryId(categoryId);
    }

}
