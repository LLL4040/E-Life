package packageserver.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import packageserver.entity.Package;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageTest {
    private Package apackage=new Package(-1,"2019-09-08 11:11:11","1",0,"user",1);
    @Test
    public void packageTest(){
        Assert.assertEquals(apackage.getUsername(), "user");
        Assert.assertEquals(apackage.getId(), -1);
        Assert.assertEquals(apackage.getCommunityId(), 1);
        Assert.assertEquals(apackage.getManagerName(), "1");
        Assert.assertEquals( apackage.getStatus(), 0);
        Assert.assertEquals( apackage.getTime(),"2019-09-08 11:11:11" );

        apackage.setUsername("jumin0");
        apackage.setStatus(1);
        apackage.setId(-2);
        apackage.setManagerName("2");
        apackage.setTime("2019-09-08 11:11:12");
        apackage.setCommunityId(2);

        Assert.assertEquals(apackage.getUsername(), "jumin0");
        Assert.assertEquals(apackage.getManagerName(), "2");
        Assert.assertEquals(apackage.getId(), -2);
        Assert.assertEquals(apackage.getStatus(), 1);
        Assert.assertEquals(apackage.getTime(),"2019-09-08 11:11:12" );
        Assert.assertEquals(apackage.getCommunityId(), 2);

    }
}
