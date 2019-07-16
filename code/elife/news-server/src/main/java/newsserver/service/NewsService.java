package newsserver.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * newsService interface
 *
 * @Author wyx
 * @Date 2019.07.10
 */
public interface NewsService {
    /**
     * save News with photo
     * \
     * @param content
     * @param managerName
     * @param title
     * @param photo
     * @param communityId
     * @return
     * @throws IOException
     */
    boolean save(String content, String managerName, String title,
                  MultipartFile photo, int communityId) throws IOException;

    /**
     * find List<News> turn to JSON
     *
     * @param communityId
     * @return JSONArayy
     */
    JSONArray findNew(int communityId) throws IOException;

    /**
     * find specific News with ID
     *
     * @param id
     * @return News
     */
    JSONObject findOne(int id) throws IOException;

    /**
     * delete special News with ID
     *
     * @param id
     * @return boolean
     */
    boolean deleteOne(int id);

    /**
     * move hot data to cold table
     *
     * @return boolean
     */
    boolean moveTable();

    /**
     * findHistory JSON
     * @param communityId
     * @param page
     * @return JSONArray
     * @throws IOException
     */
    JSONArray findHistory(int communityId,int page) throws IOException;
}
