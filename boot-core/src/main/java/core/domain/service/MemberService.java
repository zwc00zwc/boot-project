package core.domain.service;

import core.domain.mapper.MemberMapper;
import core.domain.model.Member;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    //@Transactional
    public void test(Long id,String name) {
        //Member member = update(id,name);
//        Member member = ((MemberService) AopContext.currentProxy()).update(id);
//        Member member1 = new Member();
//        member1.setUserName("ddd");
//        memberMapper.insert(member1);
        //((MemberService) AopContext.currentProxy()).update(id,name);
        update(id,name);
        System.out.print("test");
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public Member update(Long id,String name) {
        Member member1 = new Member();
        member1.setUserName("ddd");
        memberMapper.insert(member1);
        Member member = memberMapper.queryByIdForLock(id);
        member.setUserName(name);
        memberMapper.updateByIdError(member);
        return member;
    }
}
