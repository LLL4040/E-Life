package estateforum.estateforum.daoimpl;

import estateforum.estateforum.entity.Post;

import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.repository.PostRepository;
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
    public List<Post> findAllByCommunityId(int communityId){
        return postRepository.findAllByCommunityId(communityId);
    }
}
