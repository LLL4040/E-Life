package estateforum.estateforum.forumserviceimpl;

import estateforum.estateforum.formentity.Post;
import estateforum.estateforum.forumdao.PostDao;
import estateforum.estateforum.forumrepository.PostRepository;
import estateforum.estateforum.forumservice.PostService;
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
    public List<Post> findAll() {
        return postDao.findAll();
    }
}
