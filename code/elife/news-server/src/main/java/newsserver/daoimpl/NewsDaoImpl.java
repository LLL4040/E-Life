package newsserver.daoimpl;

import newsserver.dao.NewsDao;
import newsserver.entity.News;
import newsserver.entity.NewsUsed;
import newsserver.repository.NewsRepository;
import newsserver.repository.NewsUsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
/**
 * NewsDao Class
 *
 * @Author wyx
 * @Date 2019.07.10
 */
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsUsedRepository newsUsedRepository;

    @Override
    public void save( String content, String managerName, String title, int status, String photo,int communityId){
        newsRepository.changeStatus(communityId);
        newsRepository.saveNews(content,managerName,title,status,photo,communityId);
    }

    @Override
    public News findOne(int id){
        return newsRepository.getOne(id);
    }

    @Override
    public void deleteOne(int id){
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findNew(int communityId){
        return newsRepository.findNew(communityId);
    }

    @Override
    public void moveTable(){
        newsRepository.moveTable(1);
    }

    @Override
    public List<NewsUsed> findHistory(int communityId){
        return newsUsedRepository.findHistory(communityId);
    }

    @Override
    public  List<News> findHistoryHot(int communityId){
        return newsRepository.findHistory(communityId);
    }
}
