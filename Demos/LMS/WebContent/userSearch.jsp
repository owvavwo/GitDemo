<%@page import="com.hui.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%
	String identity = null;
	if (request.getSession().getAttribute("userInfo") != null) {
		identity = "user";
	} else if(request.getSession().getAttribute("adminInfo")!=null){
		identity = "admin";
	}
	
	ArrayList<User> list = (ArrayList<User>) request.getAttribute("searchresult");
	int pageCount = Integer.parseInt((String) request.getAttribute("pageCount"));
	int pageNow = Integer.parseInt(request.getParameter("pageNow"));
	String userSearch = request.getParameter("userSearch");
%>
<script type="text/javascript">
	function delUser() {
		window.document.getElementsByName("action").value = "del";
		return window.confirm("ȷ��Ҫɾ���û���");
	}


</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=userSearch%> - �������</title>
		<link href="css/result.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td><div id="headright"><table width="100%" border="0" class="text">
			<tr>
				<%
					if (identity == null) {
				%>		
					<td><a href="login.jsp">���¼</a></td>
								
				<%} else if(identity.equals("user")){%>
						<td><a href="index.jsp">��ҳ</a></td>
						<td><a href="userInfo.jsp">������Ϣ</a></td>
						<td><a href="RecordShow">���ļ�¼</a></td>
						<td><a href="Logout">�˳�</a></td>						 
				<%}	else{%>
					<td><a href="index.jsp">��ҳ</a></td>
					<td><a href="adminIndex.jsp">������ҳ</a></td>
					<td><a href="userManage.jsp">�û�����</a></td>
					<td><a href="bookManage.jsp">ͼ�����</a></td>
					<td><a href="Logout">�˳�</a></td>
				<%}%>
			</tr>
			</table></div></td>
			</tr>
			<tr>
				<td align="center"><table width="100%" border="1" class="text">
				<br>
				<br>
				<br>
				<br>
						<tr>
							<th>�û�ID</th>
							<th>����</th>
							<th>�Ա�</th>
							<th>ע��ʱ��</th>
							<th>�ɽ�����</th>

							<%
								if (identity.equals("admin")) {
							%><th>�޸�</th><%
							%><th>ɾ��</th><%									}
								%>
						</tr>
						<%
							if (list != null)
								if (!list.isEmpty()) {
									for (User u : list) {
						%><tr>
							<td><%=u.getID()%></td>
							<td><%=u.getName()%></td>
							<td><%=u.getSex()%></td>
							<td><%=u.getDate()%></td>
							<td><%=u.getNumber()%></td>
							<%
								if (identity.equals("admin")) {
							%> <td><form method="post" action="UserRegister">
									<input type="hidden" name="username" value="<%=u.getName()%>">
									<input type="hidden" name="action" value="update">
									<input type="submit" name="button" id="button" value="�޸�" >
								</form><%
							%><td><form method="post" action="UserRegister">
									<input type="hidden" name="username" value="<%=u.getName()%>">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="userSearch" value="<%=userSearch%>">
									<input type="hidden" name="pageNow" value="<%=pageNow%>">
									<input type="hidden" name="pageCount" value="<%=pageCount%>">
									<input type="submit" name="button" id="button" value="ɾ��" onClick="return delUser();"> </form><%
									}
									%></tr>
							<%
								}
							} else {
							%><tr><td colspan="9" align="center">��ɶҲû�ҵ�ѽ��(�R�n �Q)</td></tr><%									}

						%>

						<tr>
							<td colspan="9" align="center"><%
								if (pageNow != 1) {
								%><a href="UserSearch?userSearch=<%=userSearch%>&pageNow=1">��ҳ</a><%
								%>&nbsp;<%
								%><a href="UserSearch?userSearch=<%=userSearch%>&pageNow=<%=pageNow - 1%>">��һҳ</a><%
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
								%><a href="UserSearch?userSearch=<%=userSearch%>&pageNow=<%=i%>">[<%=i%>]</a><%
								} else {
								%>><%=i%><<%
									}
								%>&nbsp;<%
									}
									if (pageNow != pageCount & pageCount != 0) {
								%><a href="UserSearch?userSearch=<%=userSearch%>&pageNow=<%=pageNow + 1%>">��һҳ</a><%
								%>&nbsp;<%
								%><a href="UserSearch?userSearch=<%=userSearch%>&pageNow=<%=pageCount%>">ĩҳ</a><%
									}
								%></td>
						</tr>
					</table></td>
			</tr>
			
		</table>

    </body>
</html>
