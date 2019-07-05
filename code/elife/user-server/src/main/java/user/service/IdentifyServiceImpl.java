package user.service;

import org.springframework.stereotype.Service;
import user.dao.IdentifyDao;
import user.entity.Identify;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author ztHou
 */
@Service
public class IdentifyServiceImpl implements IdentifyService{
    private final IdentifyDao identifyDao;
    private static Pattern pattern = Pattern.compile("^[1]\\d{10}$");

    public IdentifyServiceImpl(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    @Override
    public Boolean sendIdentifyCode(String phone){
        if(!pattern.matcher(phone).matches()){
            return false;
        }
        int number = (int)((Math.random()*9+1)*100000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        try{
            Identify identify;
            try{
                identify = identifyDao.findByPhone(phone);
                identify.setCode(Integer.toString(number));
                identify.setTime(dateTime);
            }catch (Exception ex){
                identify = new Identify(phone, Integer.toString(number), dateTime);
            }
            identifyDao.save(identify);
            return identify.sendMessage();
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean verifyIdentifyCode(String phone, String code){
        try{
            if(!identifyDao.exists(phone)){
                return false;
            }else {
                Identify identify = identifyDao.findByPhone(phone);
                /* 时间格式 */
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                /* 得到现在时间 */
                Date dateTime = df.parse(df.format(new Date()));
                Calendar now = Calendar.getInstance();
                now.setTime(dateTime);
                /* 得到发信息的时间 */
                Date originTime = df.parse(identify.getTime());
                Calendar begin = Calendar.getInstance();
                begin.setTime(originTime);
                /* 得到超时时间 */
                Calendar end = Calendar.getInstance();
                end.setTime(originTime);
                end.add(Calendar.MINUTE, 5);
                Boolean flag =  (code.equals(identify.getCode()) && (now.after(begin) && now.before(end)));
                if(flag){
                    System.out.println("delete");
                    identifyDao.deleteByPhone(phone);
                }
                System.out.println("yes");
                return flag;
            }
        }catch (Exception e){
            System.out.println("no");
            return false;
        }
    }
}
