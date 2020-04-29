package com.example.okhttp_design.chain;

import com.example.okhttp_design.chain.supervisor.FirstSupervisor;
import com.example.okhttp_design.chain.supervisor.SecondSupervisor;
import com.example.okhttp_design.chain.supervisor.ThirdSupervisor;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class Main {

    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setDay(40);
        leaveRequest.setName("张三");

        ThirdSupervisor thirdSupervisor = new ThirdSupervisor("杨总");
        SecondSupervisor secondSupervisor = new SecondSupervisor("胡总");
        FirstSupervisor firstSupervisor = new FirstSupervisor("蔡总");
        thirdSupervisor.setNextSupervisor(secondSupervisor);
        secondSupervisor.setNextSupervisor(firstSupervisor);

        thirdSupervisor.handleRequest(leaveRequest);
    }

}
