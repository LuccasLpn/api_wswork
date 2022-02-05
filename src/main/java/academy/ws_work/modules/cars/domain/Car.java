package academy.ws_work.modules.cars.domain;

import academy.ws_work.modules.cars.request.CarRequest;
import academy.ws_work.modules.factories.domain.Factory;
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
public class Car {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MARCA_ID", nullable = false)
    private Factory factoryId;

    @Column(name = "MODELO")
    private String model;

    @Column(name = "ANO")
    private Integer year;

    @Column(name = "COMBUSTIVEL")
    private String fuel;

    @Column(name = "NUM_PORTAS")
    private Integer doors;

    @Column(name = "VALOR_FIPE")
    private Double cost;

    @Column(name = "COR")
    private String color;



    public static Car of(CarRequest request, Factory factory){
        return Car.builder()
                .factoryId(factory)
                .model(request.getModel())
                .year(request.getYear())
                .fuel(request.getFuel())
                .doors(request.getDoors())
                .cost(request.getCost())
                .color(request.getColor()).build();
    }

}