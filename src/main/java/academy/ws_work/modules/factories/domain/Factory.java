package academy.ws_work.modules.factories.domain;


import academy.ws_work.modules.factories.request.FactoryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer countrycode;


    public static Factory of(FactoryRequest request){
        var factories = new Factory();
        BeanUtils.copyProperties(request,factories);
        return factories;
    }
}
