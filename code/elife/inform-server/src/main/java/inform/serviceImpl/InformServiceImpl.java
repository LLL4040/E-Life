package inform.serviceImpl;

import inform.dao.InformDao;
import inform.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * InformServiceImpl class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class InformServiceImpl implements InformService {

    @Autowired
    private InformDao informDao;
}
