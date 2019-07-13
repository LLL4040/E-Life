package group.service;

import group.dao.BargainDao;
import group.entity.Bargain;
import group.entity.Merchant;
import group.service.BargainService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ztHou
 */
@Service
public class BargainServiceImpl implements BargainService {
    private final BargainDao bargainDao;

    public BargainServiceImpl(BargainDao bargainDao) {
        this.bargainDao = bargainDao;
    }


    @Override
    public JSONObject addBargain(String startTime, String endTime, String title, Long merchantId, String goods, String content, Integer status){
        JSONObject result = new JSONObject();
        result.put("add", 0);
        try{
            Merchant merchant = new Merchant();
            merchant.setId(merchantId);
            Bargain bargain = new Bargain(startTime, endTime, title, merchant, goods, content, status);
            Bargain bargainSaved = bargainDao.save(bargain);
            result.put("bargain", bargainSaved.getId());
            result.put("add", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONArray getAllBargain(){
        JSONArray jsonArray = new JSONArray();
        List<Bargain> bargainList = bargainDao.findAll();
        for(Bargain bargain : bargainList){
            JSONObject object = new JSONObject();
            object.put("startTime", bargain.getStartTime());
            object.put("endTime", bargain.getEndTime());
            object.put("title", bargain.getTitle());
            object.put("merchantId", bargain.getMerchant().getId());
            object.put("address", bargain.getMerchant().getAddress());
            object.put("name", bargain.getMerchant().getName());
            object.put("goods", bargain.getGoods());
            object.put("content", bargain.getContent());
            object.put("status", bargain.getStatus());
            object.put("phone", bargain.getMerchant().getPhone());
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }

    @Override
    public JSONObject deleteBargain(Long id){
        JSONObject result = new JSONObject();
        result.put("delete", 0);
        try{
            bargainDao.deleteById(id);
            result.put("delete", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }
}
