package group.service;

import group.dao.BargainDao;
import group.entity.Bargain;
import group.entity.Merchant;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BargainServiceTest {
    @Autowired
    private BargainServiceImpl bargainService;

    @MockBean
    private BargainDao bargainDao;

    @Test
    public void addBargainTest () {
        JSONObject result = new JSONObject();
        result.put("add", 0);
        Assert.assertEquals(result, bargainService.addBargain("1","2","3",4L,"5","6",7));
    }

    @Test
    public void getAllBargainTest () {
        JSONArray jsonArray = new JSONArray();
        List<Bargain> bargains = new ArrayList<>();
        Bargain bargain = new Bargain();
        bargain.setStartTime("start");
        bargain.setEndTime("end");
        bargain.setContent("content");
        bargain.setGoods("goods");
        bargain.setId(1L);
        Merchant merchant = new Merchant();
        bargain.setMerchant(merchant);
        bargain.setStatus(0);
        bargain.setTitle("title");

        bargains.add(bargain);
        Mockito.when(bargainDao.findAll()).thenReturn(bargains);
        JSONObject object = new JSONObject();
        object.put("id", bargain.getId());
        object.put("start", bargain.getStartTime());
        object.put("end", bargain.getEndTime());
        object.put("title", bargain.getTitle());
        object.put("merchantId", bargain.getMerchant().getId());
        object.put("address", bargain.getMerchant().getAddress());
        object.put("name", bargain.getMerchant().getName());
        object.put("goods", bargain.getGoods());
        object.put("content", bargain.getContent());
        object.put("status", bargain.getStatus());
        object.put("phone", bargain.getMerchant().getPhone());
        jsonArray.appendElement(object);
        Assert.assertEquals(jsonArray, bargainService.getAllBargain());
    }

    @Test
    public void deleteBargainTest () {
        JSONObject result = new JSONObject();
        result.put("delete", 1);
        Assert.assertEquals(result, bargainService.deleteBargain(1L));
    }

    @Test
    public void getBargainByMerchantIdTest () {
        JSONArray jsonArray = new JSONArray();
        List<Bargain> bargains = new ArrayList<>();
        Bargain bargain = new Bargain();
        bargain.setStartTime("start");
        bargain.setEndTime("end");
        bargain.setContent("content");
        bargain.setGoods("goods");
        bargain.setId(1L);
        Merchant merchant = new Merchant();
        bargain.setMerchant(merchant);
        bargain.setStatus(0);
        bargain.setTitle("title");

        bargains.add(bargain);
        Mockito.when(bargainDao.findAllByMerchant_Id(1L)).thenReturn(bargains);
        JSONObject object = new JSONObject();
        object.put("id", bargain.getId());
        object.put("start", bargain.getStartTime());
        object.put("end", bargain.getEndTime());
        object.put("title", bargain.getTitle());
        object.put("merchantId", bargain.getMerchant().getId());
        object.put("address", bargain.getMerchant().getAddress());
        object.put("name", bargain.getMerchant().getName());
        object.put("goods", bargain.getGoods());
        object.put("content", bargain.getContent());
        object.put("status", bargain.getStatus());
        object.put("phone", bargain.getMerchant().getPhone());
        jsonArray.appendElement(object);
        Assert.assertEquals(jsonArray, bargainService.getBargainByMerchantId(1L));
    }
}
