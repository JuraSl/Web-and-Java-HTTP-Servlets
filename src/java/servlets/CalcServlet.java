/*
 * 
 */
package servlets;

import calc.CalcOperators;
import calc.OperationType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalcServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet CalcServlet at " + request.getContextPath() + "</h2>");
        
        try{
            
            // reading parameters
            double one = Double.valueOf(request.getParameter("one").toString()).doubleValue();
            double two = Double.valueOf(request.getParameter("two").toString()).doubleValue();
            String operation = String.valueOf(request.getParameter("operation"));
            
            // create session
            HttpSession session = request.getSession(true);
            
            // getting operation type (String)
            OperationType operType = OperationType.valueOf(operation.toUpperCase());
            
            // calculation
            double result = calcResult(operType, one, two);
            
            ArrayList<String> operationList;
            
            // creating new list for new session
            if(session.isNew()){
                operationList = new ArrayList<String>();
            }else{
                operationList = (ArrayList<String>)session.getAttribute("formula");
            }
            
            // adding new operation to the list and session atribute
            operationList.add(one + " " + operType.getStringValue() + " " + two + " = " + result);
            session.setAttribute("formula", operationList);
            
            // print all operations
            out.println("<h3>Your session Id is: " + session.getId() + "</h3>");
            out.println("<h3> All operation list :" + operationList.size() + "</h3>");
            
            // print operations
            for (String list: operationList){
                out.println("<h3>" + list + "</h3>");
            }
            
        }catch(Exception e){
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }finally{
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private double calcResult(OperationType operType, double one, double two){
        
        double result = 0;
        switch(operType){
            case ADD: { result = CalcOperators.add(one, two); break;}
            case DIVIDE: {result =  CalcOperators.add(one, two); break;}
            case SUBSTRACT: {result =  CalcOperators.add(one, two); break;}
            case MULTIPLY: {result =  CalcOperators.add(one, two); break;}
        }
        return result;
    }
}
