package application.mapper;

import application.model.Member;

import java.util.List;

;

/**
 * Created by XR on 2016/8/22.
 */
public interface MemberDao {
    List<Member> querylist();
}
