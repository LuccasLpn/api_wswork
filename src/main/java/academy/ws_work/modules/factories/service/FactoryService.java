package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.factories.mapper.FactoryMapper;
import academy.ws_work.modules.factories.request.FactoryPost;
import academy.ws_work.modules.factories.request.FactoryPut;
import academy.ws_work.validation.Validation;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.repository.FactoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;

    private final Validation validation;


    @Transactional
    public Factory save(FactoryPost factoryPost) {
        validateFactorieNameInforme(factoryPost);
        return factoryRepository.save(FactoryMapper.INSTANCE.toPost(factoryPost));
    }

    public Factory findByIdOrThrowBadRequestException(Integer id) {
        return factoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Factory not Found"));
    }

    public void delete(Integer id){
        validateInformedId(id);
        if(validation.existsByFactorieId(id)){
            throw new ValidationException("You cannot delete this Factorie because it's already defined by a Car.");
        }
        factoryRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(FactoryPut factoryPut) {
        validateInformedId(factoryPut.getId());
        Factory savedFactory = findByIdOrThrowBadRequestException(factoryPut.getId());
        Factory factory = FactoryMapper.INSTANCE.toPut(factoryPut);
        factory.setId(savedFactory.getId());
        factoryRepository.save(factory);
    }

    public List<Factory> findByName(String name){

        return factoryRepository.findByNameIgnoreCase(name);
    }

    public List<Factory> listAllFactory(){

        return factoryRepository.findAll();
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }

    private void validateFactorieNameInforme(FactoryPost request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The Factory name was not informed");
        }

        if(isEmpty(request.getCountrycode())){
            throw new ValidationException("The Factory countrycode was not informed");
        }

    }


}
