package estateforum.estateforum.dao;

import estateforum.estateforum.entity.Post;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04
 * @author ztHou
 * @date 2019/08/25
 */
public interface PostDao {
    /**
     * 此函数存一条帖子
     * @param post 帖子
     * @return 返回成功储存的id号*/
     String save(Post post);
    /**
     * 此函数以列表形式返回所有帖子
     * @return 以列表形式返回的所有帖子
     * @param communityId 小区的id
     * @param page 当前页数
     * @param size 每页数据量*/
    List<Post> findAllByCommunityId(int communityId,int page,int size);
    /**
     *该方法返回特定pid的的帖子
     * @param  id 指定的帖子id*/
    Post findPost(String id);
    /**
     *该方法删除特定pid的的帖子
     * @param  id 指定的帖子id*/
    void deletePost(String id);

    /**
     * 该方法返回指定小区用户发布的所有标签为tag的帖子
     * @param communityId 小区id
     * @param tag 标签
     * @param page 当前页数
     * @param size 每页数据量
     * @return 返回帖子的列表
     */
    List<Post> findAllByCommunityIdAndTag(int communityId, String tag, int page, int size);

    /**
     * 该方法返回小区中帖子个数
     * @param communityId 小区id
     * @return 帖子个数
     */
    Integer count(int communityId);

}
