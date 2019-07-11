package user.dao;

import user.entity.Friend;

import java.util.List;

/**
 * @author ztHou
 */
public interface FriendDao {
    /**
     * save friend information
     * @param friend friend data
     * @return friend data saved
     */
    Friend save(Friend friend);

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
     * find all friends of username
     * @param username username
     * @return all friends of username
     */
    List<Friend> allFriends(String username);

    /**
     * find all requests haven't been accepted or have been rejected
     * @param username username
     * @return all requests haven't been accepted or have been rejected
     */
    List<Friend> allRequests(String username);

    /**
     * find all requests haven't been handled to username
     * @param username username
     * @return all requests haven't been handled to username
     */
    List<Friend> allResponses(String username);

    /**
     * whether exists friendship between username and friend
     * @param username username
     * @param friend friend
     * @param status status
     * @return whether exists friendship between username and friend
     */
    Boolean existByUsernameAndFriendAndStatus(String username, String friend, Integer status);


    /**
     * delete requests between username and friend
     * @param username username
     * @param friend friend
     */
    void deleteByUsernameAndFriend(String username, String friend);
}
