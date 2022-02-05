package academy.ws_work.modules.cars.mapper;

import academy.ws_work.modules.cars.domain.Car;
import academy.ws_work.modules.cars.request.CarPost;
import academy.ws_work.modules.cars.request.CarPut;
import academy.ws_work.modules.factories.request.FactoryPost;
import academy.ws_work.modules.factories.request.FactoryPut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CarMapper {


    public static final CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    public abstract Car toPost(CarPost carPost);
    public abstract Car toPut(CarPut carPut);


}
