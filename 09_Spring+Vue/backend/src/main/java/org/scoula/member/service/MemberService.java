package org.scoula.member.service;

import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;

public interface MemberService {

    // 회원 pk 중복검사
    Boolean checkDuplicate(String username);

    // 회원가입
    MemberDTO join(MemberJoinDTO member);
}
