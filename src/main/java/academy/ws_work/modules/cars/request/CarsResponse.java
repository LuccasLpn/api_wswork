package academy.ws_work.modules.cars.request;

import academy.ws_work.modules.cars.domain.Cars;
import academy.ws_work.modules.factories.request.FactorieResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarsResponse {

    private Integer id;
    private FactorieResponse factorieiD;
    private String model;
    private Integer year;
    private String fuel;
    private Integer doors;
    private Double cost;
    private String color;


    public static CarsResponse of(Cars cars){
        return CarsResponse.builder()
                .id(cars.getId())
                .factorieiD(FactorieResponse.of(cars.getFactoriesId()))
                .model(cars.getModel())
                .year(cars.getYear())
                .fuel(cars.getFuel())
                .doors(cars.getDoors())
                .cost(cars.getCost())
                .color(cars.getColor()).build();
    }
}
