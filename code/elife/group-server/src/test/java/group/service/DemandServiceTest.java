package group.service;

import group.dao.DemandDao;
import group.dao.ParticipateDao;
import group.entity.Demand;
import group.entity.Participate;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemandServiceTest {
    @Autowired
    private DemandService demandService;

    @MockBean
    private DemandDao demandDao;
    @MockBean
    private ParticipateDao participateDao;

    @Test
    public void addDemandTest () {
        Demand demand = new Demand("username", "startTime", "endTime", "content",
                1L, "title");
        Demand demand1 = new Demand("username", "startTime", "endTime", "content",
                1L, "title");
        demand1.setId(1L);
        Mockito.when(demandDao.save(demand)).thenReturn(demand1);
        Participate participate = new Participate("username", 1L);
        Mockito.when(participateDao.save(participate)).thenReturn(participate);
        JSONObject result = new JSONObject();
        result.put("add", 0);
        Assert.assertEquals(result, demandService.addDemand("startTime", "endTime", "content",
                "username", 1L, "title"));
    }

    @Test
    public void deleteDemandTest () {
        JSONObject result = new JSONObject();
        result.put("delete", 1);
        Assert.assertEquals(result, demandService.deleteDemand(1L));
    }

    @Test
    public void getAllDemandTest () {
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        List<Demand> demandList = new ArrayList<>();
        Demand demand = new Demand("username", "startTime", "endTime", "content",
                1L, "title");
        demand.setId(1L);
        demandList.add(demand);
        Mockito.when(demandDao.findAllByEndTimeAfterAndCommunityId(dateTime, 1L))
                .thenReturn(demandList);
        Mockito.when(participateDao.countDistinctByDemand(demand.getId())).thenReturn(2);
        JSONObject object = new JSONObject();
        object.put("id", demand.getId());
        object.put("start", demand.getStartTime());
        object.put("end", demand.getEndTime());
        object.put("title", demand.getTitle());
        object.put("content", demand.getContent());
        object.put("username", demand.getUsername());
        object.put("communityId", demand.getCommunityId());
        object.put("num", 2);
        jsonArray.appendElement(object);

        Assert.assertEquals(jsonArray, demandService.getAllDemand(1L));
    }

    @Test
    public void participateDemandTest () {
        Participate participate = new Participate("username", 1L);
        Mockito.when(participateDao.existsByUsernameAndDemand("username", 1L))
                .thenReturn(false);
        Mockito.when(participateDao.save(participate)).thenReturn(participate);
        JSONObject result = new JSONObject();
        result.put("participate", 1);
        Assert.assertEquals(result, demandService.participateDemand("username", 1L));
    }

    @Test
    public void quitDemandTest () {
        JSONObject result = new JSONObject();
        result.put("quit", 1);
        Assert.assertEquals(result, demandService.quitDemand("username", 1L));
    }
}
