package lecture.before;

// 서비스 로직
public class NotiService {

    /*
    문제점
    1. 모듈을 변경하기 위해 -> NotiService의 내부 코드 변화가 일어나야함.
     */

//    private EmailSender emailSender = new EmailSender();
    private SMSSender smsSender = new SMSSender(); // 강결합

    public void notify(String message) {
//        emailSender.send(message);

        smsSender.send(message);

    }

}
