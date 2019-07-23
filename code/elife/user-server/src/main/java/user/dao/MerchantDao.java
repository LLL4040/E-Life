package user.dao;

import user.entity.Merchant;

import java.util.List;

/**
 * @author ztHou
 */
public interface MerchantDao {
    /**
     * save merchant
     * @param merchant merchant
     * @return merchant saved
     */
    Merchant save(Merchant merchant);

    /**
     * find merchant by id
     * @param id id
     * @return merchant found using id
     */
    Merchant findById(Long id);

    /**
     * find all merchant
     * @return all merchant
     */
    List<Merchant> findAll();

    /**
     * find merchant by username
     * @param username username
     * @return merchant
     */
    Merchant findByUsername(String username);

    /**
     * find all merchant of the chosen type
     * @param type type
     * @return merchant list
     */
    List<Merchant> findAllByType(String type);

    /**
     * get all merchant by username
     * @param username username
     * @return all merchant of the username
     */
    List<Merchant> findAllByUsername(String username);

    /**
     * delete merchant by id
     * @param id id
     */
    void deleteById(Long id);

    /**
     * whether exists id
     * @param id id
     * @return whether exists id
     */
    Boolean existsById(Long id);


}
