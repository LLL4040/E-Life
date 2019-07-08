package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.entity.PostComments;
import estateforum.estateforum.dao.PostCommentsDao;
import estateforum.estateforum.service.PostCommentsService;
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
    public List<PostComments> findAllByPid(String pid){
        return  postCommentsDao.findAllByPid(pid);
    }
    @Override
    public void saveComments(PostComments postComments){
        postCommentsDao.saveComments(postComments);
    }
    @Override
    public void deleteComments(String pid, int location){
        postCommentsDao.deleteComments(pid,location);
    };
}
