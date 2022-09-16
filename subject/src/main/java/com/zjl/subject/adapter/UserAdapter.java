package com.zjl.subject.adapter;

import com.zjl.dto.user.dto.AddUserDto;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.springframework.http.ResponseEntity;

@LocalTCC
public interface UserAdapter {

//    public ResponseEntity<GetUserDto> getUserById(Integer userId);

    @TwoPhaseBusinessAction(name = "addUser", commitMethod = "commit",
            rollbackMethod = "cancel")
    public ResponseEntity<Integer> addUser(BusinessActionContext actionContext,@BusinessActionContextParameter(paramName = "addUserDto") AddUserDto addUserDto);

    public boolean commit(BusinessActionContext context);

    public boolean cancel(BusinessActionContext context);

}
