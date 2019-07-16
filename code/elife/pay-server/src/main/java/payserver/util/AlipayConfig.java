package payserver.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /** 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     *
     */
    public static String app_id = "2016101000654777";

    /** 商户私钥，您的PKCS8格式RSA2私钥
     *
     */
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzHRsGduAkeDANCHABKIm6CvCCvhJxq1tdwD4LBUxG8CrvN3ThZOAC9tzpSpJvNlZv4mt07n5aN4iwMA8wfp3CBdTXn+4ktc8eY9ovhPLrgyCxe778AN2xaqzEI09RSxEKkwte6t1YOt0UO0Qp7ec+2t7UK8mpIXBTxsS7AqL6Q9Ca7R4cueSmyqz6D0bia15UCi2fXfQ2NtO6wk2Rc7qwYRNycrdXqGg4imcmLan9MiuScIufwm0YFtX7Q7ISl6MPSwNStFdQuu8SwmZ9f/MVBs71fkRQRHTA4Z569MUrx7v+IgJETrNWLQWBR0jL7Ei+Tg1ilPJXpqna9EAh/ZjvAgMBAAECggEAf31gFyHWOR8zVJOE5c4s+jd/KhCQoEgQKJMFlmKXAIdCiDM5BpX+vR1p8MQbpv1OWSUYx4padWzB5mbO6P223Lu53CECKS8f1cnNK7W+EtSrGKm0LzuOkRIVFybq9jowTw6LMWyLUJnVcukcg5vK1XqE0RC54aDjiz4YGeSujOM3acQu68Yf/aB0hiGE/G4OKT+Diw7e7A5529zDs6W+4HhFE49eQ7WGga2RpOrjImdl1waXy2nts/aj9PCLGi+7svJH7uZt3MVftc11wr7wte3CoX3BTJ0iOm2o6nxIrr9+szf2pC1F+v9uAfqWYzpXGOrGnUZQIvUdnYUgLvu4AQKBgQDW8c5PweRkfmjo44AvbB2sM86YJ9ArX2FOMyQQvVONxkISybeZao9ePdOdzx68/EACtaJIMvpKTVSGUDfvFOAFhfBltkTCtrN3qLonOhOdwLKlIjPtxMtggH3N14+Q8jsBnUN1QN/dnjzNmt3O3ly7Wu7LFPekY3TTJqwCgxq8bwKBgQDVU0UKxBYbByUJMxtYc0HqXq0MKtdxf+A+1SiqwpmvlJoqK6uFbuYkls/LCeU3p3UIsKiZ0MizVUs+9xsKL+1SGJPZiFZPWUEK/+O+czVCd9rbBeijUZK5l/pAz3IYHC1tk6D8/OntgzmIBAsgShrYObKXCUYgdo7/kE90FQIrgQKBgHIQ8paa47R87PmiOZVw/7Ix8BIJFszdmgrg0uPbRSr4qnnD+J3+0cSGdPXLQ5mxF9sYPt2nx/GEZjW8/mHvJw9nS/L+LjFSuZznupo0lNJjv6ooo3wrsm4s4hZJrdOS6MNrjK/91I+MeOH3rHwqvvViRVUadG0VppqjbCcNIjQbAoGBAMEJYOilF/vNM05n2M7pPTSAB8eC3QHv3VP+P5aGEdw4dGjEq6lK+ScS8WWhxrHd7qG2elr1+yeMf1Ky+Nz3EaHAa8nk/XVAJwN1Gvkz93X2Tyzy49kl78fSJrqO/Yejt9f4Ry2nD2fF30GdpxPI+xG+5ESYN8UYg73vMHayrAwBAoGAdrc33CHqTvRI5g7yB6WQa3kYs2BIGcqziufxA4+8aaW69wEA6Fyfq71sp9/AKje4BPhBXC3UVrNa/vzWip7B0L422l8Gv6fknmOfBHtTsv5Ukq3RJjLAZAPcz9nVwBVW+9kQtz4DMy6tCxhSmMMyJE2zZmO+ElUTk64gWwPm6fY=";

    /** 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     *
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsx0bBnbgJHgwDQhwASiJugrwgr4ScatbXcA+CwVMRvAq7zd04WTgAvbc6UqSbzZWb+JrdO5+WjeIsDAPMH6dwgXU15/uJLXPHmPaL4Ty64MgsXu+/ADdsWqsxCNPUUsRCpMLXurdWDrdFDtEKe3nPtre1CvJqSFwU8bEuwKi+kPQmu0eHLnkpsqs+g9G4mteVAotn130NjbTusJNkXO6sGETcnK3V6hoOIpnJi2p/TIrknCLn8JtGBbV+0OyEpejD0sDUrRXULrvEsJmfX/zFQbO9X5EUER0wOGeevTFK8e7/iICRE6zVi0FgUdIy+xIvk4NYpTyV6ap2vRAIf2Y7wIDAQAB";

    /** 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     *
     */
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    /** 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     *
     */
    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    /** 签名方式
     *
     */
    public static String sign_type = "RSA2";

    /** 字符编码格式
     *
     */
    public static String charset = "utf-8";

    /** 支付宝网关
     *
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /** 支付宝网关
     *
     */
    public static String log_path = "D:\\logs\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


