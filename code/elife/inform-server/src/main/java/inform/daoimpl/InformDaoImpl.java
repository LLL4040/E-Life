package inform.daoimpl;

import inform.dao.InformDao;
import inform.repository.InformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
/**
 * InformDaoImpl class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class InformDaoImpl implements InformDao {
    @Autowired
    private InformRepository informRepository;

}
