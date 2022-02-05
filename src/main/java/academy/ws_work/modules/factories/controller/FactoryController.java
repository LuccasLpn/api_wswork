package academy.ws_work.modules.factories.controller;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.request.FactoryPost;
import academy.ws_work.modules.factories.request.FactoryPut;
import academy.ws_work.modules.factories.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/factory")
public class FactoryController {

    private final FactoryService factoryService;

    @PostMapping(path = "/save")
    public ResponseEntity<Factory>save(@RequestBody @Valid FactoryPost factoryPost){
        return new ResponseEntity<>(factoryService.save(factoryPost), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        factoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/replace")
    public ResponseEntity<Void> replace(@RequestBody FactoryPut factoryPut) {
        factoryService.replace(factoryPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<Factory>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(factoryService.findByName(name));
    }
    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Factory>> listAll() {
        return ResponseEntity.ok(factoryService.listAllFactory());
    }

}
