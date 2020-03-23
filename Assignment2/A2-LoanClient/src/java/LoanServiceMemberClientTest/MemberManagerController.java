/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanServiceMemberClientTest;

import a2.loanservice.client.LoanException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Airi
 */
@WebServlet(name = "MemberManagerController", urlPatterns = {"/MemberManagerController"})
public class MemberManagerController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MemberManagerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MemberManagerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("members").equals("displayAll")) {
            request.setAttribute("results", getMembers());
            
        } else if (request.getParameter("members").equals("displayMember")) {
            if (!request.getParameter("viewMemberID").equals("")) {
                String memberID = request.getParameter("viewMemberID");
                int memberIDValue;
                try {
                    memberIDValue = Integer.parseInt(memberID);

                    request.setAttribute("results", getMemberInfo(memberIDValue));
                } catch (NumberFormatException e) {
                    request.setAttribute("results", "Error: Invalid Input!");
                }

            } else {
                request.setAttribute("results", "Error: Empty Input!");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
        rd.forward(request, response);
        //processRequest(request, response);
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("members").equals("addMember")) {
            try {
                String memberName = request.getParameter("addMemberName");
                String memberContact = request.getParameter("addMemberContact");
                addMember(memberName, memberContact);
                request.setAttribute("results", "Add a new Member!");
            } catch (a2.loanservice.client.LoanException_Exception ex) {
                Logger.getLogger(MemberManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (request.getParameter("members").equals("updateMember"))
        {
           try {
                String memberName = request.getParameter("updateMemberName");
                String memberContact = request.getParameter("updateMemberContact");
                int memberIDValue;
                if (!request.getParameter("updateMemberID").equals(""))
                {
                    String memberID = request.getParameter("updateMemberID");
                    memberIDValue = Integer.parseInt(memberID);
                    updateMember(memberIDValue,memberName, memberContact);
                }
              
                request.setAttribute("results", "Update Member!");
            } catch (a2.loanservice.client.LoanException_Exception ex) {
                Logger.getLogger(MemberManagerController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    private static String getMembers() {
        a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
        a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
        return port.getMembers();
    }

    private static String getMemberInfo(int arg0) {
        a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
        a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
        return port.getMemberInfo(arg0);
    }

    private static void addMember(java.lang.String arg0, java.lang.String arg1) throws a2.loanservice.client.LoanException_Exception {
        a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
        a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
        port.addMember(arg0, arg1);
    }

    private static void updateMember(int arg0, java.lang.String arg1, java.lang.String arg2) throws a2.loanservice.client.LoanException_Exception {
        a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
        a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
        port.updateMember(arg0, arg1, arg2);
    }

    private static void deleteMember(int arg0) throws  a2.loanservice.client.LoanException_Exception {
        a2.loanservice.client.LoanServiceMemberManagerImplService service = new a2.loanservice.client.LoanServiceMemberManagerImplService();
        a2.loanservice.client.LoanServiceMemberManager port = service.getLoanServiceMemberManagerImplPort();
        port.deleteMember(arg0);
    }

}
