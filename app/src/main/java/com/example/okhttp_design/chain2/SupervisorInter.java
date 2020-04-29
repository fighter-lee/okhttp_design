package com.example.okhttp_design.chain2;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public interface SupervisorInter {
    Response intercept(Chain chain);

    interface Chain {
        LeaveRequest request();

        Response proceed(LeaveRequest request);
    }

}
