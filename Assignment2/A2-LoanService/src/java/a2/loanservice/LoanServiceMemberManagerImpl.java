/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.loansystem.LoanException;
import a2.loansystem.MemberManagerImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

// http://localhost:8080/A2-LoanService/LoanServiceMemberManagerImpl?wsdl
@WebService(endpointInterface="a2.loanservice.LoanServiceMemberManager")
public class LoanServiceMemberManagerImpl implements LoanServiceMemberManager {
    private static MemberManagerImpl memberManager;
    
    public LoanServiceMemberManagerImpl() throws LoanException, IOException {
        System.out.println("Created an instance of LoanService - MemberSystem");
        memberManager = memberManager.getInstance();
    }
    
    @Override
    public String getMembers() {
        return memberManager.getMembers();
    }

    @Override
    public String getMemberInfo(int memberID) {
        return memberManager.getMemberInfo(memberID);
    }

    @Override
    public String addMember(String memberName, String memberContact) throws LoanServiceSOAPFault {
        try {
            memberManager.addMember(memberName, memberContact);
        } catch (LoanException ex) {
            Logger.getLogger(LoanServiceMemberManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLoanErrorMessage());
        }
        return memberManager.getMembers();
    }

    @Override
    public String updateMember(int memberID, String memberName, String memberContact) throws LoanServiceSOAPFault{
        try {
            memberManager.updateMember(memberID, memberName, memberContact);
        } catch (LoanException ex) {
            Logger.getLogger(LoanServiceMemberManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLoanErrorMessage());
        }
        return memberManager.getMembers();
    }

    @Override
    public String deleteMember(int memberID) throws LoanServiceSOAPFault{
        try {
            memberManager.deleteMember(memberID);
        } catch (LoanException ex) {
            Logger.getLogger(LoanServiceMemberManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLoanErrorMessage());
        }
        return memberManager.getMembers();
    }
}