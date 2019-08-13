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
     * find tag by content
     * @param content tag content
     * @return tag
     */
    Tag findTagByContent(String content);

    /**
     * whether exists tag by content
     * @param content tag content
     * @return whether exists tag by content
     */
    Boolean existsTagByContent(String content);

    /**
     * delete tag by content
     * @param content tag content
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteTagByContent(String content);
}
