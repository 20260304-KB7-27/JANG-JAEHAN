package lecture.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

//    @Autowired // 필드 주입
//    private ClassB classB;

    private final ClassB classB;

    @Autowired // 생성자 주입
    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public void doSomething() {
        System.out.println("Class A is Working");
        classB.doSomething();
    }
}
