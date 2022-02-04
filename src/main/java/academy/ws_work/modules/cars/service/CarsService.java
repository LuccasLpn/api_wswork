package academy.ws_work.modules.cars.service;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Cars;
import academy.ws_work.modules.cars.repository.CarsRepository;
import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.cars.request.CarsResponse;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.repository.FactoriesRepository;
import academy.ws_work.modules.factories.service.FactoriesService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;
    private final FactoriesService factoriesService;
    private final FactoriesRepository factoriesRepository;

    public CarsResponse saveCars(CarsRequest request){
        validateFactoriId(request);
        validateCarsDataInformed(request);
        var factories = factoriesService.findById(request.getFactoriesId());
        var cars = carsRepository.save(Cars.of(request, factories));
        return CarsResponse.of(cars);
    }

    public CarsResponse update(CarsRequest request, Integer id){
        validateInformedId(id);
        var factories = factoriesService.findById(request.getFactoriesId());
        var cars = Cars.of(request, factories);
        cars.setId(id);
        carsRepository.save(cars);
        return CarsResponse.of(cars);
    }

    public List<CarsResponse> findAll(){
        return carsRepository.findAll()
                .stream()
                .map(CarsResponse::of)
                .collect(Collectors.toList());
    }

    public SuccessResponse delete(Integer id){
        carsRepository.deleteById(id);
        return SuccessResponse.create("The Cars was deleted: ");
    }

    public String upload(MultipartFile file){
        try {
            List<Cars> carList = new ArrayList<>();
            InputStream inputStream = file.getInputStream();
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
            parseAllRecords.forEach(record -> {
                Factories factoryId = factoriesService.findById(Integer.parseInt(record.getString("MARCA_ID")));
                Cars build = Cars.builder()
                        .id(Integer.parseInt(record.getString("ID")))
                        .factoriesId(factoryId)
                        .model(record.getString("MODELO"))
                        .year(Integer.parseInt(record.getString("ANO")))
                        .fuel(record.getString("COMBUSTIVEL"))
                        .doors(Integer.parseInt(record.getString("NUM_PORTAS")))
                        .cost(Double.valueOf(record.getString("VALOR_FIPE")))
                        .color(record.getString("COR")).build();
                carList.add(build);
                carsRepository.saveAll(carList);
            });
            return "Upload SuccessFull !!!";
        }catch (IOException e){
            throw new ValidationException("");
        }
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

    private void validateInformedId(Integer id){
        if (isEmpty(id)){
            throw new ValidationException("The Cars ID was not informed: ");
        }
    }

}
