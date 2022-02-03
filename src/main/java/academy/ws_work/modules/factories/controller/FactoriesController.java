package academy.ws_work.modules.factories.controller;

import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.request.FactoriesPost;
import academy.ws_work.modules.factories.request.FactoriesPut;
import academy.ws_work.modules.factories.service.FactoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/factories")
public class FactoriesController {

    private final FactoriesService factoriesService;

    @PostMapping(path = "/save")
    public ResponseEntity<Factories>save(@RequestBody @Valid FactoriesPost factoriesPost){
        return new ResponseEntity<>(factoriesService.save(factoriesPost), HttpStatus.CREATED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Factories>findById(@PathVariable Integer id) {
        return ResponseEntity.ok(factoriesService.findById(id));
    }
    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<Factories>>findByName(@PathVariable String name){
        return ResponseEntity.ok(factoriesService.findByName(name));
    }
    @GetMapping(path = "/findall")
    public ResponseEntity<List<Factories>>listAll(){
        return ResponseEntity.ok(factoriesService.findAll());
    }
    @PutMapping(path = "/replace")
    public ResponseEntity<Void>replace(@RequestBody FactoriesPut factoriesPut){
        factoriesService.replace(factoriesPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        factoriesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
