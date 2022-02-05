package academy.ws_work.modules.factories.repository;

import academy.ws_work.modules.factories.domain.Factory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import util.FactoryCreator;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for FactoryRepository")
class FactoryRepositoryTest {

    @Autowired
    private FactoryRepository factoryRepository;


    @Test
    @DisplayName("Saved Persist factory when SuccessFull")
    public void save_PersistFactory_WhenSuccessFull(){
        Factory factory = FactoryCreator.createdFactoryToBeSaved();
        Factory savedFactory = this.factoryRepository.save(factory);
        Assertions.assertThat(savedFactory).isNotNull();
        Assertions.assertThat(savedFactory.getName()).isNotNull().isEqualTo(factory.getName());
        Assertions.assertThat(savedFactory.getCountrycode()).isNotNull().isEqualTo(factory.getCountrycode());
    }

    @Test
    @DisplayName("Saved Update factory when SuccessFull")
    public void save_UpdateFactory_WhenSuccessFull(){
        Factory factory = FactoryCreator.createdFactoryToBeSaved();
        Factory savedFactory = this.factoryRepository.save(factory);
        savedFactory.setName("BMW");
        Factory saveUpdate = this.factoryRepository.save(savedFactory);
        Assertions.assertThat(saveUpdate).isNotNull();
        Assertions.assertThat(saveUpdate.getName()).isNotNull().isEqualTo(savedFactory.getName());
        Assertions.assertThat(saveUpdate.getCountrycode()).isNotNull().isEqualTo(savedFactory.getCountrycode());
    }

    @Test
    @DisplayName("Delete removes factory when SuccessFull")
    public void delete_RemoveFactory_WhenSuccessFull(){
        Factory factory = FactoryCreator.createdFactoryToBeSaved();
        Factory save = this.factoryRepository.save(factory);
        this.factoryRepository.delete(save);
        Optional<Factory> factoryOptional = this.factoryRepository.findById(save.getId());
        Assertions.assertThat(factoryOptional.isEmpty());
    }

    @Test
    @DisplayName("FindName Return factory")
    void findByModel_WhenSuccessFull(){
        Factory factory = FactoryCreator.createdFactoryToBeSaved();
        Factory savedFactory = this.factoryRepository.save(factory);
        String name = savedFactory.getName();
        List<Factory> byNameIgnoreCase = this.factoryRepository.findByNameIgnoreCase(name);
        Assertions.assertThat(byNameIgnoreCase)
                .isNotEmpty()
                .contains(savedFactory);
    }

    @Test
    @DisplayName("FindByName Return empty factory")
    void findByModel_ReturnEmpty(){
        List<Factory> name = this.factoryRepository.findByNameIgnoreCase("xaxaxaxa");
        Assertions.assertThat(name).isEmpty();
    }

}