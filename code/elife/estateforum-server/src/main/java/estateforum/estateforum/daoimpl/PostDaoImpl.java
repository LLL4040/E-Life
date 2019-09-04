package estateforum.estateforum.daoimpl;

import estateforum.estateforum.entity.Post;

import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04
 * @author ztHou
 * @date 2019/08/25
 */
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
//        PageRequest pageable = new PageRequest(page-1, size);
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "postTime"));
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

    @Override
    public List<Post> findAllByCommunityIdAndTag(int communityId, String tag, int page, int size){
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "postTime"));
        return postRepository.findAllByCommunityIdAndTag(communityId, tag, pageable);
    }

    @Override
    public Integer count(int communityId){
        return postRepository.countByCommunityId(communityId);
    }


}
