package plus.naomi.proxy.jdk.service;

/**
 * @author Naomi
 * @description
 * @date 2021/11/24 15:45
 */
public interface SmsService {
    String send(String message);

    String check(String message);
}
