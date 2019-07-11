package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.entity.Community;

/**
 * @author ztHou
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {
    /**
     * find community by id
     * @param id id
     * @return community found
     */
    Community findById(Long id);
}
