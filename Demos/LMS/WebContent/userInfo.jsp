
<%@page import="com.hui.model.User"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	User u = (User)request.getSession().getAttribute("userInfo");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>�û���Ϣ</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    
    <table align="center"  width="100%" border="0">
	<tr>
		<td><div id="headright"><table width="100%" border="0" class="text">
					<tr>
					
						<%if (u != null) {%>
						<td><div id="username">���ã�<%=u.getName()%></div></td>
						<%} else {%>
						<td><a href="login.jsp">���¼</a></td>
						<%}	%>
						<td>   </td>
                        <td><a href="index.jsp">��ҳ</a></td>
						<td><a href="RecordShow">���ļ�¼</a></td>
						<td><a href="FineShow">�����¼</a></td>
						<td><a href="Logout">�˳�</a></td>
					</tr>
				</table></div></td>
		</tr>
	    <tr>
				<td align="center"><table width="100%" border="1" class="text">
							<tr>
								<td colspan="2" align="center"><div id="title">�û���Ϣ</div></td>
							</tr>
							<tr>
								<td width="40%" align="right">ID��</td>
								<td align="left"><%=u.getID()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">������</td>
								<td align="left"><%=u.getName()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">�Ա�</td>
								<td align="left"><%=u.getSex()%></td>
							</tr>							
							<tr>
								<td width="40%" align="right">����ʱ�䣺</td>
								<td align="left"><%=u.getDate()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">�ɽ�������</td>
								<td align="left"><%=u.getNumber()%></td>
							</tr>
						</table></div></td>
			</tr>
		 </table>
    </body>
</html>
