package com.example.okhttp_design.chain.supervisor;

import com.example.okhttp_design.chain.LeaveRequest;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class SecondSupervisor extends AbsSupervisor {

    private static final String TAG = "SecondSupervisor";

    public SecondSupervisor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDay() < 10) {

            System.out.println(name + " handleRequest: 同意请假");

        } else {
            if (nextSupervisor != null) {
                nextSupervisor.handleRequest(request);
            } else {
                System.out.println(name + "handleRequest: 拒绝请假");
            }
        }
    }
}
