package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.repository.FactoriesRepository;
import academy.ws_work.modules.factories.request.FactorieRequest;
import academy.ws_work.modules.factories.request.FactorieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional
public class FactoriesService {

    private final FactoriesRepository factoriesRepository;



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
        validateSupplierNameInforme(request);
        var factories = factoriesRepository.save(Factories.of(request));
        return FactorieResponse.of(factories);
    }



    private void validateSupplierNameInforme(FactorieRequest request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The Factorie name was not informed");
        }
        if(isEmpty(request.getCountrycode())){
            throw new ValidationException("The Factorie countrycode was not informed");
        }
    }



}
