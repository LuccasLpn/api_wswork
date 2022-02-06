package util;

import academy.ws_work.modules.cars.request.CarPut;

public class CarPutCreator {


    public static CarPut createdCarPutValid(){
        return CarPut.builder()
                .factoryId(1)
                .model(CarCreator.createdCarValid().getModel())
                .fuel(CarCreator.createdCarValid().getFuel())
                .year(CarCreator.createdCarValid().getYear())
                .doors(CarCreator.createdCarValid().getDoors())
                .cost(CarCreator.createdCarValid().getCost())
                .color(CarCreator.createdCarValid().getColor())
                .build();
    }
}
