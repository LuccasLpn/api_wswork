package academy.ws_work.modules.cars.repository;

import academy.ws_work.modules.cars.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import util.CarCreator;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testing For CarRepository")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;



    @Test
    @DisplayName("Saved Persist Car When SuccessFull")
    public void Saved_PersistCar_WhenSuccessFull(){
        Car car = CarCreator.createdToBeSaved();
        Car savedCar = this.carRepository.save(car);
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getColor()).isNotNull().isEqualTo(car.getColor());
        Assertions.assertThat(savedCar.getCost()).isNotNull().isEqualTo(car.getCost());
        Assertions.assertThat(savedCar.getDoors()).isNotNull().isEqualTo(car.getDoors());
        Assertions.assertThat(savedCar.getFuel()).isNotNull().isEqualTo(car.getFuel());
        Assertions.assertThat(savedCar.getModel()).isNotNull().isEqualTo(car.getModel());
        Assertions.assertThat(savedCar.getYear()).isNotNull().isEqualTo(car.getYear());
    }

    @Test
    @DisplayName("Saved Update Car When SuccessFull")
    public void Saved_UpdateCar_WhenSuccessFull(){
        Car car = CarCreator.createdToBeSaved();
        Car savedCar = this.carRepository.save(car);
        savedCar.setModel("BMW");
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getModel()).isNotNull().isEqualTo(car.getModel());
    }

    @Test
    @DisplayName("Delete Removes Car When SuccessFull")
    public void Delete_RemovesCar_WhenSuccessFull(){
        Car car = CarCreator.createdToBeSaved();
        Car savedCar = this.carRepository.save(car);
        this.carRepository.delete(savedCar);
        Optional<Car> carOptional = this.carRepository.findById(savedCar.getId());
        Assertions.assertThat(carOptional.isEmpty());
    }

    @Test
    @DisplayName("FindByModel Return Optional of Car WhenSuccessFull")
    void FindByModel_ReturnCar_WhenSuccessFull(){
        Car car = CarCreator.createdToBeSaved();
        Car savedCar = this.carRepository.save(car);
        String model = savedCar.getModel();
        Optional<Car> findModel = this.carRepository.findByModelIgnoreCase(model);
        Assertions.assertThat(findModel)
                .isNotEmpty()
                .contains(savedCar);
    }

    @Test
    @DisplayName("FindByModel Return Empty Optional of Car WhenSuccessFull")
    void FindByModel_EmptyReturnCar_WhenSuccessFull(){
        Optional<Car> carOptional = this.carRepository.findByModelIgnoreCase("adadada");
        Assertions.assertThat(carOptional).isEmpty();
    }

    @Test
    @DisplayName("Saved throw ConstraintViolationException when Car is empty")
    void Saved_Throw(){
        Car car = new Car();
        Assertions.assertThatThrownBy(()-> this.carRepository.save(car))
                .isInstanceOf(ConstraintViolationException.class);
    }





}