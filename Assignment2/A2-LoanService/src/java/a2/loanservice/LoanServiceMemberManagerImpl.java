/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.loansystem.LoanException;
import a2.loansystem.MemberManagerImpl;
import java.io.IOException;
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
    public String addMember(String memberName, String memberContact) throws LoanException {
        memberManager.addMember(memberName, memberContact);
        return memberManager.getMembers();
    }

    @Override
    public String updateMember(int memberID, String memberName, String memberContact) throws LoanException{
        memberManager.updateMember(memberID, memberName, memberContact);
        return memberManager.getMembers();
    }

    @Override
    public String deleteMember(int memberID) throws LoanException{
        memberManager.deleteMember(memberID);
        return memberManager.getMembers();
    }
}