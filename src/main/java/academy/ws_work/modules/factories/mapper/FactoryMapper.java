package academy.ws_work.modules.factories.mapper;

import academy.ws_work.modules.factories.domain.Factory;
import academy.ws_work.modules.factories.request.FactoryPost;
import academy.ws_work.modules.factories.request.FactoryPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FactoryMapper {

    public static final FactoryMapper INSTANCE = Mappers.getMapper(FactoryMapper.class);


    public abstract Factory toPost(FactoryPost factoryPost);

    public abstract Factory toPut(FactoryPut factoryPut);
}
