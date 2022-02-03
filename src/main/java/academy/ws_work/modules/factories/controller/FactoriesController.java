package academy.ws_work.modules.factories.controller;

import academy.ws_work.modules.factories.request.FactorieRequest;
import academy.ws_work.modules.factories.request.FactorieResponse;
import academy.ws_work.modules.factories.service.FactoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
