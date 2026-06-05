package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

/*
POJO (Plain Old Java Object)
-> 순수한 Java로 만들어진 객체
-> lombok은 단순히 자바로 이루어진 코드를 생성하는 것이기 때문에 상관 X
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private ObjectId id; // MongoDB에도 ID가 객체 형태로 저장됨
    private String title;
    private String desc;
    private boolean done;
}
