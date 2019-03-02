/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vinod
 */
public class ShoppingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        try {
            String name = req.getParameter("name");
            String price = req.getParameter("Price");
            HttpSession session = req.getSession();
            ArrayList<String> list = (ArrayList<String>) session.getAttribute("itemList");

            String item = name + "," + price;

            if (list == null) {
                list = new ArrayList<>();
            }
            
            list.add(item);
            session.setAttribute("itemList", list);
            pw.print("<table>");
            pw.print("<tr>");
            pw.print("<th>Item</th>");
            pw.print("<th>Unit Price</th>");
            pw.print("<th>QTY</th>");
            pw.print("<th>Amount</th>");
            double total = 0;
            for (String string : list) {
                String[] split = string.split(",");
                pw.print("<tr>");
                pw.print("<td>"+split[0]+"</td>");
                pw.print("<td>"+split[1]+"</td>");
                pw.print("<td>QTY</td>");
                pw.print("<td>"+split[1]+"</td>");
                pw.print("</tr>");
                total = total+Integer.parseInt(split[1]);
            }
            pw.print("</table>");
           
            pw.print("Total is : "+ total);
        } finally {
            pw.close();
        }
    }
}
