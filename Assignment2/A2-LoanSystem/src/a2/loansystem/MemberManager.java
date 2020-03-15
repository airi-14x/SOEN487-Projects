/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Member;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
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
public interface MemberManager {
    
    @WebMethod public ConcurrentHashMap<Integer, Member> getMembersMap();
    @WebMethod public int memberMapKey();
    
    @WebMethod public String getMembers();
    @WebMethod public String getMemberInfo(int memberID);
    @WebMethod public void addMember(String memberName, String memberContact) throws LoanException;
    @WebMethod public void updateMember(int memberID, String memberName, String memberContact)  throws LoanException;
    @WebMethod public void deleteMember(int memberID) throws LoanException;
}
