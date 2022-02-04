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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final Locale localeBR = new Locale( "pt", "BR" );
    private final  NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

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
        validateInformedId(id);
        if(carsRepository.existsByFactoriesId(id)){
            throw new ValidationException("The Factorie does not exists: ");
        }
        carsRepository.deleteById(id);
        return SuccessResponse.create("The Cars was deleted: ");
    }

    public String upload(MultipartFile file) throws Exception{
        List<Cars> carList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);

        parseAllRecords.forEach(record -> {
            Cars carsRequest = new Cars();
            Factories factory = factoriesService.findById(Integer.parseInt(record.getString("MARCA_ID")));
            carsRequest.setId(Integer.parseInt(record.getString("ID")));
            carsRequest.setFactoriesId(factory);
            carsRequest.setModel(record.getString("MODELO"));
            carsRequest.setYear(Integer.parseInt(record.getString("ANO")));
            carsRequest.setFuel(record.getString("COMBUSTIVEL"));
            carsRequest.setDoors(Integer.parseInt(record.getString("NUM_PORTAS")));
            carsRequest.setCost(Double.parseDouble(record.getString("VALOR_FIPE")));
            carsRequest.setColor(record.getString("COR"));
            carList.add(carsRequest);
        });

        carsRepository.saveAll(carList);
        return "Upload SuccessFull !!!";
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
