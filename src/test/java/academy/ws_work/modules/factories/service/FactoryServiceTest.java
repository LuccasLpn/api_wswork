package academy.ws_work.modules.factories.service;

import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.repository.FactoryRepository;
import academy.ws_work.modules.factories.request.FactoryResponse;
import academy.ws_work.util.FactoryCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class FactoryServiceTest {

    @InjectMocks
    private FactoryService factoryService;

    @Mock
    private FactoryRepository factoryRepository;


    @BeforeEach
    void setUp(){

        BDDMockito.when(factoryRepository.save(ArgumentMatchers.any(Factory.class)))
                .thenReturn(FactoryCreator.createdFactoryValid());

        BDDMockito.doNothing().when(factoryRepository).delete(
                ArgumentMatchers.any(Factory.class));

        BDDMockito.when(factoryRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(FactoryCreator.createdFactoryValid()));

    }

    @Test
    @DisplayName("Save Return Factory When SuccessFull")
    void Save_ReturnFactory_WhenSuccessFull(){
        FactoryResponse factoryResponse = factoryService.saveFactorie(FactoryCreator.createdFactoryToBeSaved());
        Assertions.assertThat(factoryResponse).isNotNull().isEqualTo(FactoryCreator.createdFactoryResponse());
    }


}