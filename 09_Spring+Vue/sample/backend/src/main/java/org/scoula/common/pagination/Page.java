package org.scoula.common.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PRIVATE) // 생성자를 내부에서만 호출 가능 -> of 메서드로만 생성하기 위해
public class Page<T> {
    private int totalCount; // 전체 데이터 건수
    private int totalPage; //
    private List<T> list; // 페이징된 데이터 목록

    @JsonIgnore // JSON 직렬화에서 제외, 프론트엔드에서는 필요 없음
    private PageRequest pageRequest; // 내부 계산용 (getPageNum, getAmount)를 하기 위한 용도

    // 페이지 객체 생성용 정적 메서드
    public static <T> Page of(PageRequest pageRequest, int totalCount, List<T> list) {

        // 총 페이지 갯수 계산용
        int totalPage = (int)Math.ceil((double) totalCount / pageRequest.getAmount());

        return new Page(totalCount, totalPage, list, pageRequest);
    }

    // 현재 페이지 번호 반환
    public int getPageNum() {
        return pageRequest.getPage();
    }

    // 페이지당 데이터 건수 반환
    public int getAmount() {
        return pageRequest.getAmount();
    }
}
