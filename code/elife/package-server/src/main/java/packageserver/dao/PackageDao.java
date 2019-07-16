package packageserver.dao;




import packageserver.entity.Package;

import java.util.List;

/**
 * PackageDao interface
 *
 * @author wyx
 * @date 2019.07.12
 */
public interface PackageDao {

    /**
     * save Package without id
     * @param time
     * @param status
     * @param managerName
     * @param username
     * @param communityId
     */
    void save( String time, int status,String managerName,String username,int communityId);

    /**
     * set status to 1 just user take out package
     * @param id
     */
    void changeStatus(int id);

    /**
     * find special package with Id
     *
     * @param id
     * @return
     */
    Package findOne(int id);

    /**
     * delete one package by ID and return it
     *
     * @param id
     * @return none
     */
    void deleteOne(int id);

    /**
     * find newest List<package></package> by username
     *
     * @param username
     * @return
     */
    List<Package> findNew(String username);

    /**
     * find history table
     * @param username
     * @param page
     * @return list<Package>all history list</Package>
     */

    List<Package> findHistory(String username,int page);

    /**
     * find no take package table
     * @param communityId
     * @param page
     * @return list<Package>all history list</Package>
     */

    List<Package> findHistoryManager(int  communityId,int page);


}
