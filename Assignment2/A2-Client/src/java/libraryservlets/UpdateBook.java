/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import libraryclient.LibraryClient;

/**
 *
 * @author jasminelatendresse
 */
public class UpdateBook extends HttpServlet {

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        LibraryClient client = new LibraryClient();
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String isbn = request.getParameter("isbn");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String callNumber = request.getParameter("callNumber");
            
            PrintWriter out = response.getWriter();
            
            Response res = client.updateBookBasic(id, title, description, isbn, author, publisher, callNumber);
            
            out.write(res.toString());
            if (res.getStatus() == 200) {
                request.setAttribute("resMessage", res);
                request.setAttribute("successMessage", "Book successfully updated.");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "An error occurred. Book could not be updated.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (IOException | NumberFormatException | ServletException | ClientErrorException e) {
            request.setAttribute("message", "An error occurred. Book could not be updated.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
        doPut(request, response);
    }

  
}
