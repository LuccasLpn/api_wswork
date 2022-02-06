package util;

import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.factories.domain.Factory;

public class CarCreator {

    public static Car createdToBeSaved(){
        return Car.builder()
                .model("CORROLA")
                .year(2017)
                .fuel("FLEX")
                .doors(4)
                .cost(91.000)
                .color("BRANCA")
                .build();
    }

    public static Car createdCarValid(){
        return Car.builder()
                .id(1)
                .model("CORROLA")
                .year(2017)
                .fuel("FLEX")
                .doors(4)
                .cost(91.000)
                .color("BRANCA")
                .build();
    }


}
