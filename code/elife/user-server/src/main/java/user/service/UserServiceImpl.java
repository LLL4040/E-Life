package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dao.ManagerDao;
import user.dao.UserDao;
import user.entity.Community;
import user.entity.Manager;
import user.entity.User;
import user.repository.CommunityRepository;

import java.util.List;

/**
 * @author ztHou
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ManagerDao managerDao;
    private final IdentifyService identifyService;
    private final CommunityRepository communityRepository;

    public UserServiceImpl(UserDao userDao, ManagerDao managerDao, IdentifyService identifyService, CommunityRepository communityRepository) {
        this.userDao = userDao;
        this.managerDao = managerDao;
        this.identifyService = identifyService;
        this.communityRepository = communityRepository;
    }


    @Override
    public JSONObject usernameAvailable(String username) {
        JSONObject result = new JSONObject();
        Boolean flag = userDao.existByUsername(username);
        result.put("available", (flag) ? 0 : 1);
        return result;
    }

    @Override
    public JSONObject usernameAvailableManager(String username) {
        JSONObject result = new JSONObject();
        Boolean flag = managerDao.existByUsername(username);
        result.put("available", (flag) ? 0 : 1);
        return result;
    }

    @Override
    public JSONObject phoneAvailable(String phone) {
        JSONObject result = new JSONObject();
        Boolean flag = userDao.existsByPhone(phone);
        result.put("available", (flag) ? 0 : 1);
        return result;
    }

    @Override
    public JSONObject phoneAvailableManager(String phone) {
        JSONObject result = new JSONObject();
        Boolean flag = managerDao.existsByPhone(phone);
        result.put("available", (flag) ? 0 : 1);
        return result;
    }


    @Override
    public JSONObject registerUser(String username, String password, String phone, String code, String email, Long communityId) {
        JSONObject result = new JSONObject();
        result.put("register", 0);
        String available = "available";
        if (((int) usernameAvailable(username).get(available) != 1) || ((int) usernameAvailable(phone).get(available) != 1)) {
            return result;
        }
        try {
            User user = new User(username, password, phone, email, 0, communityId);
            Boolean flag = identifyService.verifyIdentifyCode(phone, code);
            if (!flag) {
                /* 手机验证码错误 */
                return result;
            } else {
                userDao.save(user);
                result.put("register", 1);
                return result;
            }
        } catch (Exception e) {
            result.put("register", 0);
        }
        return result;
    }

    @Override
    public JSONObject registerMerchant(String username, String password, String phone, String code, String email, Long communityId) {
        JSONObject result = new JSONObject();
        result.put("register", 0);
        String available = "available";
        if (((int) usernameAvailable(username).get(available) != 1) || ((int) usernameAvailable(phone).get(available) != 1)) {
            return result;
        }
        try {
            User user = new User(username, password, phone, email, 1, communityId);
            Boolean flag = identifyService.verifyIdentifyCode(phone, code);
            if (!flag) {
                /* 手机验证码错误 */
                return result;
            } else {
                userDao.save(user);
                result.put("register", 1);
                return result;
            }
        } catch (Exception e) {
            result.put("register", 0);
        }
        return result;
    }

    @Override
    public JSONObject registerManager(String username, String password, String phone, String code, String email, Long communityId) {
        JSONObject result = new JSONObject();
        result.put("register", 0);
        String available = "available";
        if (((int) usernameAvailableManager(username).get(available) != 1) || ((int) usernameAvailableManager(phone).get(available) != 1)) {
            return result;
        }
        try {
            Manager manager = new Manager(username, password, phone, email, 0, communityId);
            Boolean flag = identifyService.verifyIdentifyCode(phone, code);
            if (!flag) {
                /* 手机验证码错误 */
                return result;
            } else {
                managerDao.save(manager);
                result.put("register", 1);
                return result;
            }
        } catch (Exception e) {
            result.put("register", 0);
        }
        return result;
    }

    @Override
    public JSONObject login(String username, String password, String id) {
        JSONObject result = new JSONObject();
        result.put("login", 0);
        if (!userDao.existByUsername(username)) {
            return result;
        } else {
            Integer role = "0".equals(id) ? 0 : 1;
            User user = userDao.findByUsername(username);
            if (!user.getPassword().equals(password) || !user.getRole().equals(role)) {
                return result;
            } else {
                result.put("role", user.getRole());
                result.put("username", user.getUsername());
                result.put("communityId", user.getCommunityId());
                result.put("phone", user.getPhone());
                result.put("email", user.getEmail());
                result.put("login", 1);
                return result;
            }
        }
    }

    @Override
    public JSONObject loginPhone(String phone, String code, String id) {
        JSONObject result = new JSONObject();
        result.put("login", 0);
        if (!userDao.existsByPhone(phone)) {
            return result;
        } else {
            User user = userDao.findByPhone(phone);
            Integer role = "0".equals(id) ? 0 : 1;
            if(!user.getRole().equals(role)){
                return result;
            }
            if (!identifyService.verifyIdentifyCode(phone, code)) {
                return result;
            } else {
                result.put("username", user.getUsername());
                result.put("communityId", user.getCommunityId());
                result.put("phone", user.getPhone());
                result.put("email", user.getEmail());
                result.put("login", 1);
                return result;
            }
        }
    }

    @Override
    public JSONObject findPassword(String phone, String code, String password) {
        JSONObject result = new JSONObject();
        result.put("findPassword", 0);
        if (!userDao.existsByPhone(phone)) {
            result.put("exists", 0);
            return result;
        } else {
            result.put("exists", 1);
            if (!identifyService.verifyIdentifyCode(phone, code)) {
                result.put("identify", 0);
                return result;
            } else {
                User user = userDao.findByPhone(phone);
                user.setPassword(password);
                userDao.save(user);
                result.put("findPassword", 1);
                return result;
            }
        }
    }

    @Override
    public JSONObject loginManager(String username, String password) {
        JSONObject result = new JSONObject();
        result.put("login", 0);
        if (!managerDao.existByUsername(username)) {
            return result;
        } else {
            Manager manager = managerDao.findByUsername(username);
            if (!manager.getPassword().equals(password) || manager.getRole() == 0) {
                return result;
            } else {
                result.put("login", 1);
                result.put("username", manager.getUsername());
                result.put("communityId", manager.getCommunityId());
                result.put("phone", manager.getPhone());
                result.put("email", manager.getEmail());
                return result;
            }
        }
    }

    @Override
    public JSONObject loginPhoneManager(String phone, String code) {
        JSONObject result = new JSONObject();
        result.put("login", 0);
        if (!managerDao.existsByPhone(phone)) {
            return result;
        } else {
            Manager manager = managerDao.findByPhone(phone);
            if (!identifyService.verifyIdentifyCode(phone, code)) {
                return result;
            } else {
                result.put("login", 1);
                result.put("username", manager.getUsername());
                result.put("communityId", manager.getCommunityId());
                result.put("phone", manager.getPhone());
                result.put("email", manager.getEmail());
                return result;
            }
        }
    }

    @Override
    public JSONObject findPasswordManager(String phone, String code, String password) {
        JSONObject result = new JSONObject();
        result.put("findPassword", 0);
        if (!managerDao.existsByPhone(phone)) {
            result.put("exists", 0);
            return result;
        } else {
            result.put("exists", 1);
            if (!identifyService.verifyIdentifyCode(phone, code)) {
                result.put("identify", 0);
                return result;
            } else {
                Manager manager = managerDao.findByPhone(phone);
                manager.setPassword(password);
                managerDao.save(manager);
                result.put("findPassword", 1);
                return result;
            }
        }
    }

    @Override
    public JSONObject changeManagerRole(String username, Integer role) {
        JSONObject result = new JSONObject();
        result.put("changeRole", 0);
        if (!managerDao.existByUsername(username)) {
            result.put("exists", 0);
            return result;
        } else {
            result.put("exists", 1);
            try {
                Manager manager = managerDao.findByUsername(username);
                manager.setRole(role);
                result.put("changeRole", 1);
                return result;
            } catch (Exception e) {
                return result;
            }

        }
    }

    @Override
    public JSONArray showCommunities(){
        JSONArray jsonArray = new JSONArray();
        List<Community> communityList = communityRepository.findAll();
        for(Community community : communityList){
            JSONObject object = new JSONObject();
            object.put("id", community.getId());
            object.put("name", community.getCommunityName());
            object.put("information", community.getCommunityInfo());
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }

    @Override
    public JSONObject getCommunityById(Long id){
        JSONObject object = new JSONObject();
        Community community = communityRepository.findById(id);
        object.put("community", community.getCommunityName());
        object.put("communityInfo", community.getCommunityInfo());
        object.put("address", community.getAddress());
        return object;
    }

}
