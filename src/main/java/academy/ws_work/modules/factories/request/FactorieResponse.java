package academy.ws_work.modules.factories.request;

import academy.ws_work.modules.factories.domain.Factories;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class FactorieResponse {


    private Integer id;
    private String name;
    private Integer countrycode;


    public static FactorieResponse of(Factories factories){
        var reponse = new FactorieResponse();
        BeanUtils.copyProperties(factories, reponse);
        return reponse;
    }
}
