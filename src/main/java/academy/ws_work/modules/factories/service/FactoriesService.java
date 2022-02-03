package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.mapper.FactoriesMapper;
import academy.ws_work.modules.factories.repository.FactoriesRepository;
import academy.ws_work.modules.factories.request.FactoriesPost;
import academy.ws_work.modules.factories.request.FactoriesPut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional
public class FactoriesService {

    private final FactoriesRepository factoriesRepository;


    public Factories save(FactoriesPost factoriesPost){
        validateFactoriesNameInformed(factoriesPost);
        validateFactoriesCodeInformed(factoriesPost);
       return factoriesRepository.save(FactoriesMapper.INSTANCE.toPost(factoriesPost));
    }

    public Factories findById(Integer id){
       return factoriesRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There no Factorie for given ID - " + id));
    }

    public List<Factories> findByName(String name){
        if (isEmpty(name)){
            throw new ValidationException("The Factorie name must be informed: ");
        }
        return factoriesRepository.findByNameIgnoreCaseContaining(name);
    }

    public List<Factories> findAll(){
        return factoriesRepository.findAll();
    }

    public void replace(FactoriesPut factoriesPut){
        validateFactoriesNameInformedPut(factoriesPut);
        validateFactoriesCodeInformedPut(factoriesPut);
        Factories savedFactorie = findById(factoriesPut.getId());
        Factories factorie = FactoriesMapper.INSTANCE.toPut(factoriesPut);
        factorie.setId(savedFactorie.getId());
        factoriesRepository.save(factorie);
    }

    public void delete(Integer id){
        factoriesRepository.delete(findById(id));
    }


    private void validateFactoriesNameInformed(FactoriesPost factoriesPost){
        if(isEmpty(factoriesPost.getName())){
            throw new ValidationException("The Factories name was not informed");
        }
    }
    private void validateFactoriesNameInformedPut(FactoriesPut factoriesPut){
        if(isEmpty(factoriesPut.getName())){
            throw new ValidationException("The Factories name was not informed");
        }
    }
    private void validateFactoriesCodeInformedPut(FactoriesPut factoriesPut){
        if(isEmpty(factoriesPut.getCountrycode())){
            throw new ValidationException("The Factories Countrycode was not informed");
        }
    }
    private void validateFactoriesCodeInformed(FactoriesPost factoriesPost){
        if(isEmpty(factoriesPost.getCountrycode())){
            throw new ValidationException("The Factories Countrycode was not informed");
        }
    }



}
