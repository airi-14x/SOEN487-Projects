/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jasminelatendresse
 */
public class Login extends HttpServlet {

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
        
        String requestUsername = request.getParameter("username");
        String requestPassword = request.getParameter("password");
        PrintWriter out = response.getWriter();
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(request.getRealPath("/WEB-INF/credentials.json")));
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject user = (JSONObject) object;

        String username = (String) user.get("username");
        String password = (String) user.get("password");

        if (requestUsername.equals(username) && requestPassword.equals(password)) {
            HttpSession session=request.getSession();  
            session.setAttribute("username", username);  
            request.getRequestDispatcher("index.jsp").include(request, response);

        } else {
            out.print("Could not login. Error in username or password.");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
        out.close();

    }
}
