<%@page import="vo.ScheduleVO"%>
<%@page import="dao.ScheduleDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%
      
      ScheduleDAO dao = new ScheduleDAO();
      
      
      ArrayList<ScheduleVO> list = dao.selectAll();
      out.println("<br/>");
      for(ScheduleVO vo : list){
    	  out.println("������ȣ: " + vo.getS_NUMBER() + " ��������: " + vo.getS_CONTENTS() + " ������: " + vo.getS_START_DATE() + " ������: " + vo.getS_END_DATE()+" ���: " +vo.getE_NUMBER() +  "," + "<br/>");
      }
      
      
    /*  String text = request.getParameter("txt");
	 out.println(text);
      if(text != null){
    	  ScheduleDAO dao2 = new ScheduleDAO();

          ScheduleVO vo = dao2.getOneName(text);
    	  if(vo != null){
    		  out.println("���: " + vo.getEmpno() + " �̸�: " + vo.getEname() + " ����: " + vo.getJob() + " �μ���: " + vo.getDname()+"," + "<br/>");
    	  }
      }  */
      
   %>