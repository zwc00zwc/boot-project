package application.service;

import application.mapper.MemberDao;
import application.model.Member;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XR on 2016/12/7.
 */
@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;
    public List<Member> queryList() {
        return memberDao.querylist();
    }
}
