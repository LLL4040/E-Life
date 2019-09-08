package newsserver.repositoryTest;

import newsserver.entity.News;
import newsserver.entity.NewsUsed;
import newsserver.repository.NewsRepository;
import newsserver.repository.NewsUsedRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsUsedRepositoryTest {

    /**
     * new
     */
    @Autowired
    private NewsUsedRepository newsUsedRepository;

    @Test
    @Transactional
    public void findHistoryTest(){
        NewsUsed newsUsed=new NewsUsed(55555,"hello","admin","test","gugugui",1);
        newsUsedRepository.save(newsUsed);
        newsUsedRepository.findHistory(1);
    }
}
