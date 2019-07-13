package group.repository;

import group.entity.Bargain;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface BargainRepository extends JpaRepository<Bargain, String> {
    /**
     * find bargain by id
     * @param id id
     * @return bargain
     */
    Bargain findById(Long id);

    /**
     * whether exists id
     * @param id id
     * @return whether exists id
     */
    Boolean existsById(Long id);

    /**
     * delete bargain by id
     * @param id id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);

    /**
     * find all bargains containing the given goods
     * @param goods goods
     * @return bargain
     */
    List<Bargain> findAllByGoodsContaining(String goods);
}
