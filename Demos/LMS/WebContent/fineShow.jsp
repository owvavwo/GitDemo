<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hui.model.*"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	User u = (User)request.getSession().getAttribute("userInfo");	 
	ArrayList<Fine> list = (ArrayList<Fine>)request.getAttribute("fineList");
	int pageCount = 1;
	String pageCountString = (String) request.getParameter("pageCount");
	if (pageCountString != null) {
		pageCount = Integer.parseInt(pageCountString);
	}
	int pageNow = 1;
	String pageNowString = request.getParameter("pageNow");
	if (pageNowString != null) {
		pageNow = Integer.parseInt(pageNowString);
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>�����¼</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
			<td><div id="headright"><table width="100%" border="0" class="text">
					<tr>
					<td><a href="index.jsp">��ҳ</a></td>
					<%if(u != null) {%>
					<td><a href="userInfo.jsp">������Ϣ</a></td>					
					<%} else {%>
					<td><a href="adminIndex.jsp">������ҳ</a></td>
					<%} %>	
					<td><a href="RecordShow">���ļ�¼</a></td>
					<td><a href="Logout">�˳�</a></td>
					</tr>
					</table></div></td>			 
					
			</tr>
			<tr>
				<td align="center"><div id="infobox"><table width="100%" border="0">
							<tr>
								<td align="right"><div id="title">�����¼</div></td>
							</tr>
							<tr>
								<td><table width="100%" border="1">
										<tr>
											<th>SN</th>
											<td>�û�</td>
											<th>ISBN</th>
											<th>ʱ��</th>
											<th>����</th>
										</tr>
										<%
											if (list != null)
												if (!list.isEmpty()) {
													for (Fine r : list) {
										%>
										<tr>
											<td><%=r.getSN()%></td>
											<td><%= r.getUserName()%>
											<td><%=r.getISBN()%></td>											
											<td><%=r.getTime()%></td>
											<td><%=r.getFine() %></td>
											<%}%>

										</tr>
										<%} else {%>
										û�м�¼
										<%}	%>
										<tr>
											<td colspan="5" align="center"><%
												if (pageNow != 1) {
												%><a href="FineShow?pageNow=1">��ҳ</a><%
												%>&nbsp;<%
												%><a href="FineShow?pageNow=<%=pageNow - 1%>">��һҳ</a><%
												%>&nbsp;<%
													}
													for (int i = pageNow - 2; i <= pageNow + 2; i++) {
														if (i <= 0) {
															continue;
														}
														if (i > pageCount) {
															break;
														}
														if (i != pageNow) {
												%><a href="FineShow?pageNow=<%=i%>">[<%=i%>]</a><%
												} else {
												%>><%=i%><<%
													}
												%>&nbsp;<%
													}
													if (pageNow != pageCount && pageCount != 0) {
												%><a href="FineShow?pageNow=<%=pageNow + 1%>">��һҳ</a><%
												%>&nbsp;<%
												%><a href="FineShow?pageNow=<%=pageCount%>">ĩҳ</a><%
													}
												%></td>
										</tr>

									</table></td>
							</tr>
						</table></div></td>
			</tr>
			
		</table>
    </body>
</html>
