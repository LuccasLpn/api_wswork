package util;

import academy.ws_work.modules.factories.domain.Factory;

public class FactoryCreator {

    public static Factory createdFactoryToBeSaved(){
        return Factory
                .builder()
                .name("TOYOTA")
                .countrycode(55)
                .build();
    }

    public static Factory createdFactoryValid(){
        return Factory
                .builder()
                .id(1)
                .name("TOYOTA")
                .countrycode(55)
                .build();
    }

    public static Factory createdFactoryUpdate(){
        return Factory
                .builder()
                .id(1)
                .name("TOYOTA 2")
                .countrycode(55)
                .build();
    }
}
