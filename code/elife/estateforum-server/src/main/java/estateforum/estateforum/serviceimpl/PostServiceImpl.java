package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Override
    public String  save(Post post) {
        return postDao.save(post);
    }

    @Override
    public List<Post> findAllByCommunityId(int communityId,int page,int size) {
        return postDao.findAllByCommunityId(communityId,page,size);
    }
    @Override
    public Post findPost(String id){
        return postDao.findPost(id);
    }
    @Override
    public void deletePost(String pid){
          postDao.deletePost(pid);
    };

}
