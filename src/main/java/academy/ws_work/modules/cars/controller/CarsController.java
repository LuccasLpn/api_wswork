package academy.ws_work.modules.cars.controller;

import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.cars.request.CarsResponse;
import academy.ws_work.modules.cars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;



    @PostMapping(path = "/save")
    public CarsResponse save (@RequestBody CarsRequest request){
        return carsService.saveCars(request);
    }
}
