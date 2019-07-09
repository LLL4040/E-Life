package estateforum.estateforum.service;

import estateforum.estateforum.entity.Post;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

public interface PostService {
    /**
     * 此函数存一条帖子
     * @param post 帖子*/
    String save(Post post);
    /**
     * 此函数以列表形式返回所有帖子
     * @return 以列表形式返回的所有帖子
     * @param communityId 小区id*/
    List<Post> findAllByCommunityId(int communityId);
    /**
     *该方法返回特定id的的帖子
     * @param  id 指定的帖子id
     * @return 返回指定的帖子*/
    Post findPost(String id);
    /**
     *该方法删除特定pid的的帖子
     * @param  id 指定的帖子id*/
    void deletePost(String id);

    /**
     * 此函数以列表形式返回所有帖子
     * @return 以分页列表形式返回的所有帖子
     * @param communityId 小区id
     * @param page 页表
     * @param size 大小*/
    List<Post> findPageablePost(int communityId,Integer page,Integer size);

}
