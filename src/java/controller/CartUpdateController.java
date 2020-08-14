/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.BookBean;
import bean.CartBean;
import bean.LineItemBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.BookDAO;

/**
 *
 * @author Nam
 */
public class CartUpdateController extends HttpServlet {

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
            
            if (session.getAttribute("user") != null) {

                // params from bookListing.jsp
                String title = request.getParameter("title");
                String isbn = request.getParameter("isbn");
 //               int quantity = Integer.parseInt(request.getParameter("quantity"));
//                int updatedBookQuantity = Integer.parseInt(request.getParameter("updateQuantity"));
  //              System.out.println("the quantity is: " + quantity);
 //               System.out.println("fdfdsfs: " + updatedBookQuantity);
                // params from checkout.jsp
                // todo: modify the cart, includes both cases insert new cart item and update the cart 
                CartBean cart = (CartBean) session.getAttribute("cart");
                
                // check if it is a removal, then remove this book from the cart, from the checkout.jsp
                if (request.getParameter("remove") != null) {
                    LineItemBean item = cart.getItems().stream().filter(lineItem -> lineItem.getIsbn().equals(isbn)).collect(Collectors.toList()).get(0);
                    cart.remove(item);
                    session.setAttribute("cart", cart);
                    request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                }
                // in case there is no cart yet
                if (cart == null) {
                    cart = new CartBean();
                    LineItemBean lineItem = new LineItemBean();
                    lineItem.setIsbn(isbn);
                    lineItem.setTitle(title);
                    lineItem.setQuantity(1);
                    cart.add(lineItem);
                    System.out.println("the title from CartController: " + title);

                    session.setAttribute("cart", cart);
                    request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                } else if (request.getParameter("updateQuantity") != null) { // if new updated quantity is not null, definitely there is a book with a previous quantity available.
                    int newUpdatedQuantity = Integer.parseInt(request.getParameter("updateQuantity"));
                    for (LineItemBean item : cart.getItems()) {
                        if (item.getIsbn().equals(isbn)) {
                            item.setQuantity(newUpdatedQuantity);
                            session.setAttribute("cart", cart);
                            request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                            return;
                        }
                    }
                } else {
                    
                    // loop through all the books in the cart and +1 quantity to the right book, action perform in the booklisting when this book is already in the cart and then click "add to cart" button.
                    for (LineItemBean item : cart.getItems()) {
                        if (item.getIsbn().equals(isbn)) {
                            item.setQuantity(item.getQuantity() + 1);
                            session.setAttribute("cart", cart);
                            request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                            return;
                        }
                    }
                    // if there is no matched book in the cart, create a new one... 
                    LineItemBean item = new LineItemBean();
                    item.setIsbn(isbn);
                    item.setTitle(title);
                    item.setQuantity(1);
                    cart.add(item);
                    session.setAttribute("cart", cart);
                    request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("filter/login.jsp");
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CartUpdateController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CartUpdateController.class.getName()).log(Level.SEVERE, null, ex);
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
