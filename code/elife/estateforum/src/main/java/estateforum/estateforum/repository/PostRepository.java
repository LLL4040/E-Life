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
     * 该方法返回指定id人发布的所有帖子
     * @param pid 发帖人id
     * @return 返回指定发帖人发布的所有帖子*/
    List<Post> findByPid(int pid);
    List<Post>findAllByCommunityId(int communityId);

}
