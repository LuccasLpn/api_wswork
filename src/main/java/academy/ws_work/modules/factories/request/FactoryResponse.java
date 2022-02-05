package academy.ws_work.modules.factories.request;

import academy.ws_work.modules.factories.domain.Factory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactoryResponse {


    private Integer id;
    private String name;
    private Integer countrycode;


    public static FactoryResponse of(Factory factory){
        var reponse = new FactoryResponse();
        BeanUtils.copyProperties(factory, reponse);
        return reponse;
    }
}
