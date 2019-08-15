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
     * whether exists content and community id
     * @param tag tag
     * @param communityId community id
     * @return whether exists
     */
    Boolean existsByContentAndCommunityId(String tag, Long communityId);

    /**
     * find tag by content in community with id
     * @param content tag content
     * @param communityId community id
     * @return tag
     */
    Tag findTagByContentAndCommunityId(String content, Long communityId);

    /**
     * get all tags
     * @return all tags
     */
    List<Tag> getAllTags();

    /**
     * find all tags in community
     * @param communityId community id
     * @return tags in community
     */
    List<Tag> getAllTagsByCommunityId(Long communityId);

    /**
     * add num of given tag
     * @param tag content of tag
     * @param communityId community id
     * @return whether add tag num successfully
     */
    Boolean addTagNum(String tag, Long communityId);

    /**
     * reduce num of given tag
     * @param tag content of tag
     * @param communityId community id
     * @return whether reduce tag num successfully
     */
    Boolean reduceTagNum(String tag, Long communityId);
}
