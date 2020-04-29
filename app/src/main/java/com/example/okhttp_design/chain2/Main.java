package com.example.okhttp_design.chain2;

import com.example.okhttp_design.chain2.supervisor.AbsSupervisor;
import com.example.okhttp_design.chain2.supervisor.FirstSupervisor;
import com.example.okhttp_design.chain2.supervisor.SecondSupervisor;
import com.example.okhttp_design.chain2.supervisor.SupervisorChain;
import com.example.okhttp_design.chain2.supervisor.ThirdSupervisor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class Main {

    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setDay(8);
        leaveRequest.setName("张三");

        List<AbsSupervisor> absSupervisors = new ArrayList<>();
        ThirdSupervisor thirdSupervisor = new ThirdSupervisor("杨总");
        SecondSupervisor secondSupervisor = new SecondSupervisor("胡总");
        FirstSupervisor firstSupervisor = new FirstSupervisor("蔡总");
        absSupervisors.add(thirdSupervisor);
        absSupervisors.add(secondSupervisor);
        absSupervisors.add(firstSupervisor);
        SupervisorChain supervisorChain = new SupervisorChain(absSupervisors, 0, leaveRequest);
        Response proceed = supervisorChain.proceed(leaveRequest);

        System.out.println(proceed.toString());
    }

}
