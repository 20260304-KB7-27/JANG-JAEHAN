package lecture.after;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // ComponentScan을 위한
//@Primary // 동일한 타입의 여러 Bean중에 우선 주입 됨
public class SMSSender extends EmailSender { // 다형성을 위한 상속

    // 문자 보내는 기능
    @Override
    public void send(String message) {
        System.out.println("SMS 발송 : " + message);
    }
}
