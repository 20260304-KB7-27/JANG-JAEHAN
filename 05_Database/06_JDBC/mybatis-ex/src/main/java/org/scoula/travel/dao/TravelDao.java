package org.scoula.travel.dao;

import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.Optional;

public interface TravelDao {

    int getTotalCount();

    // 페이지별 목록 얻기
    List<TravelVO> getTravels(int start, int end);

    List<String> getDistricts();                          // 권역 목록 얻기
    List<TravelVO> getTravelsByDistrict(String district); // 해당 권역의 목록 얻기
    Optional<TravelVO> getTravel(Long no);                // 특정 관광지 정보 얻기

    void insert(TravelVO travel);
    void update(TravelVO travel);
    void remove(Long no);
}