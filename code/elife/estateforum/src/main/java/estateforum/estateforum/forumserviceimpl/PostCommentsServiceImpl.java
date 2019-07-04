package estateforum.estateforum.forumserviceimpl;

import estateforum.estateforum.formentity.PostComments;
import estateforum.estateforum.forumdao.PostCommentsDao;
import estateforum.estateforum.forumservice.PostCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Service
public class PostCommentsServiceImpl implements PostCommentsService {
    @Autowired
    private PostCommentsDao postCommentsDao;
    @Override
    public List<PostComments> findAllByPid(int Pid){
        return  postCommentsDao.findAllByPid(Pid);
    }
    @Override
    public void saveComments(PostComments postComments){
        postCommentsDao.saveComments(postComments);
    }
}
