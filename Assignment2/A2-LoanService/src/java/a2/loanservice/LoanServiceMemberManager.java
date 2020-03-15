/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.loansystem.LoanException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Airi
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface LoanServiceMemberManager {
    @WebMethod public String getMembers();
    @WebMethod public String getMemberInfo(int memberID);
    @WebMethod public String addMember(String memberName, String memberContact) throws LoanException;
    @WebMethod public String updateMember(int memberID, String memberName, String memberContact)  throws LoanException;
    @WebMethod public String deleteMember(int memberID) throws LoanException;
}
