package com.example.okhttp_design.chain2.supervisor;


import com.example.okhttp_design.chain2.LeaveRequest;
import com.example.okhttp_design.chain2.Response;

/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class ThirdSupervisor extends AbsSupervisor {

    private static final String TAG = "ThirdSupervisor";

    public ThirdSupervisor(String name) {
        super(name);
    }

    @Override
    public Response intercept(Chain chain) {
        System.out.println("三级主管开始处理");
        LeaveRequest request = chain.request();

        if (request.getDay() < 3) {

            System.out.println(name + " handleRequest: 同意请假");
            Response response = new Response();
            response.setApprove(true);
            response.setRemake("3天小意思，批假");
            response.setRequest(request);
            return response;
        } else {
            Response response = chain.proceed(request);
            //            if (response.isApprove()) {
            //                if (request.getName().equals("小李")) {
            //                    response.setApprove(false);
            //                    response.setRemake("就是不让你请，能咋地");
            //                }
            //            }
            return response;
        }
    }
}
