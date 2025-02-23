package newsserver.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ActivityService interface
 *
 * @Author wyx
 * @Date 2019.07.11
 */
public interface ActivityService {
    /**
     * save Activity
     * @param startTime
     * @param endTime
     * @param content
     * @param managerName
     * @param title
     * @param status
     * @param photo
     * @param communityId
     * @return
     * @throws IOException
     */
    boolean saveActivity(String startTime, String endTime, String content,
                         String managerName, String title, int status, MultipartFile photo, int communityId) throws IOException;

    /**
     * find All Activity to user
     * @param communityId
     * @param page
     * @return JSONArray Activity
     */
    JSONArray findAllActivity(int communityId,int page) throws IOException;

    /**
     * find five Activity to user
     * @param communityId
     * @return JSONArray Activity limit 5
     */
    JSONArray findNewActivity(int communityId) throws IOException;

    /**
     * delete Activity by id
     * @param aid
     * @return boolean true or false
     */
    boolean deleteActivity(int aid);

    /**
     * change activity status and delete all disParticipator
     * @param aid
     * @param managerName
     * @param communityId
     * @return boolean true or false
     */
    boolean submitActivity(int aid,String managerName,int communityId);

    /**
     * save Participator
     * @param aid
     * @param content
     * @param status
     * @param username
     * @return boolean true or false
     */
    boolean saveParticipator(int aid,String content,int status,String username);

    /**
     * findAllParticipator
     * @param aid
     * @param page
     * @return JSONArray
     */
    JSONArray findAllParticipator(int aid,int page);

    /**
     * change Participator status to disagree apply
     * @param pid
     * @Param status
     * @return
     */
    boolean disagreeParticipator(int pid,int status);

    /**
     * get real photo
     * @param path
     * @return JSONObject
     */
    JSONObject getBigPhoto(String path);
}
