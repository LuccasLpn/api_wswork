package academy.ws_work.modules.cars.domain;

import academy.ws_work.modules.factories.domain.Factories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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





}
