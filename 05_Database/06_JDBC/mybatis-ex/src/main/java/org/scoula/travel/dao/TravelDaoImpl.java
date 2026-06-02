package org.scoula.travel.dao;

import org.apache.ibatis.session.SqlSession;
import org.scoula.travel.database.MyBatisConfig;
import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/*
SQLSession 메서드
- selectOne(id) : 결과가 정확히 1건인 select , 1건 넘어가면 exception 발생
- selectList(id) : 결과가 0건 이상인 (응답이 List 형태로 나옴)
ㄴ> id는 namespace값 + 메서드명(dao 구현체의 오버라이딩한 메서드들)
- insert(id, param) : return이 영향 받은 행의 수
- update(id, param) : return이 영향 받은 행의 수
- delete(id, param) : return이 영향 받은 행의 수

Transaction
- commit() : 변경사항을 DB에 반영
- rollback() : 변경사항 취소
 */

public class TravelDaoImpl implements TravelDao{

    static final String NAMESPACE = "org.scoula.travel.dao.TravelDao.";

    @Override
    public int getTotalCount() {
        return MyBatisConfig.getSqlSession()
                // org.scoula.travel.dao.TravelDao.getTotalCount
                .selectOne(NAMESPACE + "getTotalCount");
    }

    @Override
    public List<TravelVO> getTravels(int start, int end) {
        return List.of();
    }

    @Override
    public List<String> getDistricts() {
        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getDistricts");
    }

    @Override
    public List<TravelVO> getTravelsByDistrict(String district) {
        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getTravelsByDistrict", district);
    }

    @Override
    public Optional<TravelVO> getTravel(Long no) {
        return Optional.empty();
    }

    /*
    트랜잭션 처리 메서드
     */
    private void transaction(Consumer<SqlSession> action) {
        try (SqlSession session = MyBatisConfig.getSqlSession()) {
            action.accept(session);
            session.commit(); // db에 반영
        }
    }

    @Override
    public void insert(TravelVO travel) {
        transaction(session -> session.insert(NAMESPACE + "insert", travel));
    }

    @Override
    public void update(TravelVO travel) {
        transaction(session
                -> session.update(NAMESPACE + "update" , travel));
    }

    @Override
    public void remove(Long no) {
        transaction(session -> session.delete(NAMESPACE + "remove", no));
    }
}
