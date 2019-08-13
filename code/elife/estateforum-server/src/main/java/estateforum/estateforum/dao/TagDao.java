package estateforum.estateforum.dao;

import estateforum.estateforum.entity.Tag;

import java.util.List;

/**
 * @author ztHou
 * @date 2019/08/13
 */
public interface TagDao {
    /**
     * save tag
     * @param tag tag
     * @return tag saved
     */
    Tag save(Tag tag);

    /**
     * get all tags
     * @return all tags
     */
    List<Tag> getAllTags();

    /**
     * add num of given tag
     * @param tag content of tag
     * @return whether add tag num successfully
     */
    Boolean addTagNum(String tag);

    /**
     * reduce num of given tag
     * @param tag content of tag
     * @return whether reduce tag num successfully
     */
    Boolean reduceTagNum(String tag);
}
