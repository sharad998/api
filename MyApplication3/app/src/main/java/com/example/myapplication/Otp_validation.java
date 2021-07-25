package com.example.myapplication;

public class Otp_validation {
    String errorCode;
    String errorDes;
    String requestID;

    String mobileNumber;
    String otpSource;
    String otp;

    public Otp_validation(String errorCode, String errorDes, String requestID) {
        this.errorCode = errorCode;
        this.errorDes = errorDes;
        this.requestID = requestID;
    }

    public Otp_validation(String requestID, String mobileNumber, String otpSource, String otp) {
        this.requestID = requestID;
        this.mobileNumber = mobileNumber;
        this.otpSource = otpSource;
        this.otp = otp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDes() {
        return errorDes;
    }

    public void setErrorDes(String errorDes) {
        this.errorDes = errorDes;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtpSource() {
        return otpSource;
    }

    public void setOtpSource(String otpSource) {
        this.otpSource = otpSource;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
