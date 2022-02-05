package academy.ws_work.modules.cars.controller;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.cars.request.CarPost;
import academy.ws_work.modules.cars.request.CarPut;
import academy.ws_work.modules.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @PostMapping(path = "/save") //test
    public ResponseEntity<Car> save(@RequestBody @Valid CarPost carPost) {
        return new ResponseEntity<>(carService.save(carPost), HttpStatus.CREATED);
    }

    @PostMapping(path = "/save/upload") // test
    public String uploadData(@RequestParam("file") MultipartFile file){
        try {
            return carService.upload(file);
        }
        catch (MultipartException e){
            throw new ValidationException("File Format");
        }
    }

    @DeleteMapping(path = "/delete/{id}") // test
    public ResponseEntity<Void>delete(@PathVariable Integer id){
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/findAll") // test
    public ResponseEntity<List<Car>> listAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(path = "/findById/{id}") //test
    public ResponseEntity<Car> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/findByModel/{model}")
    public ResponseEntity<Car> findByModel(@PathVariable String model) {
        return ResponseEntity.ok(carService.findByModel(model));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void>update(@RequestBody CarPut carPut){
        carService.update(carPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
