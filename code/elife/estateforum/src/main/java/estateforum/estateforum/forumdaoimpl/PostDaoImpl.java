package estateforum.estateforum.forumdaoimpl;

import estateforum.estateforum.formentity.Post;
import estateforum.estateforum.forumdao.PostDao;
import estateforum.estateforum.forumrepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Repository
public class PostDaoImpl implements PostDao {
    @Autowired
    private PostRepository postRepository;
    @Override
    public void save(Post post){
        postRepository.save(post);
    }
    @Override
    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
