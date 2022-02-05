package academy.ws_work.util;

import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.request.FactoryRequest;
import academy.ws_work.modules.factories.request.FactoryResponse;

public class FactoryCreator {


    public static FactoryRequest createdFactoryToBeSaved(){
        return FactoryRequest.builder()
                .name("TOYOTA")
                .countrycode(55)
                .build();
    }
    public static Factory createdFactoryValid() {
        return Factory.builder()
                .id(1)
                .name("TOYOTA")
                .countrycode(55)
                .build();
    }

    public static FactoryResponse createdFactoryResponse(){
        return FactoryResponse.builder()
                .id(1)
                .name("TOYOTA")
                .countrycode(55)
                .build();
    }


}