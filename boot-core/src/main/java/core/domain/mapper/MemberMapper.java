package core.domain.mapper;

import java.util.List;
import core.domain.model.Member;
import org.apache.ibatis.annotations.Param;

/**
 * Created by XR on 2016/12/8.
 */
public interface MemberMapper {
    List<Member> querylist();

    Member queryByIdForLock(Long id);

    int updateById(@Param("member") Member member);

    int updateByIdError(@Param("member") Member member);

    int insert(@Param("member") Member member);
}
