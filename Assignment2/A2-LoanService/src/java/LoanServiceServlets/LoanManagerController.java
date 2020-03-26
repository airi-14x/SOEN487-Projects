/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanServiceServlets;


import a2.loanservice.loanmanager.client.LoanServiceSOAPFault_Exception;

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

/**
 *
 * @author Airi
 */
@WebServlet(name = "LoanManagerController", urlPatterns = {"/LoanManagerController"})
public class LoanManagerController extends HttpServlet {

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
            out.println("<title>Servlet LoanManagerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoanManagerController at " + request.getContextPath() + "</h1>");
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
        switch (request.getParameter("loans")) {
            case "deleteBookLoan":
                if (!request.getParameter("deleteLoanID").equals("")) {
                    String loanID = request.getParameter("deleteLoanID");
                    int deleteIDValue;
                    try {
                        deleteIDValue = Integer.parseInt(loanID);
                        request.setAttribute("message", "Delete Loan with: " + loanID);
                        request.setAttribute("results", deleteBookLoan(deleteIDValue));
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                    } catch (LoanServiceSOAPFault_Exception ex) {
                        Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", "Unable to delete loan");
                        request.setAttribute("results", ex.getFaultInfo().getMessage());
                    }

                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                }
                break;
            case "listLoan1":
                if (!request.getParameter("listLoanID").equals("")) {
                    String memberID = request.getParameter("listLoanID");
                    int memberIDValue;
                    try {
                        memberIDValue = Integer.parseInt(memberID);
                        request.setAttribute("message", "Get Loan associated with Member ID: " + memberID);
                        request.setAttribute("results", listLoanID(memberIDValue));
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                    } catch (LoanServiceSOAPFault_Exception ex) {
                        Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", "Unable to get member associated with Member ID: " + memberID);
                        request.setAttribute("results", ex.getFaultInfo().getMessage());
                    }

                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                }
                break;
            case "listLoan2":
                if (!request.getParameter("listLoanBook").equals("")) {
                    String bookTitle = request.getParameter("listLoanBook");
                    try {
                        request.setAttribute("message", "Get loan associated with Book Title: " + bookTitle);
                        request.setAttribute("results", listLoan(bookTitle));
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                    } catch (LoanServiceSOAPFault_Exception ex) {
                        Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", "Unable to get loans associated with Book Title: " + bookTitle);
                        request.setAttribute("results", ex.getFaultInfo().getMessage());
                    }

                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                }
                break;
            default:
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/loanResults.jsp");
        rd.forward(request, response);
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
        switch (request.getParameter("loans")) {
            case "borrowBook":
                int memberIDValue;
                try {
                    String callNumber = request.getParameter("addCallNumber");
                    String borrowDate = request.getParameter("addBorrowDate");
                    String returnDate = request.getParameter("addReturnDate");
                    if (!request.getParameter("addBorrowMemberID").equals("")) {
                        String memberID = request.getParameter("addBorrowMemberID");
                        memberIDValue = Integer.parseInt(memberID);
                        
                        request.setAttribute("message", "Borrowed a book!");
                        request.setAttribute("results", borrowBook(callNumber, memberIDValue, borrowDate, returnDate));
                    } else {
                        request.setAttribute("message", "Error: Empty Input!");
                        request.setAttribute("results", "");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Error: Invalid Input!");
                } catch (LoanServiceSOAPFault_Exception ex) {
                    Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "Unable to borrow a book!");
                    request.setAttribute("results", ex.getFaultInfo().getMessage());
                }
                break;
            case "editBookLoan":
                int loanIDValue;
                if (!request.getParameter("editLoanID").equals("")) {
                    String loanID = request.getParameter("editLoanID");
                    String borrowDate = request.getParameter("editBorrowDate");
                    String returnDate = request.getParameter("editReturnDate");
                    try {
                        loanIDValue = Integer.parseInt(loanID);
                        request.setAttribute("message", "Edit Loan with: " + loanIDValue);
                        request.setAttribute("results", editBookLoan(loanIDValue, borrowDate, returnDate));
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                    } catch (LoanServiceSOAPFault_Exception ex) {
                        Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", "Unable to edit loan");
                        request.setAttribute("results", ex.getFaultInfo().getMessage());
                    }

                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                }
                break;
            case "returnBookLoan":
                if (!request.getParameter("returnLoanID").equals("")) {
                    String loanID = request.getParameter("returnLoanID");
                    try {
                        loanIDValue = Integer.parseInt(loanID);
                        request.setAttribute("message", "Return Book with LoanID: " + loanID);
                        request.setAttribute("results", returnBookLoan(loanIDValue));
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                    } catch (LoanServiceSOAPFault_Exception ex) {
                        Logger.getLogger(LoanManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", "Unable to return Book with LoanID: " + loanID);
                        request.setAttribute("results", ex.getFaultInfo().getMessage());
                    }
                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                }
                break;
            default:
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/loanResults.jsp");
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


    private static String listLoan(java.lang.String arg0) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.listLoan(arg0);
    }

    private static String listLoanID(int arg0) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.listLoanID(arg0);
    }

    private static String borrowBook(java.lang.String arg0, int arg1, java.lang.String arg2, java.lang.String arg3) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.borrowBook(arg0, arg1, arg2, arg3);
    }

    private static String deleteBookLoan(int arg0) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.deleteBookLoan(arg0);
    }

    private static String editBookLoan(int arg0, java.lang.String arg1, java.lang.String arg2) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.editBookLoan(arg0, arg1, arg2);
    }

    private static String returnBookLoan(int arg0) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service service = new a2.loanservice.loanmanager.client.LoanServiceLoanManagerImpl1Service();
        a2.loanservice.loanmanager.client.LoanServiceLoanManager1 port = service.getLoanServiceLoanManagerImpl1Port();
        return port.returnBookLoan(arg0);
    }

}
