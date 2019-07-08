package estateforum.estateforum.dao;

import estateforum.estateforum.entity.PostComments;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
public interface PostCommentsDao {
    /**
     * 该函数旨在返回特定的一条帖子的所有评论
     * @param Pid 发帖人的id
     * @return List<PostComments> 该函数返回一条帖子的所有评论*/
    List<PostComments> findAllByPid(String Pid);
   /**
    * 该函数存一条帖子的评价
    * @param postComments 帖子的评论*/
    void saveComments(PostComments postComments);
    /**
     * 该函数旨在返回删除的一条帖子的特定楼层的评论
     * @param pid 贴子的id
     * @param location 发帖评论的楼层数*/
    void deleteComments(String pid,int location);
}
