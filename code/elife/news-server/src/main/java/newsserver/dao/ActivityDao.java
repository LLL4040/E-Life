package newsserver.dao;



import newsserver.entity.Activity;
import newsserver.entity.Participator;

import java.util.List;

/**
 * ActivityDao interface
 *
 * @Author wyx
 * @Date 2019.07.11
 */
public interface ActivityDao {
    /**
     * save Activity without id
     *
     * @param startTime
     * @param endTime
     * @param content
     * @param managerName
     * @param title
     * @param status
     * @param photo
     * @param communityId
     */
    void saveActivity(String startTime, String endTime, String content,
                      String managerName, String title, int status, String photo, int communityId);

    /**
     * find all Activity to user look
     * @param communityId
     * @return List<Activity>all activity</>
     */
    List<Activity> findAllActivity(int communityId,int page);

    /**
     * find new five activity list to user look
     * @param communityId
     * @return List<Activity>five member</>
     */
    List<Activity> findNewActivity(int communityId);

    /**
     * delete activity by manager
     * @param aid
     */
    void deleteActivity (int aid);

    /**
     * change Activity Status to close activity
     * @param aid
     * @param status
     */
    void changeStatusAct(int aid,int status);

    /**
     * Save participator without id
     * @param aid
     * @param content
     * @param status
     * @param username
     */
    void saveParticipator(int aid,String content,int status,String username);

    /**
     * find All participator to manager to control
     * @param aid
     * @return List participator all
     */
    List<Participator> findAllParticipator(int aid,int page);

    /**
     * change Participator status to disagree it
     * @param pid
     * @Param status
     */
    void changeStatusPar(int pid,int status);

    /**
     * deleteAll disagree participator to start activity
     * @param aid
     */
    void deleteAllDisPar(int aid);

    /**
     * before save if have username return true
     * @param aid
     * @param username
     * @return boolean true or false
     */
    boolean existApply(int aid,String username);

    /**
     * find page
     * @param communityId
     * @return
     */
    int findPageActivity(int communityId);

    /**
     * find page
     * @param aid
     * @return
     */
    int findPageParticipator(int aid);

    /**
     * find all participatar without page
     * @param aid
     * @return
     */
    List<Participator> allParticipator(int aid);

    /**
     * get activity By id
     * @param aid
     * @return
     */
    Activity getOne(int aid);

}
