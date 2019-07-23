package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.entity.Friend;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface FriendRepository extends JpaRepository<Friend, String> {
    /**
     * find by id
     * @param id id
     * @return friend found
     */
    Friend findById(Long id);
    /**
     * whether exists id
     * @param id id
     * @return whether exists id
     */
    Boolean existsById(Long id);

    /**
     * whether exists data that username and friend and status equals to the condition
     * @param username username
     * @param friend friend
     * @param status status
     * @return whether exists data that username and friend and status equals to the condition
     */
    Boolean existsByUsernameAndFriendAndStatus(String username, String friend, Integer status);

    /**
     * find all friends of username
     * @param username username
     * @param status status 1 represent all friends
     * @return all friends when status 1 or all friend requests when status 0
     */
    List<Friend> findAllByUsernameAndStatus(String username, Integer status);

    /**
     * find all requests of username that haven't been accepted
     * @param username username
     * @param status status 1 represent all friend requests haven't been accepted and friend requests rejected
     * @return all requests of username that haven't been accepted
     */
    List<Friend> findAllByUsernameAndStatusNot(String username, Integer status);

    /**
     * delete friend
     * @param username username
     * @param friend friend
     * @param status status only 1 which says that the current friend instead of just a request
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsernameAndFriendAndStatus(String username, String friend, Integer status);

    /**
     * delete all requests from username to friend
     * @param username username
     * @param friend friend
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByUsernameAndFriend(String username, String friend);

    /**
     * find all requests received
     * @param friend username
     * @param status status 0 represent haven't been accepted
     * @return all requests received but haven't been accepted
     */
    List<Friend> findAllByFriendAndStatus(String friend, Integer status);
}
