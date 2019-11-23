/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
@WebServlet(name = "CtrlLogin", urlPatterns = {"/Login", "/Iniciar","/Principal, pito"})
public class CtrlLogin extends HttpServlet {

    private String url = "";
    
    @EJB
    private UsuarioFacade usrf;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        url = request.getServletPath();
        System.out.println("CtrlLogin"+url);
        if (url.equals("/Login")) {
            rd = getServletContext().getRequestDispatcher("/views/Login.jsp");
            rd.forward(request, response);
        }
        else if(url.equals("/Principal")){
            rd = getServletContext().getRequestDispatcher("/views/Principal.jsp");
            rd.forward(request, response);
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
        url = request.getServletPath();
        System.out.println("envias"+url);
        if(url.equals("/Principal")){
            Usuario usr;
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            response.setContentType("text/html;charset=UTF-8");
            try{
                usr = usrf.find(usuario);
                if(usr!=null){
                    if(usr.getPassword().equals(password)){
                        response.getWriter().print("1");
                        response.sendRedirect("Usuarios");
                    }else{
                        response.getWriter().print("Contraseña incrrecta");
                    }
                }else{
                    response.getWriter().print("No permitido");
                }
            }catch(EJBException ex){
                if(ex.getCause()instanceof NoResultException){
                    response.getWriter().print("Usuario no encontrado");
                }else if(ex.getCause() instanceof NonUniqueResultException){
                    response.getWriter().print("Problema porque hubo muchos resultados");
                }
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE,null,ex);
            }catch(Exception ex){
                response.getWriter().print(ex);
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        

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
