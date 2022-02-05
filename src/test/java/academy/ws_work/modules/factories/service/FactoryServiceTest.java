package academy.ws_work.modules.factories.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.repository.FactoryRepository;
import academy.ws_work.modules.factories.request.FactoryPost;
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
import util.FactoryCreator;
import util.FactoryPostCreator;
import util.FactoryPutCreator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class FactoryServiceTest {

    @InjectMocks
    private FactoryService service;

    @Mock
    private FactoryRepository repository;


    @BeforeEach
    void setUp(){
        BDDMockito.when(repository.save(ArgumentMatchers.any(Factory.class)))
                .thenReturn(FactoryCreator.createdFactoryToBeSaved());

        BDDMockito.when(repository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.of(FactoryCreator.createdFactoryValid()));

        BDDMockito.doNothing().when(repository).delete(
                ArgumentMatchers.any(Factory.class));

        BDDMockito.when(repository.findByNameIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(List.of(FactoryCreator.createdFactoryValid()));

        BDDMockito.when(repository.findAll())
                .thenReturn(List.of(FactoryCreator.createdFactoryValid()));
    }
    @Test
    @DisplayName("Saved Return Factory When SuccessFull")
    void Saved_ReturnFactory_WhenSuccessFull(){
        Factory factory = service.save(FactoryPostCreator.createdPost());
        Assertions.assertThat(factory).isNotNull().isEqualTo(FactoryCreator.createdFactoryToBeSaved());
    }
    @Test
    @DisplayName("Delete Removes Factory When SuccessFull")
    void Delete_RemovesFactory_WhenSuccessFull(){
        Assertions.assertThatCode(()-> service.delete(1)).doesNotThrowAnyException();
    }
    @Test
    @DisplayName("Replace Update Factory When SuccessFull")
    void Replace_UpdateFactory_WhenSuccessFull(){
    Assertions.assertThatCode(()-> service.replace(FactoryPutCreator.createdFactoryPut()));
    }
    @Test
    @DisplayName("FindByNameIgnoreCase Return a List of Factory When SuccessFull")
    void FindByNameIgnoreCase_ReturnFactory_WhenSuccessFull(){
        String expectedName = FactoryCreator.createdFactoryValid().getName();
        List<Factory> factoryList = service.findByName(expectedName);
        Assertions.assertThat(factoryList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(factoryList.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("FindByNameIgnoreCase EmptyReturn a List of Factory When SuccessFull")
    void FindByNameIgnoreCase_EmptyReturnFactory_WhenSuccessFull(){
        BDDMockito.when(service.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Factory> factory = service.findByName("factory");
        Assertions.assertThat(factory)
                .isNotNull()
                .isEmpty();
    }
    @Test
    @DisplayName("findByIdOrThrowBadRequestException Return Factory When successful")
    void FindByIdOrThrowBadRequestException_ReturnsFactory_WhenSuccessful(){
        Integer expectedId = FactoryCreator.createdFactoryValid().getId();
        Factory factory = service.findByIdOrThrowBadRequestException(1);
        Assertions.assertThat(factory).isNotNull();
        Assertions.assertThat(factory.getId()).isNotNull().isEqualTo(expectedId);

    }
    @Test
    @DisplayName("findByIdOrThrowBadRequestException ThrowBadRequestException Return Factory When successful")
    void FindByIdOrThrowBadRequestException_ThrowBadRequestExceptionReturnsFactory_WhenSuccessful(){
        BDDMockito.when(repository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ValidationException.class)
                .isThrownBy (() -> service.findByIdOrThrowBadRequestException(1));
    }
    @Test
    @DisplayName("FindAll Return List Of Factory When SuccessFull")
    void FindAll_ReturnListFactory_WhenSuccessFull(){
        String expectedName = FactoryCreator.createdFactoryValid().getName();
        List<Factory> factories = service.listAllFactory();
        Assertions.assertThat(factories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(factories.get(0).getName()).isEqualTo(expectedName);
    }



}