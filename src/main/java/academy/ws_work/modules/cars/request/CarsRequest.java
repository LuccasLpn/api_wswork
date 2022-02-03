package academy.ws_work.modules.cars.request;

import lombok.Data;

@Data
public class CarsRequest {

    private Integer factoriesId;
    private String model;
    private Integer year;
    private String fuel;
    private Integer doors;
    private Double cost;
    private String color;

}
