package estateforum.estateforum.repository;


import estateforum.estateforum.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
public interface PostRepository extends MongoRepository <Post,Long>{
    /**
     * 该方法返回指定用户发布的所有帖子
     * @param posterName 发帖人id
     * @return 返回指定发帖人发布的所有帖子*/
    List<Post> findByPosterName(String posterName);
    /**
     * 该方法返回指定小区用户发布的所有帖子
     * @param communityId 小区的id*/
    List<Post>findAllByCommunityId(int communityId);
    /**
     *该方法返回特定id的的帖子
     * @param  id 指定的帖子id
     * @return 返回指定的帖子*/
    Post getById(String id);
    /**
     *该方法删除特定id的的帖子
     * @param  id 指定的帖子id*/
    void deleteById(String id);

}
