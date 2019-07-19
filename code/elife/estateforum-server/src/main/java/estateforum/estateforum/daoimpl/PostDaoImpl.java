package estateforum.estateforum.daoimpl;

import estateforum.estateforum.entity.Post;

import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public String save(Post post){
        return  postRepository.save(post).getId();
    }

    @Override
    public List<Post> findAllByCommunityId(int communityId,int page,int size){
        PageRequest pageable = new PageRequest(page-1, size);
        return postRepository.findAllByCommunityId(communityId,pageable);
    }
    @Override
    public void deletePost(String id){
        postRepository.deleteById(id);
    };
    @Override
    public Post findPost(String id){
       return postRepository.getById(id);
    }


}
