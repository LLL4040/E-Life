package estateforum.estateforum.forumrepository;

import estateforum.estateforum.formentity.PostComments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
public interface PostCommentsRepository extends MongoRepository<PostComments,Long> {
    /**
     * 该函数旨在返回特定的一条帖子的所有评论
     * @param Pid 发帖人的id
     * @return List<PostComments> 该函数返回一条帖子的所有评论*/
    List<PostComments> findAllByPid(int Pid);
}
