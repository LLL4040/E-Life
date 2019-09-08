package apackage.dao;


import org.springframework.stereotype.Repository;
import apackage.entity.User;
import apackage.repository.UserRepository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public List<User> findAllByUsernameContains(String username){
        return userRepository.findAllByUsernameContains(username);
    }

    @Override
    public List<User> findAllByCommunityId(Long communityId){
        return  userRepository.findAllByCommunityId(communityId);
    }

    @Override
    public List<String> findUsernameByCommunityId(Long communityId){
        return userRepository.findUsernameByCommunityId(communityId);
    }
}
