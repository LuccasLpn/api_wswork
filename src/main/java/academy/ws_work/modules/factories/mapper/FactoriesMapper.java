package academy.ws_work.modules.factories.mapper;
import academy.ws_work.modules.factories.domain.Factories;
import academy.ws_work.modules.factories.request.FactoriesPost;
import academy.ws_work.modules.factories.request.FactoriesPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FactoriesMapper {

    public static final FactoriesMapper INSTANCE = Mappers.getMapper(FactoriesMapper.class);


    public abstract Factories toPost(FactoriesPost factoriesPost);
    public abstract Factories toPut(FactoriesPut factoriesPut);


}
