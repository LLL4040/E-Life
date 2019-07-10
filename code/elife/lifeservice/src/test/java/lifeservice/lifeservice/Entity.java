package lifeservice.lifeservice;

import lifeservice.lifeservice.entity.Maintain;
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
}
