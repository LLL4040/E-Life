package estateforum.estateforum.daoimpl;


import estateforum.estateforum.entity.PostComments;
import estateforum.estateforum.dao.PostCommentsDao;
import estateforum.estateforum.repository.PostCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public List<PostComments> findAllByPid(String pid){

        return  postCommentsRepository.findAllByPid(pid);
    }

    @Override
    public List<PostComments> findAllByPidPage(String Pid, int page, int size) {
        PageRequest pageable = new PageRequest(page-1, size);
        return postCommentsRepository.findAllByPidPage(Pid,pageable);
    }

    @Override
    public void saveComments(PostComments postComments){
        postCommentsRepository.save(postComments);
    }
    @Override
    public void deleteComments(String pid,int location){
        postCommentsRepository.deleteByPidAndLocation(pid,location);
    }
}
