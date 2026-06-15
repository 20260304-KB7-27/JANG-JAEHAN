package lecture.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        BeanCurrentlyInCreationException
        생성자 주입 시 순환 참조 문제가 발생할 경우 예외 발생
         */

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ClassA classA = context.getBean(ClassA.class);

        System.out.println("입력 : ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        if(str.equals("1")) {
            classA.doSomething();
        }

        sc.close();



    }
}
