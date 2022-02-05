package academy.ws_work.modules.cars.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarPost {

    private Integer factoryId;
    private String model;
    private String fuel;
    private Integer year;
    private Integer doors;
    private Double cost;
    private String color;



}
