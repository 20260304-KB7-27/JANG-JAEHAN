package org.scoula.travel.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoula.travel.domain.TravelVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelDaoImplTest {

    private TravelDao dao;

    @BeforeEach
    void setup() {
        dao = new TravelDaoImpl();
    }

    @Test
    void getTravels() {

        List<TravelVO> travels = dao.getTravels();

        System.out.println(travels);

        assertNotNull(travels);
        assertFalse(travels.isEmpty(), "여행지 목록이 비면 안됨.");
    }

    // 여행지 지역별 조회 가능
    // pass하게끔 만들어보세요
    @Test
    void getTravelsByDistrict() {
        String district = dao.getDistricts().get(0);
        List<TravelVO> travels = dao.getTravels(district);
        assertNotNull(travels);
        travels.forEach(t -> assertEquals(district, t.getDistrict()));
    }
}