package apackage.dao;

import org.springframework.stereotype.Repository;
import apackage.entity.Friend;
import apackage.repository.FriendRepository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class FriendDaoImpl implements FriendDao{
    private final FriendRepository friendRepository;

    public FriendDaoImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public Friend save(Friend friend){
        return friendRepository.save(friend);
    }

    @Override
    public Friend findById(Long id){
        return friendRepository.findById(id);
    }

    @Override
    public Boolean existsById(Long id){
        return friendRepository.existsById(id);
    }

    @Override
    public List<Friend> allFriends(String username){
        return friendRepository.findAllByUsernameAndStatus(username, 1);
    }

    @Override
    public List<Friend> allRequests(String username){
        return friendRepository.findAllByUsernameAndStatusNot(username, 1);
    }

    @Override
    public List<Friend> allResponses(String username){
        return friendRepository.findAllByFriendAndStatus(username, 0);
    }

    @Override
    public Boolean existByUsernameAndFriendAndStatus(String username, String friend, Integer status){
        return friendRepository.existsByUsernameAndFriendAndStatus(username, friend, status);
    }

    @Override
    public void deleteByUsernameAndFriend(String username, String friend){
        friendRepository.deleteAllByUsernameAndFriend(username, friend);
        friendRepository.deleteAllByUsernameAndFriend(friend, username);
    }
}
