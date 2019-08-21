package com.rcss.humanresource.util;

import org.openbravo.erpCommon.utility.OBError;

public class MessageBody {
    String message = "Successfully Processed";
    String errorMessage = "Error Occured";
    public OBError getSuccessMessage(String successMessage){
        message = successMessage;
        final OBError obError = new OBError();
        obError.setType("Success");
        obError.setTitle("Success");
        obError.setMessage(message);
        return obError;
    }
    public OBError getSuccessMessage(){
        final OBError obError = new OBError();
        obError.setType("Success");
        obError.setTitle("Success");
        obError.setMessage(message);
        return obError;
    }
    public OBError getErrorMessage(){
        message = errorMessage;
        final OBError obError = new OBError();
        obError.setType("Error");
        obError.setTitle("Error");
        obError.setMessage(message);
        return obError;
    }
    public OBError getErrorMessage(String errorMessage){
        message = errorMessage;
        final OBError obError = new OBError();
        obError.setType("Error");
        obError.setTitle("Error");
        obError.setMessage(message);
        return obError;
    }
}
