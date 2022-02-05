package academy.ws_work.modules.cars.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.cars.mapper.CarMapper;
import academy.ws_work.modules.cars.repository.CarRepository;
import academy.ws_work.modules.cars.request.CarPost;
import academy.ws_work.modules.cars.request.CarPut;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.service.FactoryService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarService {

    private final CarRepository carRepository;
    private final FactoryService factoryService;


    @Transactional
    public Car save(CarPost carPost) {
        validateFactoriId(carPost);
        validateCarsDataInformed(carPost);
        return carRepository.save(CarMapper.INSTANCE.toPost(carPost));
    }
    public Car findByIdOrThrowBadRequestException(Integer id){
        return carRepository.findById(id)
                .orElseThrow(()-> new ValidationException("Car Not found"));
    }
    public void delete(Integer id){
        carRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public Car findByModel(String model){
        return carRepository.findByModelIgnoreCase(model).orElseThrow(
                ()-> new ValidationException("There Car for given MODEL: " + model)
        );
    }

    public void update(CarPut carPut){
        validateInformedId(carPut.getId());
        Car savedCar = findByIdOrThrowBadRequestException(carPut.getId());
        Car car = CarMapper.INSTANCE.toPut(carPut);
        car.setId(savedCar.getId());
        carRepository.save(car);
    }

    public List<Car> findAll(){
        return carRepository.findAll();
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
                Factory factoryId = factoryService.findByIdOrThrowBadRequestException(
                        Integer.parseInt(record.getString("MARCA_ID")));
                Car build = Car.builder()
                        .id(Integer.parseInt(record.getString("ID")))
                        .factoryId(factoryId.getId())
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


    private void validateCarsDataInformed(CarPost request){
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
    private void validateFactoriId(CarPost request){
        if(isEmpty(request.getFactoryId())){
            throw new ValidationException("The Factorie id was not informed: ");
        }
    }
    private void validateInformedId(Integer id){
        if (isEmpty(id)){
            throw new ValidationException("The Car ID was not informed: ");
        }
    }




}
