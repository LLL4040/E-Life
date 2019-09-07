package apackage.service;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface FriendService {
    /**
     * search friend list
     * @param username username searching
     * @return search friend list
     */
    JSONArray friendSearchList(String username);

    /**
     * send friend request from username to friend
     * @param username username
     * @param friend friend
     * @param content content
     * @return whether send friend request successfully
     */
    JSONObject sendFriendRequest(String username, String friend, String content);

    /**
     * get all friends of username
     * @param username username
     * @return all friends of username
     */
    JSONArray friendList(String username);

    /**
     * get all requests from username
     * @param username username
     * @return all requests from username
     */
    JSONArray requestList(String username);

    /**
     * all requests to username
     * @param username username
     * @return all requests to username
     */
    JSONArray responseList(String username);

    /**
     * friend accept username's request
     * @param id id
     * @return whether accept successfully
     */
    JSONObject acceptRequest(Long id);
    /**
     * friend reject username's request
     * @param id id
     * @return whether reject successfully
     */
    JSONObject rejectRequest(Long id);

    /**
     * delete friend
     * @param username username
     * @param friend friend
     * @return whether delete friend successfully
     */
    JSONObject deleteFriend(String username, String friend);
}
