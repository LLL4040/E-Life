package estateforum.estateforum.repository;


import estateforum.estateforum.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04
 * @author ztHou
 * @date 2019/08/25
 */
public interface PostRepository extends MongoRepository <Post,Long>{
    /**
     * 该方法返回指定用户发布的所有帖子
     * @param posterName 发帖人id
     * @return 返回指定发帖人发布的所有帖子
     */
    List<Post> findByPosterName(String posterName);
    /**
     * 该方法返回指定小区用户发布的所有帖子
     * @param communityId 小区的id
     * @param pageable 用来分页的参数
     * @return 返回帖子的列表
     */
    List<Post>findAllByCommunityId(int communityId, Pageable pageable);
    /**
     *该方法返回特定id的的帖子
     * @param  id 指定的帖子id
     * @return 返回指定的帖子
     */
    Post getById(String id);
    /**
     *该方法删除特定id的的帖子
     * @param  id 指定的帖子id
     */
    void deleteById(String id);

    /**
     * 该方法返回指定小区用户发布的所有标签为tag的帖子
     * @param communityId 小区id
     * @param tag 标签
     * @param pageable 用来分页的参数
     * @return 返回帖子的列表
     */
    List<Post>findAllByCommunityIdAndTag(int communityId, String tag, Pageable pageable);

    /**
     * 该方法返回指定小区用户发布的帖子总数
     * @param communityId 小区id
     * @return 帖子总数
     */
    Integer countByCommunityId(int communityId);

}
