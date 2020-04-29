package com.example.okhttp_design.chain.supervisor;

import com.example.okhttp_design.chain.LeaveRequest;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public abstract class AbsSupervisor {

    protected AbsSupervisor(String name) {
        this.name = name;
    }

    /**
     * 领导名称
     */
    protected String name;

    protected AbsSupervisor nextSupervisor;

    public abstract void handleRequest(LeaveRequest request);

    public void setNextSupervisor(AbsSupervisor supervisor) {
        this.nextSupervisor = supervisor;
    }
}
