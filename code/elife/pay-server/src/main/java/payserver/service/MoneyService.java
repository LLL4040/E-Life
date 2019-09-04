package payserver.service;


import com.alipay.api.AlipayApiException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import payserver.entity.RoomSpace;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public interface MoneyService {

    /**
     * init room like A010302
     * @param type
     * @param maxNum
     * @param maxFloor
     * @param maxRoom
     * @param roomSpace
     * @param communityId
     * @return boolean
     */
    boolean initRoom(String type ,int maxNum,int maxFloor,int maxRoom,int roomSpace,int communityId);

    /**
     * get room space to register username for room
     * @param communityId
     * @return
     */
    JSONArray getRoomSpace(int communityId);

    /**
     * insert username to room
     * @param username
     * @param type
     * @param room
     * @param communityId
     * @return
     */
    boolean saveUsername(String username,String type,String room,int communityId);

    boolean calManMoney(BigDecimal money,int communityId);

    boolean initParkPlan(BigDecimal Amoney,BigDecimal Bmoney,int communityId);

    boolean changeParking(String username,String parking,String type, int communityId);

    boolean  calParking( int communityId);

    JSONObject getRoomByRoomNumber(String type,String room,int communityId);
}
