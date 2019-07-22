package packageserver.daoimpl;

import packageserver.dao.PackageDao;
import packageserver.entity.Package;
import packageserver.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
/**
 * PackageDaoImpl Class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PackageDaoImpl implements PackageDao {
    @Autowired
    private PackageRepository packageRepository;


    @Override
    public void save( String time, int status,String managerName, String username,int communityId){
     packageRepository.savePackage(time,status,managerName,username,communityId);
    }

    @Override
    public Package findOne(int id){
        return packageRepository.getOne(id);
    }

    @Override
    public void deleteOne(int id){
        packageRepository.deleteById(id);
    }

    @Override
    public void changeStatus(int id) {
        Package temp = packageRepository.getOne(id);
        temp.setStatus(1);
        packageRepository.save(temp);
    }
    @Override
    public List<Package> findNew(String username){
        return packageRepository.findNew(username);
    }

    @Override
    public List<Package> findHistory(String username,int page){
        return packageRepository.findHistory(username,10*(page-1),10);
    }

    @Override
    public List<Package> findHistoryManager(int communityId,int page){
        return packageRepository.findHistoryManager(communityId,10*(page-1),10);
    }

    @Override
    public int findPageNum(String username){
        List<Package> temp = packageRepository.findPageNum(username);
        int pageNum=temp.size();
        if(pageNum%10!=0){
            pageNum=pageNum/10+1;
        }
        else {
            pageNum=pageNum/10;
        }
        return pageNum;
    }

    @Override
    public int findPageNumManager(int communityId){
        List<Package> temp = packageRepository.findPageNumManager(communityId);
        int pageNum=temp.size();
        if(pageNum%10!=0){
            pageNum=pageNum/10+1;
        }
        else {
            pageNum=pageNum/10;
        }
        return pageNum;
    }
   }
