package user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import user.entity.User;
import user.repository.UserRepository;

/**
 * @author ztHou
 */
@Repository
public class UserDaoImpl implements UserDao{
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public Boolean existByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    @Override
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}
