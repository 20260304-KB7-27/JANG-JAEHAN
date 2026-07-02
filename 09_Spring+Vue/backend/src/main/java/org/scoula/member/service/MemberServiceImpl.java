package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.mapper.MemberMapper;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final MemberMapper mapper;
    final PasswordEncoder passwordEncoder;

    // 중복 체크
    @Override
    public Boolean checkDuplicate(String username) {
        MemberVO memberVO = mapper.findByUsername(username);
        return memberVO != null ? true : false;
    }

    // 회원가입
    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO dto) {

        MemberVO member = dto.toVO(); // db 매핑을 위해 전달받은 dto를 vo로 변환

        // 1. 회원가입 한 비밀번호 암호화 후 저장
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 2. 회원 정보 저장
        mapper.insert(member);

        // 3. 권한 정보 저장
        AuthVO auth = new AuthVO();
        auth.setUsername(member.getUsername());
        auth.setAuth("ROLE_MEMBER"); // 기본 권한 설정

        mapper.insertAuth(auth);

        // 4. 아바타 파일 저장
        saveAvatar(dto.getAvatar(), member.getUsername());

        return get(member.getUsername());

    }

    // 단건(상세조회)
    private MemberDTO get(String username) {

        MemberVO memberVO = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);

        return MemberDTO.of(memberVO);

    }

    // 아바타 파일 저장 메서드
    private void saveAvatar(MultipartFile avatar, String username) {
        if(avatar != null && !avatar.isEmpty()) {

            File dest = new File("D:/upload/avatar", username + ".png");
            try {
                avatar.transferTo(dest); // 파일 저장
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
