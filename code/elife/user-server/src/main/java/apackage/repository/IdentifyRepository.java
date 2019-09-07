package apackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import apackage.entity.Identify;

import javax.transaction.Transactional;


/**
 * @author ztHou
 */
public interface IdentifyRepository extends JpaRepository<Identify, String> {
    /**
     * delete data by phone number
     *
     * @param phone phone number
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByPhone(String phone);

    /**
     * whether exists phone number
     *
     * @param phone phone number
     * @return whether exists phone number
     */
    Boolean existsByPhone(String phone);

    /**
     * find data by phone number
     *
     * @param phone phone number
     * @return identify
     */
    Identify findByPhone(String phone);
}
