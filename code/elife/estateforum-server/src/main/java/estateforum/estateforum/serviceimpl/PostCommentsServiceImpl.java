package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.entity.PostComments;
import estateforum.estateforum.dao.PostCommentsDao;
import estateforum.estateforum.service.PostCommentsService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Service
public class PostCommentsServiceImpl implements PostCommentsService {
    @Autowired
    private PostCommentsDao postCommentsDao;

    @Override
    public List<PostComments> findAllByPid(String pid){
        return  postCommentsDao.findAllByPid(pid);
    }

    @Override
    public JSONArray findAllByPidPage(String pid, int page, int size) {
        List<PostComments> postCommentsList= postCommentsDao.findAllByPidPage(pid,page,size);
        JSONArray jsonArray =new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        int count =postCommentsDao.findAllByPid(pid).size();
        int pageNum = count/size;
        if (count%size!=0){
            pageNum=pageNum+1;
        }
        jsonObject1.put("pageNum",pageNum);
        jsonArray.add(jsonObject1);
        Iterator<PostComments> iter = postCommentsList.iterator();
        while(iter.hasNext()){
            PostComments temp = iter.next();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("pid", temp.getPid());
            jsonObject.put("commenterName", temp.getCommenterName());
            jsonObject.put("commentsTime", temp.getCommentsTime());

            jsonObject.put("location",temp.getLocation());
            jsonObject.put("postComment",temp.getpostComment());

            jsonArray.add(jsonObject);
        }


        return jsonArray;
    }

    @Override
    public void saveComments(PostComments postComments){
        postCommentsDao.saveComments(postComments);
    }
    @Override
    public void deleteComments(String pid, int location){
        postCommentsDao.deleteComments(pid,location);
    };
}
