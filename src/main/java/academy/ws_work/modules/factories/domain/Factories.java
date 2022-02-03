package academy.ws_work.modules.factories.domain;


import academy.ws_work.modules.factories.request.FactorieRequest;
import lombok.AllArgsConstructor;
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
public class Factories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer countrycode;


    public static Factories of(FactorieRequest request){
        var factories = new Factories();
        BeanUtils.copyProperties(request,factories);
        return factories;
    }
}
