package core.domain.service;

import core.domain.model.Member;

import java.util.List;

/**
 * Created by XR on 2016/12/9.
 */
public interface IMember {
    List<Member> queryList();

    void test(Long id, String name);

    Member update(Long id,String name);
}
