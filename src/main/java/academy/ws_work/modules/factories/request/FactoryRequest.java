package academy.ws_work.modules.factories.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactoryRequest {

    private String name;
    private Integer countrycode;

}
