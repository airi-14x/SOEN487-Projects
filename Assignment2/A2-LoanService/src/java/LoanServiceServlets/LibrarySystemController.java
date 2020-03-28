/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoanServiceServlets;

import a2.loanservice.librarymanager.client.LoanServiceSOAPFault_Exception;
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
@WebServlet(name = "LibrarySystemController", urlPatterns = {"/LibrarySystemController"})
public class LibrarySystemController extends HttpServlet {

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
            out.println("<title>Servlet LibrarySystemController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LibrarySystemController at " + request.getContextPath() + "</h1>");
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
        //Library library;
        try {
            //library = Library.getInstance();
            switch (request.getParameter("library")) {
                case "displayAll":
                    request.setAttribute("message", "Displaying all books!");
                    request.setAttribute("results", displayBooks());
                    request.setAttribute("callNumberResults", displayCallNumberMap());
                    break;
                case "displayBook":
                    if (!request.getParameter("viewBookID").equals("")) {
                        String bookID = request.getParameter("viewBookID");
                        int bookIDValue;
                        try {
                            bookIDValue = Integer.parseInt(bookID);
                            request.setAttribute("message", "Displaying book with ID:" + bookID);
                            request.setAttribute("results", getBook(bookIDValue));
                            request.setAttribute("callNumberResults", "");
                        } catch (NumberFormatException e) {
                            request.setAttribute("results", "Error: Invalid Input!");
                        }

                    } else {
                        request.setAttribute("results", "Error: Empty Input!");
                    }
                    break;
                case "deleteBook":
                    if (!request.getParameter("deleteBookID").equals("")) {
                        String bookID = request.getParameter("deleteBookID");
                        int bookIDValue;
                        try {
                            bookIDValue = Integer.parseInt(bookID);
                            request.setAttribute("message", "Delete book with ID:" + bookID);

                            request.setAttribute("results", removeBook(bookIDValue));
                            request.setAttribute("callNumberResults", displayCallNumberMap());
                        } catch (NumberFormatException e) {
                            request.setAttribute("results", "Error: Invalid Input!");
                        }

                    } else {
                        request.setAttribute("results", "Error: Empty Input!");
                    }
                    break;
                default:
                    break;
            }
        } catch (LoanServiceSOAPFault_Exception ex) {
            Logger.getLogger(LibrarySystemController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", ex.getFaultInfo().getMessage());
            request.setAttribute("results", "");
            request.setAttribute("callNumberResults", "");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/libraryResults.jsp");
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
        //Library library;
        try {
            //library = Library.getInstance();
            String title = request.getParameter("title");
            String isbn = request.getParameter("isbn");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String callNumber = request.getParameter("callNumber");

            if (request.getParameter("library").equals("addBook")) {

                request.setAttribute("message", "Added Book to Library");
                request.setAttribute("results", addBook(title, description, isbn, author, publisher, callNumber));
                request.setAttribute("callNumberResults", displayCallNumberMap());
            } else if (request.getParameter("library").equals("updateBook")) {
                if (!request.getParameter("updateBookID").equals("")) {
                    String updateBookID = request.getParameter("updateBookID");
                    int bookIDValue;
                    try {
                        bookIDValue = Integer.parseInt(updateBookID);
                        ;
                        request.setAttribute("message", "Updated book!");
                        request.setAttribute("results", updateBook(bookIDValue, title, description, isbn, author, publisher, callNumber));
                        request.setAttribute("callNumberResults", displayCallNumberMap());
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Error: Invalid Input!");
                        request.setAttribute("results", "");
                        request.setAttribute("callNumberResults", "");
                    }
                } else {
                    request.setAttribute("message", "Error: Empty Input!");
                    request.setAttribute("results", "");
                    request.setAttribute("callNumberResults", "");
                }
            }
        } catch (LoanServiceSOAPFault_Exception ex) {
            Logger.getLogger(LibrarySystemController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", ex.getFaultInfo().getMessage());
            request.setAttribute("results", "");
            request.setAttribute("callNumberResults", "");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/libraryResults.jsp");
        rd.forward(request, response);
        //processRequest(request, response);
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

    private static String addBook(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.addBook(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    private static String displayBooks() {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.displayBooks();
    }

    private static String getBook(int arg0) {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.getBook(arg0);
    }

    private static String removeBook(int arg0) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.removeBook(arg0);
    }

    private static String updateBook(int arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6) throws LoanServiceSOAPFault_Exception {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.updateBook(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    private static String displayCallNumberMap() {
        a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService service = new a2.loanservice.librarymanager.client.LoanServiceLibraryManagerImplService();
        a2.loanservice.librarymanager.client.LoanServiceLibraryManager port = service.getLoanServiceLibraryManagerImplPort();
        return port.displayCallNumberMap();
    }

}
