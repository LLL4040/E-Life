package newsserver.dao;




import newsserver.entity.News;
import newsserver.entity.NewsUsed;

import java.util.List;

/**
 * NewsDao interface
 *
 * @author wyx
 * @date 2019.07.10
 */
public interface NewsDao {

    /**
     * save news without time and id
     *
     * @param content
     * @param managerName
     * @param title
     * @param status
     * @param photo
     * @param communityId
     */
    void save( String content, String managerName, String title, int status, String photo,int communityId);

    /**
     * find special News with Id
     *
     * @param id
     * @return
     */
    News findOne(int id);

    /**
     * delete one news by ID and return it
     *
     * @param id
     * @return none
     */
    void deleteOne(int id);

    /**
     * find newest List<news></news> by communityId
     *
     * @param communityId
     * @return
     */
    List<News> findNew(int communityId);
    /**
     * move cold table data to history table
     */
    void moveTable();

    /**
     * return history news by communityId from clod table
     *
     * @param communityId
     * @return
     */
    List<NewsUsed> findHistory(int communityId);

    /**
     * return List from chot table
     *
     * @param communityId
     * @return
     */
    List<News> findHistoryHot(int communityId);
}
