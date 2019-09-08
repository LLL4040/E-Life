package apackage.entity;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ztHou
 */
@Entity
@Table(name = "identify")
public class Identify implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "phone")
    private String phone;

    @Column(name = "code")
    private String code;

    @Column(name = "time")
    private String time;

    public Identify() {
    }

    public Identify(String phone, String code, String time) {
        this.phone = phone;
        this.code = code;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean sendMessage() throws com.aliyuncs.exceptions.ClientException {
        /* 云通信API产品名和域名 */
        String product = "Dysmsapi";
        String domain = "dysmsapi.aliyuncs.com";

        /* 阿里云access key */
        String accessKeyId = "LTAI9hske46Yv2pI";
        String accessKeySecret = "vfR2qZIpewPxwyRo2nSXUNUvc8IxTc";

        /* 超时时间 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();

        /* 待发送手机号 */
        request.setPhoneNumbers(this.phone);

        /* 短信签名 */
        request.setSignName("智能小区");

        /* 模板CODE */
        request.setTemplateCode("SMS_169636315");
        request.setTemplateParam("{\"code\":\"" + this.code + "\"}");

        /* 发送短信 */
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        String success = "OK";
        if (sendSmsResponse.getCode() != null && success.equals(sendSmsResponse.getCode())) {
            System.out.println("短信发送成功！");
            return true;
        } else {
            System.out.println("短信发送失败！");
            return false;
        }
    }
}
