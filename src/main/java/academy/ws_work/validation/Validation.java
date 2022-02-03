package academy.ws_work.validation;

import academy.ws_work.modules.cars.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Validation {

    private final CarsRepository carsRepository;

    public Boolean existsByFactorieId(Integer categoryId) {
        return carsRepository.existsByFactoriesId(categoryId);
    }

}
