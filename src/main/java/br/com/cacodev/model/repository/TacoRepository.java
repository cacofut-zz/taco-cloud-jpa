
package br.com.cacodev.model.repository;

import br.com.cacodev.tacocloudjpa.model.entity.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cristianoca
 */
public interface TacoRepository extends CrudRepository<Taco, Long>{
    
    
}
