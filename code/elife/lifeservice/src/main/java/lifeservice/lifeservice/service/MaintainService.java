package lifeservice.lifeservice.service;

import lifeservice.lifeservice.entity.Maintain;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
public interface MaintainService {
    /**
     * 该函数储存一条维修请求，是由用户发起的
     * @return  该函数返回生成的维修请求的id号
     * @param maintain 用户发起的维修请求*/
    long saveMaintian(Maintain maintain);
    /**
     * 该函数修改维修请求，修改它的maintainname，phone，managername
     * @return  返回一条修改维修请求成功的消息，说明已经分配修改人员，状态改为1，表示待处理
     * @param  id 生成请求的id号
     * @param maintianname 维修人员名字
     * @param phone 维修人员手机号
     * @param  managername 管理人员名字*/
    String managerMaintain(long id,String maintianname,String phone,String managername);
    /**
     * 该函数返回用户发的所有的待处理的请求
     * @return 返回请求单的列表
     * @param username 用户名
     * @param status 维修请求的状态*/
    List<Maintain> findbyUsernameAndStatus(String username, int status);
    /**
     * 用户可以取消或者更改维修请求为已完成
     * @return 返回更改成功的消息
     * @param id 维修单的id
     * @param status 维修单的状态，2是取消，3是已完成*/
    String userEditMaintain(long id,int status);
}
