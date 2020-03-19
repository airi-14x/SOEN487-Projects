/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loancore;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Airi
 */
@XmlRootElement
public class Member implements Serializable {
    private int memberID;
    private String memberName;
    private String memberContact;

    public Member(){
        
    }
    
    public Member(String memberName, String memberContact) {
        this.memberName = memberName;
        this.memberContact = memberContact;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    @Override
    public String toString() {
        return "Member{" + "memberID=" + memberID + ", memberName=" + memberName + ", memberContact=" + memberContact + '}';
    }
    
    
}