package packageserver.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import packageserver.entity.Package;
import packageserver.repository.PackageRepository;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageDaoTest {
    @Autowired
    PackageDao packageDao;
    @Autowired
    PackageRepository packageRepository;

    @Test
    public void findOneTest(){
        Package apackage=new Package(999999,"2019-09-08 11:11:11","admin",0,"user",1);
        packageRepository.save(apackage);
         Assert.assertEquals("findOnefail", 0, packageDao.findOne(999999).getStatus());
    }

    @Test
    public void changeStatusTest(){
        Package apackage=new Package(999999,"2019-09-08 11:11:11","admin",0,"user",1);
        packageRepository.save(apackage);
        packageDao.changeStatus(999999);
        Assert.assertEquals("changeStatusFail", 1, packageDao.findOne(999999).getStatus());

    }

    @Test
    public void deleteOneTest(){
        Package apackage=new Package(999999,"2019-09-08 11:11:11","admin",0,"user",1);
        packageRepository.save(apackage);
        packageDao.deleteOne(999999);
        try{
            packageDao.deleteOne(999999);
        }
        catch (Exception e){
           return;
        }
        Assert.fail();
    }
    @Test
    public void runTest(){
        packageDao.findHistory("user",1);
        packageDao.findNew("user");
        packageDao.findHistoryManager(1,1);
        packageDao.findPageNum("user");
        packageDao.findPageNumManager(1);
    }
}
