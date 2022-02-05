package util;

import academy.ws_work.modules.factories.request.FactoryPost;

public class FactoryPostCreator {

    public static FactoryPost createdPost(){
        return FactoryPost.builder()
                .name(FactoryCreator.createdFactoryToBeSaved().getName())
                .countrycode(FactoryCreator.createdFactoryToBeSaved().getCountrycode())
                .build();
    }
}
