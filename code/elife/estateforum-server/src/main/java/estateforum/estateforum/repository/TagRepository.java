package estateforum.estateforum.repository;

import estateforum.estateforum.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 * @date 2019/08/13
 */
public interface TagRepository extends MongoRepository<Tag, String> {
    /**
     * find tag by content in community with id
     * @param content tag content
     * @param communityId community id
     * @return tag
     */
    Tag findTagByContentAndCommunityId(String content, Long communityId);

    /**
     * find tags in community
     * @param communityId community id
     * @return tags in community
     */
    List<Tag> findAllByCommunityId(Long communityId);

    /**
     * whether exists tag by content in community with id
     * @param content tag content
     * @param communityId community id
     * @return whether exists tag by content
     */
    Boolean existsTagByContentAndCommunityId(String content, Long communityId);

    /**
     * delete tag by content and community id
     * @param content tag content
     * @param communityId community id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteTagByContentAndCommunityId(String content, Long communityId);
}
