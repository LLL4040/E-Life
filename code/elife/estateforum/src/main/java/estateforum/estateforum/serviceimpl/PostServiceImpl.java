package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public List<Post> findAllByCommunityId(int communityId) {
        return postDao.findAllByCommunityId(communityId);
    }
}
