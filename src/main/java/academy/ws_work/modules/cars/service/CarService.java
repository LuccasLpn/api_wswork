package academy.ws_work.modules.cars.service;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.cars.repository.CarRepository;
import academy.ws_work.modules.cars.request.CarRequest;
import academy.ws_work.modules.cars.request.CarResponse;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.repository.FactoryRepository;
import academy.ws_work.modules.factories.service.FactoryService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final FactoryService factoryService;
    private final FactoryRepository factoryRepository;

    public CarResponse saveCars(CarRequest request){
        validateFactoriId(request);
        validateCarsDataInformed(request);
        var factories = factoryService.findById(request.getFactoriesId());
        var cars = carRepository.save(Car.of(request, factories));
        return CarResponse.of(cars);
    }

    public CarResponse update(CarRequest request, Integer id){
        validateInformedId(id);
        var factories = factoryService.findById(request.getFactoriesId());
        var cars = Car.of(request, factories);
        cars.setId(id);
        carRepository.save(cars);
        return CarResponse.of(cars);
    }

    public List<CarResponse> findAll(){
        return carRepository.findAll()
                .stream()
                .map(CarResponse::of)
                .collect(Collectors.toList());
    }

    public SuccessResponse delete(Integer id){
        carRepository.deleteById(id);
        return SuccessResponse.create("The Car was deleted: ");
    }

    public String upload(MultipartFile file){
        try {
            List<Car> carList = new ArrayList<>();
            InputStream inputStream = file.getInputStream();
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
            parseAllRecords.forEach(record -> {
                Factory factoryId = factoryService.findById(Integer.parseInt(record.getString("MARCA_ID")));
                Car build = Car.builder()
                        .id(Integer.parseInt(record.getString("ID")))
                        .factoryId(factoryId)
                        .model(record.getString("MODELO"))
                        .year(Integer.parseInt(record.getString("ANO")))
                        .fuel(record.getString("COMBUSTIVEL"))
                        .doors(Integer.parseInt(record.getString("NUM_PORTAS")))
                        .cost(Double.valueOf(record.getString("VALOR_FIPE")))
                        .color(record.getString("COR")).build();
                carList.add(build);
                carRepository.saveAll(carList);
            });
            return "Upload SuccessFull !!!";
        }catch (IOException e){
            throw new ValidationException("");
        }
    }

    private void validateCarsDataInformed(CarRequest request){
        if(isEmpty(request.getFuel())){
            throw new ValidationException("The Car fuel was not informed: ");
        }
        if(isEmpty(request.getColor())){
            throw new ValidationException("The Car Color was not informed: ");
        }
        if(isEmpty(request.getDoors())){
            throw new ValidationException("The Car Doors was not informed: ");
        }
        if(isEmpty(request.getModel())){
            throw new ValidationException("The Car Model was not informed: ");
        }
        if(isEmpty(request.getYear())){
            throw new ValidationException("The Car Year was not informed: ");
        }
    }

    private void validateFactoriId(CarRequest request){
        if(isEmpty(request.getFactoriesId())){
            throw new ValidationException("The Factorie id was not informed: ");
        }
    }

    private void validateInformedId(Integer id){
        if (isEmpty(id)){
            throw new ValidationException("The Car ID was not informed: ");
        }
    }

}
