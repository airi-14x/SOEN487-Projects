/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import javax.xml.soap.SOAPException;
import javax.xml.ws.WebFault;

/**
 *
 * @author Airi
 */
@WebFault(name="LoanServiceSOAPFault")
public class LoanServiceSOAPFault extends SOAPException {

    /**
     * Constructs an instance of <code>SOAPFault</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoanServiceSOAPFault(String msg) {
        super(msg);
    }
    
    public LoanServiceSOAPFault(String msg, Throwable cause) {
        super(msg, cause);
    }
}
