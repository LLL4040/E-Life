package inform.repository;

import inform.entity.Inform;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InformRepository interface
 *
 * @author wyx
 * @date 2019.07.04
 */
public interface InformRepository extends JpaRepository<Inform, Integer> {

}
