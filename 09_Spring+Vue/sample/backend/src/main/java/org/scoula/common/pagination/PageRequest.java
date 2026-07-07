package org.scoula.common.pagination;


import lombok.Data;

@Data
public class PageRequest {

    private int page;
    private int amount;

    public PageRequest() {
        page = 1;
        amount = 5;
    }

    public PageRequest(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }

    public static PageRequest of(int page, int amount){
        return new PageRequest(page, amount);
    }

    // MyBatis LIMIT 절에 사용할 오프셋 getter 생성
    public int getOffset() {
        return (page-1) * amount;
    }
}
