package user.service;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import user.dao.MerchantDao;
import user.dao.UserDao;
import user.entity.Merchant;
import user.entity.User;

import java.util.List;

/**
 * @author ztHou
 */
@Service
public class MerchantServiceImpl implements MerchantService{
    private final MerchantDao merchantDao;
    private final UserDao userDao;
    private final UserService userService;

    public MerchantServiceImpl(MerchantDao merchantDao, UserDao userDao, UserService userService) {
        this.merchantDao = merchantDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public JSONObject register(String username, String password, String email, String name, String merchantPhone, String type,
                            String address, String detail, String phone, String code, Long communityId){
        JSONObject result = new JSONObject();
        String register = "register";
        result.put(register, 0);
        JSONObject jsonObject = userService.registerMerchant(username, password, phone, code, email, communityId);
        if((int)jsonObject.getAsNumber(register) != 1){
            return result;
        }
        try{
            User user = userDao.findByUsername(username);
            Merchant merchant = new Merchant(user, name, merchantPhone, address, detail, type);
            merchantDao.save(merchant);
            result.put(register, 1);
            return result;
        } catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONObject changeMerchant(Long id, String name, String merchantPhone, String type,
                                     String address, String detail){
        JSONObject result = new JSONObject();
        String change = "change";
        result.put(change, 0);
        if(!merchantDao.existsById(id)){
            return result;
        }
        try{
            Merchant merchant = merchantDao.findById(id);
            merchant.setName(name);
            merchant.setPhone(merchantPhone);
            merchant.setType(type);
            merchant.setAddress(address);
            merchant.setDetail(detail);
            merchantDao.save(merchant);
            result.put(change, 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONArray findAllByType(String type){
        JSONArray jsonArray = new JSONArray();
        List<Merchant> merchantList = merchantDao.findAllByType(type);
        for(Merchant merchant : merchantList){
            JSONObject object = new JSONObject();
            object.put("name", merchant.getName());
            object.put("phone", merchant.getPhone());
            object.put("address", merchant.getAddress());
            object.put("type", merchant.getType());
            object.put("detail", merchant.getDetail());
            object.put("username", merchant.getUser().getUsername());
            object.put("id", merchant.getId());
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }

    @Override
    public JSONArray findAll(){
        JSONArray jsonArray = new JSONArray();
        List<Merchant> merchantList = merchantDao.findAll();
        for(Merchant merchant : merchantList){
            JSONObject object = new JSONObject();
            object.put("address", merchant.getAddress());
            object.put("type", merchant.getType());
            object.put("detail", merchant.getDetail());
            object.put("name", merchant.getName());
            object.put("phone", merchant.getPhone());
            object.put("username", merchant.getUser().getUsername());
            object.put("id", merchant.getId());
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }

    @Override
    public JSONObject deleteMerchant(Long id){
        JSONObject result = new JSONObject();
        String delete = "delete";
        result.put(delete, 1);
        if(!merchantDao.existsById(id)){
            return result;
        }
        try{
            Merchant merchant = merchantDao.findById(id);
            String username = merchant.getUser().getUsername();
            merchantDao.deleteById(id);
            userDao.deleteByUsername(username);
            return result;
        } catch (Exception e){
            if(!merchantDao.existsById(id)){
                return result;
            }else{
                result.put(delete, 0);
                return result;
            }
        }
    }

    @Override
    public JSONObject getMerchant(Long id){
        JSONObject object = new JSONObject();
        Merchant merchant = merchantDao.findById(id);
        object.put("id", merchant.getId());
        object.put("merchantPhone", merchant.getPhone());
        object.put("detail", merchant.getDetail());
        object.put("type", merchant.getType());
        object.put("address", merchant.getAddress());
        object.put("name", merchant.getName());
        object.put("username", merchant.getUser().getUsername());
        object.put("email", merchant.getUser().getEmail());
        object.put("phone", merchant.getUser().getPhone());
        object.put("communityId", merchant.getUser().getCommunityId());
        return object;
    }

    @Override
    public JSONObject getMerchantByUsername(String username){
        JSONObject object = new JSONObject();
        Merchant merchant = merchantDao.findByUsername(username);
        object.put("id", merchant.getId());
        object.put("name", merchant.getName());
        object.put("merchantPhone", merchant.getPhone());
        object.put("detail", merchant.getDetail());
        object.put("type", merchant.getType());
        object.put("address", merchant.getAddress());
        return object;
    }
}
