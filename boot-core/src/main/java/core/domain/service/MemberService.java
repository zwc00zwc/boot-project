package core.domain.service;

import core.domain.mapper.MemberMapper;
import core.domain.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XR on 2016/12/7.
 */
@Service
public class MemberService implements IMember {
    @Autowired
    private MemberMapper memberMapper;

    public List<Member> queryList() {
        return memberMapper.querylist();
    }
}
