package lifeservice.lifeservice;

import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.entity.Merchant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Entity {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String maintainTime = df.format(new Date());
    Maintain maintain=new Maintain(maintainTime,"我家窗户破了","哪吒","12345");
    Merchant merchant=new Merchant("哪吒","风火烤肉店","123456","小区门口","风火轮专属烤肉店，来过的人都说好","周边餐饮");
    @Test
    public void maintainIdTest(){

        long id = maintain.getId();
        Assert.assertNotNull(id);
    }
    @Test
    public void TimeTest(){
        Assert.assertEquals("get maintainTime fail",maintainTime,maintain.getTime());
        String mainTime2 = df.format(new Date());
        maintain.setTime(mainTime2);
        Assert.assertEquals("get maintainTime fail",mainTime2,maintain.getTime());
        maintain.setTime(maintainTime);
    }
    @Test
    public void StatusTest(){
        Assert.assertEquals("get status fail",0,maintain.getStatus());
        maintain.setStatus(1);
        Assert.assertEquals("get status fail",1,maintain.getStatus());
        maintain.setStatus(0);
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("get content fail","我家窗户破了",maintain.getContent());
        maintain.setContent("我家门把坏了");
        Assert.assertEquals("get content fail","我家门把坏了",maintain.getContent());
        maintain.setContent("我家窗户破了");
    }
    @Test
    public void MaintainnameTest(){
        Assert.assertEquals("get maintainame fail",null,maintain.getMaintainname());
        maintain.setMaintainname("土地老爷");
        Assert.assertEquals("get maintainame fail","土地老爷",maintain.getMaintainname());
    }
    @Test
    public void maintainPhoneTest(){
        Assert.assertEquals("get maintainphone fail",null,maintain.getPhone());
        maintain.setPhone("123");
        Assert.assertEquals("get maintainphone fail","123",maintain.getPhone());

    }
    @Test
    public void maintainManagerTest(){
        Assert.assertEquals("get maintainManager fail",null,maintain.getManagername());
        maintain.setManagername("二郎神");
        Assert.assertEquals("get maintainManager fail","二郎神",maintain.getManagername());
    }
    @Test
    public void usernameTest(){
        Assert.assertEquals("get username fail","哪吒",maintain.getUsername());
        maintain.setUsername("哮天犬");
        Assert.assertEquals("get username fail","哮天犬",maintain.getUsername());
    }
    @Test
    public void userphoneTest(){
        Assert.assertEquals("get userphone fail","12345",maintain.getUserPhone());
        maintain.setUserPhone("54321");
        Assert.assertEquals("get userphone fail","54321",maintain.getUserPhone());

    }
    @Test
    public void MerchantUsernameTest(){
        Assert.assertEquals("get MerchantUsername fail","哪吒",merchant.getUsername());
        merchant.setUsername("悟能");
        Assert.assertEquals("get MerchantUsername fail","悟能",merchant.getUsername());

    }
    @Test
    public void MerchantNameTest(){
        Assert.assertEquals("get MerchantName fail","风火烤肉店",merchant.getName());
        merchant.setName("火风烤肉店");
        Assert.assertEquals("get MerchantName fail","火风烤肉店",merchant.getName());

    }
    @Test
    public void MerchantPhoneTest(){
        Assert.assertEquals("get MerchantPhone fail","123456",merchant.getPhone());
        merchant.setPhone("654321");
        Assert.assertEquals("get MerchantPhone fail","654321",merchant.getPhone());

    }
    @Test
    public void MerchantAddressTest(){
        Assert.assertEquals("get MerchantAddress fail","小区门口",merchant.getAddress());
        merchant.setAddress("小区大门");
        Assert.assertEquals("get MerchantAddress fail","小区大门",merchant.getAddress());

    }
    @Test
    public void MerchantDetailTest(){
        Assert.assertEquals("get MerchantDetail fail","风火轮专属烤肉店，来过的人都说好",merchant.getDetail());
        merchant.setDetail("风火轮专属烤肉店，没来过的人也说好");
        Assert.assertEquals("get MerchantDetail fail","风火轮专属烤肉店，没来过的人也说好",merchant.getDetail());

    }
    @Test
    public void MerchantTypeTest(){
        Assert.assertEquals("get MerchantType fail","周边餐饮",merchant.getType());
        merchant.setType("休闲娱乐");
        Assert.assertEquals("get MerchantType fail","休闲娱乐",merchant.getType());

    }

}
