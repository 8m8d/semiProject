<%@page import="dao.EmployeeDAO"%>
<%@page import="vo.EmployeeVO"%>
<%@page import="vo.ProVO"%>
<%@page import="dao.ProDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%
      
      ProDAO dao = new ProDAO();
      String name = null;
      
      ArrayList<EmployeeVO> list = dao.selectAll();
      for(EmployeeVO vo : list){
    	  name = vo.geteName();
    	  out.println("���: " + vo.geteNumber() + " �̸�: " + vo.geteName() + " ����: " + vo.geteOfficialResponsibilities() + " �μ���: " + vo.getdName()+",");
      }

     String text = request.getParameter("txt");
	 out.println(text);
      if(text != null){
    	  ProDAO dao2 = new ProDAO();

    	  EmployeeVO vo = dao2.getOneName(text);
    	  if(vo != null){
    		  out.println("���: " + vo.geteNumber() + " �̸�: " + vo.geteName() + " ����: " + vo.geteOfficialResponsibilities() + " �μ���: " + vo.getdName()+"," );
    	  }
      } 
      
      
      
   %>
