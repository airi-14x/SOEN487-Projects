/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Member;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author Airi
 */

public interface MemberManager {
    
    public ConcurrentHashMap<Integer, Member> getMembersMap();
    public int memberMapKey();
    
    public String getMembers();
    public String getMemberInfo(int memberID);
    public void addMember(String memberName, String memberContact) throws LoanException;
    public void updateMember(int memberID, String memberName, String memberContact)  throws LoanException;
    public void deleteMember(int memberID) throws LoanException;
}
