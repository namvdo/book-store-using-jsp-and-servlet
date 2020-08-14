/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CartBean;
import bean.LineItemBean;
import bean.TransactionBean;
import db.LineItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.TransactionDAO;

/**
 *
 * @author Nam
 */
public class SubmitOrderController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CartBean cart = (CartBean) session.getAttribute("cart");
            String auth = (String) session.getAttribute("user");
            System.out.println(cart);
            List<LineItemBean> items = cart.getItems();
            boolean enoughInventory = true;
            for(LineItemBean item: items) {
                if (!item.isEnougInventory()) {
                    enoughInventory = false;
                }
            }
            
            if (!enoughInventory) {
                out.println("Sorry, we haven't had enough quantity for your order, check it again");
                request.getRequestDispatcher("/view/notEnoughQuantity.jsp").forward(request, response);
                return;
            }
            
            // update new book quantity for the book bean object and also database
            
            for(LineItemBean item: items) {
                item.updateBookQuantity();
            }
            
            
            // create a transaction and commit to the database;
            
            TransactionBean transaction = new TransactionBean();
            double totalPriceAllItems = getTotalPrice(items);
            transaction.setTotalPrice(totalPriceAllItems);
            transaction.setUserName(auth);
            int transactionId = new TransactionDAO().insertTransactionAndGetTransactionId(transaction);
            items.forEach(item -> item.setTransactionId(transactionId));
            new LineItemDAO().insertLineItems(items);
            cart.removeAllItems();
            request.setAttribute("confirmId", transactionId);
		          System.out.println("Something here: " + request.getContextPath());
		RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/view/orderConfirmation.jsp");
        dispatcher.forward(request, response);
        }
    }
    
    public double getTotalPrice(List<LineItemBean> items) throws SQLException {
        double sum = 0;
        for (LineItemBean item: items) {
            sum += item.getTotalPrice();
        }
        return sum;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubmitOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubmitOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
