package com.example.okhttp_design.chain2.supervisor;

import com.example.okhttp_design.chain2.LeaveRequest;
import com.example.okhttp_design.chain2.Response;
import com.example.okhttp_design.chain2.SupervisorInter;

import java.util.List;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class SupervisorChain implements SupervisorInter.Chain {
    private List<AbsSupervisor> supervisors;
    private int index;
    private LeaveRequest request;

    public SupervisorChain(List<AbsSupervisor> supervisors, int index, LeaveRequest request) {
        this.supervisors = supervisors;
        this.index = index;
        this.request = request;
        System.out.println("index:" + index);
    }

    @Override
    public LeaveRequest request() {
        return request;
    }

    @Override
    public Response proceed(LeaveRequest request) {
        if (index > supervisors.size()) {
            throw new RuntimeException("index error");
        }
        SupervisorChain nextSupervisorChain = new SupervisorChain(supervisors, index + 1, request);
        AbsSupervisor absSupervisor = supervisors.get(index);
        System.out.println(absSupervisor);
        Response response = absSupervisor.intercept(nextSupervisorChain);
        return response;
    }
}
