package group.service;

import net.coobird.thumbnailator.Thumbnails;
import group.dao.DiscountDao;
import group.entity.Discount;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public JSONObject addDiscount(String startTime, String endTime, Long merchantId, Integer number,
                                  String content, Integer status, Long communityId, String title, MultipartFile photo){
        JSONObject result = new JSONObject();
        result.put("add", 0);
        try{
            try {
                String path = UUID.randomUUID() + photo.getOriginalFilename();
                if (!photo.isEmpty()) {
                    byte[] bytes = photo.getBytes();
                    BufferedOutputStream bufferedOutputStream = new
                            BufferedOutputStream(new FileOutputStream(new File("./group-server/pic/" + path)));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                }
                Thumbnails.of("./group-server/pic/"+path)
                        .size(80,80)
                        .keepAspectRatio(false)
                        .toFile("./group-server/cpic/"+path);
                Discount discount = new Discount(startTime, endTime, merchantId, number, content, status, communityId, title, path);
                discountDao.save(discount);
                result.put("add", 1);
                return result;
            }
            catch (Exception e){
                return result;
            }
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
                File file = new File("./group-server/cpic/" + discount.getPhoto());
                byte[] data = Files.readAllBytes(file.toPath());
                String photo = Base64.encodeBase64String(data);
                object.put("path", discount.getPhoto());
                object.put("photo", "data:image/jpg;base64," + photo);
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
        try{
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
                File file = new File("./group-server/cpic/" + discount.getPhoto());
                byte[] data = Files.readAllBytes(file.toPath());
                String photo = Base64.encodeBase64String(data);
                object.put("photo", "data:image/jpg;base64," + photo);
                object.put("path", discount.getPhoto());
                jsonArray.appendElement(object);
            }
            return jsonArray;
        }catch (Exception e){
            return jsonArray;
        }
    }

    @Override
    public JSONObject getBigPhoto(String path){
        JSONObject jsonObject = new JSONObject();
        try{
            File file = new File("./group-server/pic/" + path);
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("error","notfound");
            return jsonObject;
        }
    }
}
