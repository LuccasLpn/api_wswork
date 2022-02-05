package academy.ws_work.modules.cars.domain;

import academy.ws_work.modules.factories.domain.Factory;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FACTORY_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Factory factory;

    @Column(name = "FACTORY_ID", insertable = false, updatable = false)
    private Integer factoryId;

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








}
