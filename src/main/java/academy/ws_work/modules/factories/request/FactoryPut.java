package academy.ws_work.modules.factories.request;

import academy.ws_work.modules.factories.domain.Factory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoryPut {


    private Integer id;
    private String name;
    private Integer countrycode;



}
