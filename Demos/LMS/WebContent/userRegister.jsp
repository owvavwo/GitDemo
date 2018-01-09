<%@page import="java.sql.Date"%>
<%@page import="com.hui.model.User"%>
<%@page import="com.hui.model.Admin"%>
<%@page import="java.text.SimpleDateFormat"%>

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
	String dateValue = "";
	Date currentDate = new Date(System.currentTimeMillis());
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	dateValue = df.format(currentDate);
	User u = new User();
	if (action.equals("add")) {
		titleValue = "�����û�";
		buttonValue = "����";
		actionValue = "add";		
	} else if (action.equals("update")) {
		u = (User)request.getAttribute("updateInfo");
		titleValue = "�޸��û�";
		buttonValue = "�޸�";
		readonly = "readonly disable";
		actionValue = "updateConfirm";
	}

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>�û�����</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td><div id="headright"><table width="100%" border="0" class="text">
					<tr>
						<td><a href="index.jsp">��ҳ</a></td>
						<td><a href="userManage.jsp">�û�����</a></td>
						<td><a href="bookManage.jsp">ͼ�����</a></td>
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
								<td><form  action="UserRegister?action=<%=actionValue%>" method="post"><table width="100%" border="0">
											<tr>
												<td width="33%" align="right">�û�����</td>
												<td width="33%" align="center"><input type="text" <%if(u.getName()!=null) { %>value="<%=u.getName()%>" <%} %> name="username" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">���룺</td>
												<td width="33%" align="center"><input type="password" value="" name="password" id="textfield">
												</td>
												<td width="33%">&nbsp;</td>
											</tr>
											
											<tr>
												<td width="33%" align="right">�Ա�</td>
												<td width="33%" align="center"><input type="text" <%if(u.getSex()!=null) { %>value="<%=u.getSex()%>" <%} %> name="sex" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											
											<tr>
												<td width="33%" align="right">����ʱ�䣺</td>
												<td width="33%" align="center"><input type="text" value="<%=dateValue%>" name="date" id="textfield" readonly disable></td>
												<td width="33%">&nbsp;</td>
											</tr>
											
											<tr>
												<td width="33%" align="right">�ɽ�������</td>
												<td width="33%" align="center"><input type="text" value="3" name="number" id="textfield"></td>
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
