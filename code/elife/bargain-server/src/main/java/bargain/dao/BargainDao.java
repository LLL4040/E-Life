package bargain.dao;

import bargain.entity.Bargain;

import java.util.List;

/**
 * @author ztHou
 */
public interface BargainDao {
    /**
     * find bargain by id
     * @param id id
     * @return bargain found
     */
    Bargain findById(Long id);

    /**
     * find all bargain
     * @return all bargain
     */
    List<Bargain> findAll();

    /**
     * save bargain
     * @param bargain bargain
     * @return bargain saved
     */
    Bargain save(Bargain bargain);

    /**
     * delete bargain by id
     * @param id id
     */
    void deleteById(Long id);

    /**
     * find all bargains containing the given goods
     * @param goods goods
     * @return bargain
     */
    List<Bargain> findAllByGoodsContaining(String goods);


}
