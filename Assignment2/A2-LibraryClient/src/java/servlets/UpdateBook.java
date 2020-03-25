/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
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
        
    }

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
        
        Map<String, String> params = getParameterMap(request);
        LibraryClient client = new LibraryClient();
        
        try {
            int id = Integer.parseInt(params.get("id"));
            String title = params.get("title");
            String description = params.get("description");
            String isbn = params.get("isbn");
            String author = params.get("author");
            String publisher = params.get("publisher");
            String callNumber = params.get("callNumber");
            
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
        processRequest(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private static Map<String, String> getParameterMap(HttpServletRequest request) {
    BufferedReader br = null;
    Map<String, String> dataMap = null;

    try {

        InputStreamReader reader = new InputStreamReader(
                request.getInputStream());
        br = new BufferedReader(reader);

        String data = br.readLine();

        dataMap = Splitter.on('&')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                        .limit(2)
                        .trimResults())
                .split(data);

        return dataMap;
    } catch (IOException ex) {
        //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    return dataMap;
}

}
