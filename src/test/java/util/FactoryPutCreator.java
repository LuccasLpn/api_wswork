package util;

import academy.ws_work.modules.factories.request.FactoryPut;

public class FactoryPutCreator {

    public static FactoryPut createdFactoryPut(){
        return FactoryPut.builder()
                .id(FactoryCreator.createdFactoryValid().getId())
                .name(FactoryCreator.createdFactoryValid().getName())
                .countrycode(FactoryCreator.createdFactoryValid().getCountrycode())
                .build();
    }
}
