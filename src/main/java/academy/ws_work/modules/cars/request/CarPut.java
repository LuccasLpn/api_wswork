package academy.ws_work.modules.cars.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CarPut {

    private Integer id;
    private String model;
    private Integer year;
    private String fuel;
    private Integer doors;
    private Double cost;
    private String color;


}
