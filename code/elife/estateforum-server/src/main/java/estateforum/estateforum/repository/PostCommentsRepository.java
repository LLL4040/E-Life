package estateforum.estateforum.repository;

import estateforum.estateforum.entity.PostComments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
public interface PostCommentsRepository extends MongoRepository<PostComments,Long> {
    /**
     * 该函数旨在返回特定的一条帖子的所有评论
     * @param pid 帖子的id
     * @return List<PostComments> 该函数返回一条帖子的所有评论*/

    List<PostComments> findAllByPid(String pid);
    /**
     * 该函数旨在删除特定的一条帖子的特定楼层的评论
     * @param pid 贴子的id
     * @param location 发帖评论的楼层数*/
    void deleteByPidAndLocation(String pid,int location);
    /**
     * 该函数旨在返回特定的一条帖子的所有评论
     * @param pageable 用于分页的参数
     * @param pid 发帖人的id
     * @return List<PostComments> 该函数返回一条帖子的所有评论*/
    @Query(value = "{" +
            "    pid:?0" +
            "}")
    List<PostComments> findAllByPidPage(String pid,Pageable pageable);


}
