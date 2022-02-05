package academy.ws_work.modules.factories.controller;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.modules.factories.request.FactoryRequest;
import academy.ws_work.modules.factories.request.FactoryResponse;
import academy.ws_work.modules.factories.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/factories")
public class FactoryController {

    private final FactoryService factoryService;

    @PostMapping(path = "/save")
    public FactoryResponse save(@RequestBody FactoryRequest request){
        return factoryService.saveFactorie(request);
    }

    @GetMapping(path = "/findById/{id}")
    public FactoryResponse findById(@PathVariable Integer id){
        return factoryService.findByIdResponse(id);
    }

    @PutMapping(path = "/update/{id}")
    public FactoryResponse update(@RequestBody FactoryRequest request,
                                  @PathVariable Integer id) {
        return factoryService.update(request, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return factoryService.delete(id);
    }


    @GetMapping(path = "/findAll")
    public List<FactoryResponse> findAll() {
        return factoryService.findAll();
    }



}
