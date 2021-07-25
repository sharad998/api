package com.example.myapplication;

public class request {
    int errorCode;
    String errorMsg;
    int mobile_No_c;
    String requestID;

    int mobileNumber;
    String otpSource;
    String module;



    public request(int errorCode, String errorMsg, int mobile_No_c, String requestID){
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
        this.mobile_No_c=mobile_No_c;
        this.requestID=requestID;

    }

    public request(int mobileNumber, String otpSource, String module) {
        this.mobileNumber = mobileNumber;
        this.otpSource = otpSource;
        this.module = module;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getMobile_No_c() {
        return mobile_No_c;
    }

    public void setMobile_No_c(int mobile_No_c) {
        this.mobile_No_c = mobile_No_c;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
