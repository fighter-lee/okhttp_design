package com.example.okhttp_design.chain2.supervisor;

import com.example.okhttp_design.chain2.SupervisorInter;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public abstract class AbsSupervisor implements SupervisorInter {

    protected AbsSupervisor(String name) {
        this.name = name;
    }

    /**
     * 领导名称
     */
    protected String name;

}
