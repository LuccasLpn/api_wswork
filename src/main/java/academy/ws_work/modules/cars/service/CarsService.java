package academy.ws_work.modules.cars.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Cars;
import academy.ws_work.modules.cars.repository.CarsRepository;
import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.cars.request.CarsResponse;
import academy.ws_work.modules.factories.service.FactoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;
    private final FactoriesService factoriesService;



    public CarsResponse saveCars(CarsRequest request){
        validateFactoriId(request);
        validateCarsDataInformed(request);
        var factories = factoriesService.findById(request.getFactoriesId());
        var cars = carsRepository.save(Cars.of(request, factories));
        return CarsResponse.of(cars);
    }
    public CarsResponse update(CarsRequest request, Integer id){
        var factories = factoriesService.findById(request.getFactoriesId());
        var cars = Cars.of(request, factories);
        cars.setId(id);
        carsRepository.save(cars);
        return CarsResponse.of(cars);
    }



    private void validateCarsDataInformed(CarsRequest request){
        if(isEmpty(request.getFuel())){
            throw new ValidationException("The Cars fuel was not informed: ");
        }
        if(isEmpty(request.getColor())){
            throw new ValidationException("The Cars Color was not informed: ");
        }
        if(isEmpty(request.getDoors())){
            throw new ValidationException("The Cars Doors was not informed: ");
        }
        if(isEmpty(request.getModel())){
            throw new ValidationException("The Cars Model was not informed: ");
        }
        if(isEmpty(request.getYear())){
            throw new ValidationException("The Cars Year was not informed: ");
        }
    }
    private void validateFactoriId(CarsRequest request){
        if(isEmpty(request.getFactoriesId())){
            throw new ValidationException("The Factorie id was not informed: ");
        }
    }







}
