package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.validation.Validation;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.repository.FactoryRepository;
import academy.ws_work.modules.factories.request.FactoryRequest;
import academy.ws_work.modules.factories.request.FactoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;

    private final Validation validation;

    public FactoryResponse findByIdResponse(Integer id){
       return FactoryResponse.of(findById(id));
    }

    public Factory findById(Integer id) {
        if(isEmpty(id)){
            throw new ValidationException("The Factorie ID was not informed: " + id);
        }
        return factoryRepository.findById(id)
                .orElseThrow(()-> new ValidationException("There Factorie for given ID: " + id));
    }

    public FactoryResponse saveFactorie(FactoryRequest request){
        validateFactorieNameInforme(request);
        var factories = factoryRepository.save(Factory.of(request));
        return FactoryResponse.of(factories);
    }
    public FactoryResponse update(FactoryRequest request, Integer id){
        validateFactorieNameInforme(request);
        var factories = Factory.of(request);
        factories.setId(id);
        factoryRepository.save(factories);
        return FactoryResponse.of(factories);
    }

    public SuccessResponse delete(Integer id){
        validateInformedId(id);
        if(validation.existsByFactorieId(id)){
            throw new ValidationException("You cannot delete this Factorie because it's already defined by a Car.");
        }
        factoryRepository.deleteById(id);
        return SuccessResponse.create("The Factorie was deleted");
    }
    public List<FactoryResponse> findAll() {
        return factoryRepository
                .findAll()
                .stream()
                .map(FactoryResponse::of)
                .collect(Collectors.toList());
    }

    public Factory findByName(String name){
        if (isEmpty(name)){
            throw new ValidationException("The Factorie Name must be informed: ");
        }
        return factoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(()-> new ValidationException("There Factorie for given NAME: " + name));

    }

    private void validateFactorieNameInforme(FactoryRequest request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The Factorie name was not informed");
        }
        if(isEmpty(request.getCountrycode())){
            throw new ValidationException("The Factorie countrycode was not informed");
        }
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }









}
