<%@page import="vo.ProVO"%>
<%@page import="dao.ProDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%
      
      ProDAO dao = new ProDAO();
      
      
      ArrayList<ProVO> list = dao.selectAll();
      out.println("<br/>");
      for(ProVO vo : list){
    	  out.println("���: " + vo.getEmpno() + " �̸�: " + vo.getEname() + " ����: " + vo.getJob() + " �μ���: " + vo.getDname()+"," + "<br/>");
      }
      
      
     String text = request.getParameter("txt");
	 out.println(text);
      if(text != null){
          ProDAO dao2 = new ProDAO();

    	  ProVO vo = dao2.getOneName(text);
    	  if(vo != null){
    		  out.println("���: " + vo.getEmpno() + " �̸�: " + vo.getEname() + " ����: " + vo.getJob() + " �μ���: " + vo.getDname()+"," + "<br/>");
    	  }
      } 
      
   %>