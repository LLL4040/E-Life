package group.service;

import group.dao.DiscountDao;
import group.entity.Discount;
import net.minidev.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;


import static org.mockito.ArgumentMatchers.any;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceTest {
    @Autowired
    private DiscountService discountService;

    @MockBean
    private DiscountDao discountDao;

    @Test
    @Rollback
    public void addDiscountTest () throws Exception {
        JSONObject result = new JSONObject();
        result.put("add", 0);
        MultipartFile photo = null;
        Mockito.when(discountDao.save(any(Discount.class))).thenReturn(new Discount());
        Assert.assertEquals(result, discountService.addDiscount("startTime", "endTime", 1L,
                100, "content", 1, 1L,"title", photo));
    }

    @Test
    public void deleteDiscountTest () {
        JSONObject result = new JSONObject();
        result.put("delete", 1);
        Assert.assertEquals(result, discountService.deleteDiscount(1L));
    }
}
