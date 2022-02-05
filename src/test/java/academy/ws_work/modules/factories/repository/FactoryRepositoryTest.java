package academy.ws_work.modules.factories.repository;

import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.request.FactoryRequest;
import academy.ws_work.modules.factories.request.FactoryResponse;
import academy.ws_work.util.FactoryCreator;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;


@DataJpaTest
@DisplayName("Test for FactoryRepository")
class FactoryRepositoryTest {

    @Autowired
    private FactoryRepository repository;


    @Test
    @DisplayName("Saved Persist Factory When SuccessFull")
    public void save_PersistFactory_WhenSuccessFull(){
        FactoryRequest saved = FactoryCreator.createdFactoryToBeSaved();
        Factory savedFactory = this.repository.save(Factory.of(saved));
        Assertions.assertThat(savedFactory).isNotNull();
        Assertions.assertThat(savedFactory.getId()).isNotNull();
        Assertions.assertThat(savedFactory.getName()).isNotNull().isEqualTo(saved.getName());
        Assertions.assertThat(savedFactory.getCountrycode()).isNotNull().isEqualTo(saved.getCountrycode());
    }

    @Test
    @DisplayName("Saved Update Factory When SuccessFull")
    public void save_UpdateFactory_WhenSuccessFull(){
        FactoryRequest saved = FactoryCreator.createdFactoryToBeSaved();
        Factory factory = this.repository.save(Factory.of(saved));
        factory.setName("BMW");
        Factory factoryUpdate = this.repository.save(factory);
        Assertions.assertThat(factoryUpdate).isNotNull();
        Assertions.assertThat(factoryUpdate.getId()).isNotNull();
        Assertions.assertThat(factoryUpdate.getId()).isNotNull().isEqualTo(factory.getId());
        Assertions.assertThat(factoryUpdate.getName()).isNotNull().isEqualTo(factory.getName());
    }

    @Test
    @DisplayName("Delete Removes Factory When SuccessFull")
    public void delete_RemoveFactory_WhenSuccessFull(){
        FactoryRequest saved = FactoryCreator.createdFactoryToBeSaved();
        Factory savedFactory = this.repository.save(Factory.of(saved));
        this.repository.deleteById(savedFactory.getId());
        Optional<Factory> factoryOptional = this.repository.findById(savedFactory.getId());
        Assertions.assertThat(factoryOptional.isEmpty());
    }

    @Test
    @DisplayName("Saved Throw ConstraintViolationException When Name Is Empty")
    public void save_ThrowConstraintViolationException_WhenIsEmpty(){
        Factory factory = new Factory();
        Assertions.assertThatThrownBy(() -> this.repository.save(factory))
                .isInstanceOf(ConstraintViolationException.class);
    }

}