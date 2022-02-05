package academy.ws_work.modules.cars.controller;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.modules.cars.repository.CarRepository;
import academy.ws_work.modules.cars.request.CarRequest;
import academy.ws_work.modules.cars.request.CarResponse;
import academy.ws_work.modules.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;


    @PostMapping(path = "/save")
    public CarResponse save (@RequestBody CarRequest request){
        return carService.saveCars(request);
    }

    @PutMapping("/update/{id}")
    public CarResponse update(@RequestBody CarRequest request, @PathVariable Integer id) {
        return carService.update(request, id);
    }

    @GetMapping(path = "/findAll")
    public List<CarResponse> findAll(){
        return carService.findAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public SuccessResponse delete(@PathVariable Integer id){
        return carService.delete(id);
    }

    @PostMapping(path = "/save/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception{
        return carService.upload(file);
    }

}
