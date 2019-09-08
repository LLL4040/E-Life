package packageserver.repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import packageserver.entity.Package;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageRepositoryTest {
    @Autowired
    PackageRepository packageRepository;

    @Test
    @Transactional
    public void savePackageTest(){
        packageRepository.savePackage("2019-09-01 11:11:11",0,"admin","user",1);
    }

    @Test
    public void RunAbleTest(){
        List<Package> packages=packageRepository.findNew("user");
        packageRepository.findHistory("user",0,10);
        packageRepository.findHistoryManager(1,0,10);
        packageRepository.findPageNum("user");
        packageRepository.findPageNumManager(1);
    }

}
