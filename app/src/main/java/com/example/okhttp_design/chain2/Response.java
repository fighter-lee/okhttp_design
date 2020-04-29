package com.example.okhttp_design.chain2;


/**
 * @author fighter_lee
 * @date 2020/4/29
 */
public class Response {

    public LeaveRequest getRequest() {
        return request;
    }

    public void setRequest(LeaveRequest request) {
        this.request = request;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    private LeaveRequest request;

    private boolean approve;

    private String remake;

    @Override
    public String toString() {
        return "Response{" +
                "request=" + request +
                ", approve=" + approve +
                ", remake='" + remake + '\'' +
                '}';
    }
}
