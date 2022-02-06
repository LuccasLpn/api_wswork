package academy.ws_work.modules.cars.service;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.cars.repository.CarRepository;
import academy.ws_work.modules.cars.request.CarPut;
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
import util.CarCreator;
import util.CarPostCreator;
import util.CarPutCreator;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CarServiceTest {


    @InjectMocks
    private CarService service;


    @Mock
    private CarRepository repository;




    @BeforeEach
    void setUp(){

        BDDMockito.when(repository.save(ArgumentMatchers.any(Car.class)))
                .thenReturn(CarCreator.createdCarValid());

        BDDMockito.when(repository.findAll())
                .thenReturn(List.of(CarCreator.createdCarValid()));

        BDDMockito.when(repository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(CarCreator.createdCarValid()));

        BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any(Car.class));
    }

    @Test
    @DisplayName("Save Return Car When SuccessFull")
    void Save_ReturnCar_WhenSuccessFull(){
        Car save = service.save(CarPostCreator.createdValidPost());
        Assertions.assertThat(save).isNotNull().isEqualTo(CarCreator.createdCarValid());
    }

    @Test
    @DisplayName("FindAll Return Car Of When SuccessFull")
    void FindAll_ReturnCar_WhenSuccessFull(){
        String expectedModel = CarCreator.createdCarValid().getModel();
        List<Car> car = service.findAll();
        Assertions.assertThat(car).isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(car.get(0).getModel()).isEqualTo(expectedModel);
    }

    @Test
    @DisplayName("Delete Removes Car When SuccessFull")
    void Delete_RemovesCar_WhenSuccessFull(){
        Assertions.assertThatCode(()-> service.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Replace Update Car When SuccessFull")
    void Replace_UpdateCar_WhenSuccessFull(){
        Assertions.assertThatCode(()-> service.update(
                CarPutCreator.createdCarPutValid()));
    }

    @Test
    @DisplayName("FindByIdOrThrowBadRequestException Return Car When SuccessFull")
    void FindByIdOrThrowBadRequestException_ReturnCar_WhenSuccessFull(){
        Integer id = CarCreator.createdCarValid().getId();
        Car carId = service.findByIdOrThrowBadRequestException(1);
        Assertions.assertThat(carId).isNotNull();
        Assertions.assertThat(carId.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    @DisplayName("FindByIdOrThrowBadRequestException ThrowBadRequestException Return Car when Car Is Not Found")
    void FindByIdOrThrowBadRequestException_ThrowBadRequestException_WhenCarIsNotFound(){
        BDDMockito.when(repository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(()-> service.findByIdOrThrowBadRequestException(1));
    }



}