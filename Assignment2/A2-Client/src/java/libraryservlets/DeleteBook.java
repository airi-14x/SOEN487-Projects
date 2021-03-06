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
import javax.ws.rs.core.Response;
import libraryclient.LibraryClient;

/**
 *
 * @author jasminelatendresse
 */
public class DeleteBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        response.setContentType("text/html");
        LibraryClient client = new LibraryClient();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Response res = client.deleteBook(id);

            if (res.getStatus() == 200) {
                request.setAttribute("resMessage", res);
                request.setAttribute("successMessage", "Book successfully deleted.");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "An error occurred. Book could not be deleted.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "An error occurred. Book could not be deleted.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
