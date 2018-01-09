<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hui.model.*"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	User u = (User)request.getSession().getAttribute("userInfo");	 
	ArrayList<Record> list = (ArrayList<Record>)request.getAttribute("recordList");
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
	
	String viewType = request.getParameter("viewType");
	if (viewType == null) {
		viewType = "all";
	} else if (!(viewType.equals("borrow") || viewType.equals("return"))) {
		viewType = "all";
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>���ļ�¼</title>
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
					<td><a href="FineShow">�����¼</a></td>
					<td><a href="Logout">�˳�</a></td>
					</tr>
					</table></div></td>			 
					
			</tr>
			<tr>
				<td align="center"><div id="infobox"><table width="100%" border="0">
							<tr>
								<td align="right"><div id="title">
								<%if(u!=null){ %>
								�ҵĽ���
								<%}else { %>
								�û�����
								<%} %>
								</div></td>
							</tr>
							<tr>
								<td><div id="menu">
										<table width="100%" border="0">
											<tr>
												<td width="70%" align="right">&nbsp;</td>
												<td width="10%" align="right">
												<%if (viewType.equals("all")) {%>
												ȫ������
												<%} else {%>
												<a href="RecordShow?viewType=all">ȫ������</a>
												<%}%></td>
											  <td width="10%" align="right">
											  <%if (viewType.equals("return")) {%>
											  	�ѹ黹
											  	<%} else {%>
											  	<a href="RecordShow?viewType=return">�ѹ黹</a>
											  	<%}%></td>
											  <td width="10%" align="right">
											  <%if (viewType.equals("borrow")) {%>
											   δ�黹
											  <%} else {%><a href="RecordShow?viewType=borrow">δ�黹</a>
											  <%}%></td>
											</tr>
										</table>

									</div></td>
							</tr>
							<tr>
								<td><table width="100%" border="1">
										<tr>
											<th>ISBN</th>
											<%if(u != null) {%>
											<td>����Ա</td>
											<%}else{ %>
											<td>�û�</td>
											<% }%>
											<th>���ʱ��</th>
											<th>����ʱ��</th>
											<th>�黹ʱ��</th>
										</tr>
										<%
											if (list != null)
												if (!list.isEmpty()) {
													for (Record r : list) {
														Date date = new Date(r.getBorrowDate().getTime() + 30 * 24 * 3600 * 1000L);
										%>
										<tr>
											<td><%=r.getISBN()%></td>
											<%if(u!=null) {%>
											<td><%=r.getAdminName() %>
											<%}else {%>
											<td><%= r.getUserName()%>
											<%} %>
											<td><%=r.getBorrowDate()%></td>											
											<td><%=date%></td>
											<%
												if (r.getReturnDate() != null) {
											%>
											<td><%=r.getReturnDate()%></td>
											<%
											} else {
											%><td>δ�黹</td><%													}
											%>

										</tr>
										<%											}
										} else {
										%>û�м�¼<%													}
										%>
										<tr>
											<td colspan="5" align="center"><%
												if (pageNow != 1) {
												%><a href="RecordShow?viewType=<%=viewType%>&pageNow=1">��ҳ</a><%
												%>&nbsp;<%
												%><a href="RecordShow?viewType=<%=viewType%>&pageNow=<%=pageNow - 1%>">��һҳ</a><%
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
												%><a href="RecordShow?viewType=<%=viewType%>&pageNow=<%=i%>">[<%=i%>]</a><%
												} else {
												%>><%=i%><<%
													}
												%>&nbsp;<%
													}
													if (pageNow != pageCount && pageCount != 0) {
												%><a href="RecordShow?viewType=<%=viewType%>&pageNow=<%=pageNow + 1%>">��һҳ</a><%
												%>&nbsp;<%
												%><a href="RecordShow?viewType=<%=viewType%>&pageNow=<%=pageCount%>">ĩҳ</a><%
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
