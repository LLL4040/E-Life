package estateforum.estateforum.forumdaoimpl;


import estateforum.estateforum.formentity.PostComments;
import estateforum.estateforum.forumdao.PostCommentsDao;
import estateforum.estateforum.forumrepository.PostCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Repository
public class PostCommentsImpl implements PostCommentsDao {
    @Autowired
    private PostCommentsRepository postCommentsRepository;
    @Override
    public List<PostComments> findAllByPid(int Pid){
        return  postCommentsRepository.findAllByPid(Pid);
    }
    @Override
    public void saveComments(PostComments postComments){
        postCommentsRepository.save(postComments);
    }
}
