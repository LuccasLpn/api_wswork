package academy.ws_work.modules.factories.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactoriesPost {

    private String name;
    private Integer countrycode;
}
