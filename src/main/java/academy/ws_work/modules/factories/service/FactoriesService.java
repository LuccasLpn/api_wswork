package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.SuccessResponse;
import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.validation.Validation;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.repository.FactoriesRepository;
import academy.ws_work.modules.factories.request.FactorieRequest;
import academy.ws_work.modules.factories.request.FactorieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class FactoriesService {

    private final FactoriesRepository factoriesRepository;

    private final Validation validation;

    public FactorieResponse findByIdResponse(Integer id){
       return FactorieResponse.of(findById(id));
    }

    public Factories findById(Integer id) {
        if(isEmpty(id)){
            throw new ValidationException("The Factorie ID was not informed: " + id);
        }
        return factoriesRepository.findById(id)
                .orElseThrow(()-> new ValidationException("There Factorie for given ID: " + id));
    }


    public FactorieResponse saveFactorie(FactorieRequest request){
        validateFactorieNameInforme(request);
        var factories = factoriesRepository.save(Factories.of(request));
        return FactorieResponse.of(factories);
    }

    public FactorieResponse update(FactorieRequest request, Integer id){
        validateFactorieNameInforme(request);
        var factories = Factories.of(request);
        factories.setId(id);
        factoriesRepository.save(factories);
        return FactorieResponse.of(factories);
    }

    public SuccessResponse delete(Integer id){
        validateInformedId(id);
        if(validation.existsByFactorieId(id)){
            throw new ValidationException("You cannot delete this Factorie because it's already defined by a Cars.");
        }
        factoriesRepository.deleteById(id);
        return SuccessResponse.create("The Factorie was deleted");
    }

    public List<FactorieResponse> findAll() {
        return factoriesRepository
                .findAll()
                .stream()
                .map(FactorieResponse::of)
                .collect(Collectors.toList());
    }

    private void validateFactorieNameInforme(FactorieRequest request){
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
