/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.Usuario;
import model.sessions.UsuarioFacade;

/**
 *
 * @author Pablo Tenorio
 */
@WebServlet(name = "CtrlUsuarios", urlPatterns = {"/Usuarios"})
public class CtrlUsuarios extends HttpServlet {

    @EJB
    private UsuarioFacade uf;
    String url;
   
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
        RequestDispatcher rd;
        url = request.getServletPath();
        System.out.println("Ctrlusu "+ url);
        switch(url){
            case "/Usuarios":
                List<Usuario> usuarios = uf.findAll();
                request.setAttribute("datos",usuarios);
                rd = getServletContext().getRequestDispatcher("/WEB-INF/views/Usuarios.jsp");
                rd.forward(request,response);
                break;
            
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

}



