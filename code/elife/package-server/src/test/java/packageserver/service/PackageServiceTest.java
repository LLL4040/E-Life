package packageserver.service;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import packageserver.entity.Package;
import packageserver.repository.PackageRepository;

import javax.transaction.Transactional;


/**
 * @author wyx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageServiceTest {
    @Autowired
    PackageService packageService;
    @Autowired
    PackageRepository packageRepository;

    @Test
    @Transactional
    public void RunTest(){
        Package apackage=new Package(-2,"2019-09-08 11:11:11","admin",0,"user",1);
        packageRepository.save(apackage);
        packageService.findHistory("user",1);
        packageService.findHistoryManager(1,1);
        packageService.findNew("user");
        packageService.findOne(-2);
        packageService.takeOut(-2);
        packageService.deleteOne(-2);
        packageService.save("2019-09-01 11:11:11","admin","user",1);
    }
}
