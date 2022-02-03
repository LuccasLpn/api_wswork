package academy.ws_work.modules.cars.domain;

import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.request.FactorieRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "FACTORY_ID", nullable = false)
    private Factories factories;
    private String model;
    private Integer year;
    private String fuel;
    private Integer doors;
    private Double cost;
    private String color;



    public static Cars of(CarsRequest request, Factories factories){
        return Cars.builder()
                .factories(factories)
                .model(request.getModel())
                .year(request.getYear())
                .fuel(request.getFuel())
                .doors(request.getDoors())
                .cost(request.getCost())
                .color(request.getColor()).build();
    }




}
