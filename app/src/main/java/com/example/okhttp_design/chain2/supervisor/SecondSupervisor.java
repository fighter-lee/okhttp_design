package com.example.okhttp_design.chain2.supervisor;

import com.example.okhttp_design.chain2.LeaveRequest;
import com.example.okhttp_design.chain2.Response;

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
    public Response intercept(Chain chain) {
        System.out.println("二级主管开始处理");
        LeaveRequest request = chain.request();

        if (request.getDay() < 10) {

            System.out.println(name + " handleRequest: 同意请假");
            Response response = new Response();
            response.setApprove(true);
            response.setRemake("小伙子不错，批假");
            response.setRequest(request);
            return response;
        } else {
            Response response = chain.proceed(request);
            return response;
        }
    }
}
