package academy.ws_work.modules.cars.controller;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.modules.cars.repository.CarsRepository;
import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.cars.request.CarsResponse;
import academy.ws_work.modules.cars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;
    private final CarsRepository carsRepository;


    @PostMapping(path = "/save")
    public CarsResponse save (@RequestBody CarsRequest request){
        return carsService.saveCars(request);
    }

    @PutMapping("/update/{id}")
    public CarsResponse update(@RequestBody CarsRequest request,@PathVariable Integer id) {
        return carsService.update(request, id);
    }

    @GetMapping(path = "/findAll")
    public List<CarsResponse> findAll(){
        return carsService.findAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public SuccessResponse delete(@PathVariable Integer id){
        return carsService.delete(id);
    }

    @PostMapping(path = "/save/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception{
        return carsService.upload(file);
    }

}
