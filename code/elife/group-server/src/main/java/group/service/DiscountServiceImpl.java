package group.service;

import group.dao.DiscountDao;
import group.entity.Discount;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ztHou
 */
@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountDao discountDao;


    public DiscountServiceImpl(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    @Override
    public JSONObject addDiscount(String startTime, String endTime, Long merchantId, Integer number, String content, Integer status, Long communityId, String title){
        JSONObject result = new JSONObject();
        result.put("add", 0);
        try{
            Discount discount = new Discount(startTime, endTime, merchantId, number, content, status, communityId, title);
            discountDao.save(discount);
            result.put("add", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONObject deleteDiscount(Long id){
        JSONObject result = new JSONObject();
        result.put("delete", 0);
        try{
            discountDao.deleteById(id);
            result.put("delete", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONArray findDiscountByMerchantId(Long merchantId){
        JSONArray jsonArray = new JSONArray();
        if(!discountDao.existsByMerchantId(merchantId)){
            return jsonArray;
        }
        try{
            List<Discount> discountList = discountDao.findByMerchantId(merchantId);
            for(Discount discount : discountList){
                JSONObject object = new JSONObject();
                object.put("id", discount.getId());
                object.put("start", discount.getStartTime());
                object.put("end", discount.getEndTime());
                object.put("mId", discount.getMerchantId());
                object.put("num", discount.getNumber());
                object.put("title", discount.getTitle());
                object.put("content", discount.getContent());
                object.put("status", discount.getStatus());
                object.put("communityId", discount.getCommunityId());
                jsonArray.appendElement(object);
            }
            return jsonArray;
        }catch (Exception e){
            return jsonArray;
        }
    }

    @Override
    public JSONArray findDiscountByCommunityId(Long communityId){
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        List<Discount> discountList = discountDao.findAllByEndTimeAfterAndCommunityId(dateTime, communityId);
        for(Discount discount : discountList){
            JSONObject object = new JSONObject();
            object.put("id", discount.getId());
            object.put("num", discount.getNumber());
            object.put("content", discount.getContent());
            object.put("status", discount.getStatus());
            object.put("start", discount.getStartTime());
            object.put("end", discount.getEndTime());
            object.put("mId", discount.getMerchantId());
            object.put("communityId", discount.getCommunityId());
            object.put("title", discount.getTitle());
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }
}
