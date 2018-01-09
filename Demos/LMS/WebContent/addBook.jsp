<%@page import="com.hui.model.Book"%>
<%@page import="com.hui.model.Admin"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	Admin a = (Admin)request.getSession().getAttribute("adminInfo");
	if (a == null) {
		response.sendRedirect("login.jsp");
	}
	String action = request.getParameter("action");
	String titleValue = "";
	String buttonValue = "";
	String actionValue = "";
	String readonly = "";
	Book b = new Book();
	if (action.equals("add")) {
		titleValue = "���ͼ��";
		buttonValue = "���";
		actionValue = "add";
	} else if (action.equals("update")) {
		b = (Book) request.getAttribute("bookInfo");
		titleValue = "�޸�ͼ��";
		buttonValue = "�޸�";
		readonly = "readonly disable";
		actionValue = "updateConfirm";
	}

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>ͼ�����</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td><div id="headright"><table width="100%" border="0" class="text">
					<tr>
						<td><a href="index.jsp">��ҳ</a></td>
						<td><a href="adminIndex.jsp">������ҳ</a></td>
						<td><a href="bookManage.jsp">ͼ�����</a></td>
						<td><a href="RecordShow">���ļ�¼</a></td>
						<td><a href="FineShow">�����¼</a></td>
						<td><a href="Logout">�˳�</a></td>
					</tr>
				</table></div></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox">

						<table width="100%" border="0">
							<tr>
								<td colspan="2" align="center"><div id="title"><%=titleValue%></div></td>
							</tr>
                            <tr>
								<td><form  action="BookManage?action=<%=actionValue%>" method="post"><table width="100%" border="0">
											<tr>
												<td width="33%" align="right">ISBN��</td>
												<td width="33%" align="center"><input name="ISBN" type="text" id="textfield" <%if(b.getISBN()!=null) {%>value="<%=b.getISBN()%>" <%} %><%=readonly%>></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">������</td>
												<td width="33%" align="center"><input type="text" <%if(b.getName()!=null) {%>value="<%=b.getName()%>"<%} %> name="name" id="textfield">
												</td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">���ߣ�</td>
												<td width="33%" align="center"><input type="text" <%if(b.getAuthor()!=null) { %>value="<%=b.getAuthor()%>" <%} %>name="author" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>											
											<tr>
												<td width="33%" align="right">ͼ�����ͣ�</td>
												<td width="33%" align="center"><input type="text" <%if(b.getCategory()!=null) {%>value="<%=b.getCategory()%>" <%} %>name="category" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">���ۣ�</td>
												<td width="33%" align="center"><input type="text" value="<%=b.getPrice()%>" name="price" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
										
											<tr>
												<td width="33%" align="right">�ݲأ�</td>
												<td width="33%" align="center"><input type="text" value="<%=b.getNumber()%>" name="number" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3" align="center"><input type="submit" name="button" id="button" value="<%=buttonValue%>"></td>
											</tr>
										</table>
									</form></td></tr>
						</table>
					</div></td>
			</table>
	</body>
</html>
