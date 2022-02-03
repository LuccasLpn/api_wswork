package academy.ws_work.modules.factories.controller;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.modules.factories.request.FactorieRequest;
import academy.ws_work.modules.factories.request.FactorieResponse;
import academy.ws_work.modules.factories.service.FactoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/factories")
public class FactoriesController {

    private final FactoriesService factoriesService;

    @PostMapping(path = "/save")
    public FactorieResponse save(@RequestBody FactorieRequest request){
        return factoriesService.saveFactorie(request);
    }

    @GetMapping(path = "/findById/{id}")
    public FactorieResponse findById(@PathVariable Integer id){
        return factoriesService.findByIdResponse(id);
    }

    @PutMapping(path = "/update/{id}")
    public FactorieResponse update(@RequestBody FactorieRequest request,
                                   @PathVariable Integer id) {
        return factoriesService.update(request, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return factoriesService.delete(id);
    }


    @GetMapping(path = "/findAll")
    public List<FactorieResponse> findAll() {
        return factoriesService.findAll();
    }



}
