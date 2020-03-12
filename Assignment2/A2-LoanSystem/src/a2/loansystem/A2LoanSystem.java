/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import java.io.IOException;

/**
 *
 * @author Airi
 */
public class A2LoanSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LoanException, IOException {
       
       MemberManager members = new MemberManager();
       members.addMember("Airi", "fkwjf@gmail.com");
       members.addMember("Ali", "wssjf@gmail.com");
       System.out.println(members.getMembersMap());
       
       System.out.println(members.getMembers());
       System.out.println(members.getMemberInfo(4));
       
       members.updateMember(2, "Alice", "sfdd");
       System.out.println(members.getMembersMap());

       members.deleteMember(2);
       System.out.println(members.getMembersMap());

       // Database Version //
       //LoanManager loans = LoanManager.getInstance();
       //loans.dropLoanTable();
       //loans.createLoanTable();
       //loans.cleanup();
       
       //MemberManager members = MemberManager.getInstance();
       //members.dropMemberTable();
       //members.createMemberTable();
       //members.cleanup();
    }
    
    
}
