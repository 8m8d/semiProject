<%@page import="dao.EmployeeDAO"%>
<%@page import="vo.EmployeeVO"%>
<%@page import="vo.ProVO"%>
<%@page import="dao.ProDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%
      
      ProDAO dao = new ProDAO();
      
      
      ArrayList<EmployeeVO> list = dao.selectAll();
      out.println("<br/>");
      for(EmployeeVO vo : list){
    	  out.println("���: " + vo.getE_number() + " �̸�: " + vo.getE_name() + " ����: " + vo.getE_official_responsibilities() + " �μ���: " + vo.getD_name()+"," + "<br/>");
      }
      
      
     String text = request.getParameter("txt");
	 out.println(text);
      if(text != null){
    	  ProDAO dao2 = new ProDAO();

    	  EmployeeVO vo = dao2.getOneName(text);
    	  if(vo != null){
    		  out.println("���: " + vo.getE_number() + " �̸�: " + vo.getE_name() + " ����: " + vo.getE_official_responsibilities() + " �μ���: " + vo.getD_name()+"," + "<br/>");
    	  }
      } 
      
   %>