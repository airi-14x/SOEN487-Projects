/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Member;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class MemberManagerImpl implements MemberManager {

    private static ConcurrentHashMap<Integer, Member> members = new ConcurrentHashMap<Integer, Member>();
    private static AtomicInteger memberMapKey = new AtomicInteger(); // Need to create memberID --> UNIQUE
    private static MemberManagerImpl memberManagerConnectionInstance;

    public MemberManagerImpl() {
        System.out.println("Created an instance of MemberSystem()");
    }

    @Override
    public ConcurrentHashMap<Integer, Member> getMembersMap() {
        return members;
    }

    // For setting memberID manually //
    @Override
    public int memberMapKey() {
        return memberMapKey.get();
    }

    // SINGLETON    
    public static MemberManagerImpl getInstance() throws LoanException, IOException {
        if (memberManagerConnectionInstance == null) {
            memberManagerConnectionInstance = new MemberManagerImpl();
            System.out.println("MemberManager - Instance has been created!");
        }
        return memberManagerConnectionInstance;
    }

    // GET 
    // List members
    @Override
    public synchronized String getMembers() {
        String currentMembers = members.toString();
        if (members.isEmpty()) {
            return ("No members to display");
        }
        return currentMembers;

    }

    // GET
    @Override
    public synchronized String getMemberInfo(int memberID) {
        // Return: memberID, String name, String contact
        if (!members.containsKey(memberID)) {
            return "Member doesn't exist";
        }
        Member member = members.get(memberID);
        String memberInfo = member.toString();
        return memberInfo;

    }

    // POST
    @Override
    public synchronized void addMember(String memberName, String memberContact) throws LoanException {
        int memberID = memberMapKey.incrementAndGet();
        Member member = new Member(memberName, memberContact);
        member.setMemberID(memberID);
        members.put(memberID, member);

        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Error in adding a member!");
        }
    }

    // UPDATE
    @Override
    public synchronized void updateMember(int memberID, String memberName, String memberContact) throws LoanException {

        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Member cannot be found. Cannot update!");
        } else {
            Member member = new Member(memberName, memberContact);
            member.setMemberID(memberID);
            members.put(memberID, member);
            if (!members.containsKey(memberID)) {
                throw new LoanException("Member Manager - Was unable to update the member!");
            }
        }
    }

    // DELETE
    @Override
    public synchronized void deleteMember(int memberID) throws LoanException {
        if (!members.containsKey(memberID)) {
            throw new LoanException("Member Manager - Member cannot be found. Cannot delete!");
        } else {
            members.remove(memberID);
        }
    }
}
