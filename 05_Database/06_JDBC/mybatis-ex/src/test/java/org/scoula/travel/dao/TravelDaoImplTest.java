package org.scoula.travel.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scoula.travel.domain.TravelVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelDaoImplTest {

    TravelDao dao = new TravelDaoImpl();

    @Test
    void getTotalCount() {
        int count = dao.getTotalCount();
        Assertions.assertTrue(count > 0, "Total Count should be greater than 0");
        System.out.printf("Total Count : %d\n", count);
    }

    @Test
    void getDistricts() {
        List<String> district = dao.getDistricts();
        Assertions.assertTrue(district.size() > 0);
        district.forEach(System.out::println);
    }

    @Test
    void getTravelsByDistrict() {
        List<TravelVO> list = dao.getTravelsByDistrict("강원권");
        list.forEach(System.out::println);
    }

    @Test
    void insert() {
        TravelVO travel = TravelVO.builder()
                .district("강원권")
                .title("두물머리")
                .description("경치 좋음")
                .address("남양주")
                .phone("111-222-3333")
                .build();
        dao.insert(travel);
        System.out.println(travel);
    }

    @Test
    void update() {
        TravelVO travel = TravelVO.builder()
                .no(115L) // 실제 no 확인 후 사용
                .district("수도권")
                .title("해너미 명소___")
                .description("해너미 명소로 유명해요___")
                .address("인천 광역시 서해바다")
                .phone("111-222-3333")
                .build();
        dao.update(travel);
    }

    @Test
    void remove() {
        Long no = 113L;
        dao.remove(no);
    }
}