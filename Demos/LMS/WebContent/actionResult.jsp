
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	String result = request.getParameter("result");
	String action = request.getParameter("action");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>�������</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td><div id="headright"><table width="100%" border="0" class="text">
					<tr>					
						<td><a href="index.jsp">��ҳ</a></td>
						<td><a href="adminIndex.jsp">������ҳ</a></td>
						<td><a href="userManage.jsp">�û�����</a></td>
						<td><a href="BookManage.jsp">ͼ�����</a></td>
						<td><a href="RecordShow">���ļ�¼</a></td>
						<td><a href="Logout">��ȫ�˳�</a></td>
					</tr>
				</table></div></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox">
						<table width="50%" border="1">
							<tr>
								<th colspan="2" align="center">�������</th>
							</tr>
							<tr>
								<td width="50%" align="center">����</td>
								<td width="50%" align="center"><%=action%></td>
							</tr>
							<tr>
								<td width="50%" align="center">���</td>
								<td width="50%" align="center"><%=result%></td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>

    </body>
</html>
