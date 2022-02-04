package academy.ws_work.modules.cars.domain;

import academy.ws_work.modules.cars.request.CarsRequest;
import academy.ws_work.modules.factories.domain.Factories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cars {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FACTORY_ID", nullable = false)
    private Factories factoriesId;

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



    public static Cars of(CarsRequest request, Factories factories){
        return Cars.builder()
                .factoriesId(factories)
                .model(request.getModel())
                .year(request.getYear())
                .fuel(request.getFuel())
                .doors(request.getDoors())
                .cost(request.getCost())
                .color(request.getColor()).build();
    }

}
