package com.shakirov.coffeeservice.servlets;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dao.CoffeeTypeListDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.ElementNotFoundException;
import com.shakirov.coffeeservice.dto.CoffeeType;
import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import com.shakirov.coffeeservice.utils.SettingsUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeOrderServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String[] list = request.getParameterValues("id");
        try {
            CoffeeOrderDao orderDao = DaoFactory.getInstance(SettingsUtil.getDataSource()).getCoffeeOrderDao();
            if (list.length > 0) {

                CoffeeTypeListDao coffeeTypes;
                try {
                    coffeeTypes = DaoFactory.getInstance(SettingsUtil.getDataSource()).getCoffeeTypeList();
                } catch (SQLException ex) {
                    Logger.getLogger(CoffeeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ServletException(ex.fillInStackTrace());
                }

                double cost = 0;
                for (String s : list) {
                    int i = Integer.valueOf(s);
                    if ("on".equals(request.getParameter("check" + i))) {
                        int count = Integer.valueOf(request.getParameter("count" + i));
                        if (count > 0)
                        {
                            CoffeeOrderItem orderItem = new CoffeeOrderItem(0, (CoffeeType)coffeeTypes.getItemById(i), orderDao.getOrder(), count);
                            orderDao.addItem(orderItem);
                            cost += orderItem.getQuantity() * orderItem.getType().getPrice();
                        }
                    }
                }
                orderDao.getOrder().setCost(cost);
            }
            request.setAttribute("orderDao", orderDao);
            request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
        } catch (SQLException | ElementNotFoundException ex) {
                Logger.getLogger(CoffeeOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServletException(ex.fillInStackTrace());
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
