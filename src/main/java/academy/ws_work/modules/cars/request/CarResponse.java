package academy.ws_work.modules.cars.request;

import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.factories.request.FactoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Integer id;
    private FactoryResponse factorieiD;
    private String model;
    private Integer year;
    private String fuel;
    private Integer doors;
    private Double cost;
    private String color;


    public static CarResponse of(Car car){
        return CarResponse.builder()
                .id(car.getId())
                .factorieiD(FactoryResponse.of(car.getFactoryId()))
                .model(car.getModel())
                .year(car.getYear())
                .fuel(car.getFuel())
                .doors(car.getDoors())
                .cost(car.getCost())
                .color(car.getColor()).build();
    }
}
