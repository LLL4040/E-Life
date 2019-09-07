package apackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import apackage.entity.Merchant;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    /**
     * find merchant by id
     * @param id id
     * @return merchant
     */
    Merchant findById(Long id);

    /**
     * find merchant by username
     * @param username username
     * @return merchant
     */
    Merchant findByUser_Username(String username);

    /**
     * whether exists id
     * @param id id
     * @return whether exists id
     */
    Boolean existsById(Long id);

    /**
     * find all merchant of the chosen type
     * @param type type
     * @return all merchant of the chosen type
     */
    List<Merchant> findAllByType(String type);

    /**
     * get all merchant by username
     * @param username username
     * @return all merchant of the username
     */
    List<Merchant> findAllByUser_Username(String username);

    /**
     * delete merchant by id
     * @param id id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);
}
