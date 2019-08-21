
package com.redcarpet.payroll.util;

/**
 *
 * @author      S.A. Mateen
 * @email       mateen.syed@rcssgroup.biz
 * @company     RedCarpet Software Solutions
 */
public class PayrollRuntimeException extends RuntimeException {

    public PayrollRuntimeException() {
    }

    public PayrollRuntimeException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }
    

}
