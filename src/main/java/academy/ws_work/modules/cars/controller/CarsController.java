package academy.ws_work.modules.cars.controller;

import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.cars.request.CarsResponse;
import academy.ws_work.modules.cars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;



    @PostMapping(path = "/save")
    public CarsResponse save (@RequestBody CarsRequest request){

        return carsService.saveCars(request);
    }


    @PutMapping("/update/{id}")
    public CarsResponse update(@RequestBody CarsRequest request,
                                  @PathVariable Integer id) {
        return carsService.update(request, id);
    }

}
