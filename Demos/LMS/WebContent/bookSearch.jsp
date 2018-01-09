
<%@page import="com.hui.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hui.model.User"%>
<%@page import="com.hui.model.Admin"%>
<%@page contentType="text/html" pageEncoding="GBK"%>
<%  
	User u = null;
	Admin a = null;
	String identity  = null;
	if ((u=(User)request.getSession().getAttribute("userInfo")) != null) {
		identity = "user";
	} else if((a=(Admin)request.getSession().getAttribute("adminInfo"))!=null){
		identity = "admin";
	}
	ArrayList<Book> list= (ArrayList<Book>) request.getAttribute("searchresult");
	int pageCount = Integer.parseInt((String) request.getAttribute("pageCount"));
	int pageNow = Integer.parseInt(request.getParameter("pageNow"));
	String searchText = request.getParameter("searchText");
%>
<script type="text/javascript">
	function delBook() {
		window.document.getElementsByName("action").value = "delete";
		return window.confirm("Are you sure?");
	}


</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>"<%=searchText%>"���������</title>
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
				    <td><a href="index.jsp">��ҳ</a></td>
					<td><a href="login.jsp">���¼</a></td>
								
				<%} else if(identity.equals("user")){%>
					<%if (u != null) {%>
							<td><a href="index.jsp">��ҳ</a></td>
							<td><a href="userInfo.jsp">������Ϣ</a></td>
							<td><a href="RecordShow">���ļ�¼</a></td>
							<td><a href="FineShow">�����¼</a></td>
							<td><a href="Logout">�˳�</a></td>						 
					<%}	%>	 
				<%}	else{%>
				 		<%if (a != null) {%>
						<td><a href="index.jsp">��ҳ</a></td>
						<td><a href="adminIndex.jsp">������ҳ</a></td>
						<td><a href="RecordShow">���ļ�¼</a></td>
						<td><a href="FineShow">�����¼</a></td>
						<td><a href="Logout">�˳�</a></td>
					<%}%>
				<%}%>
			</tr>
			</table></div></td>
			<tr>
				<td align="center"><table width="100%" border="1" class="text">
				<br>
				<br>
				<br>
				<br>
						<tr>
							<th>ISBN</th>
							<th>����</th>
							<th>����</th>
							<th>����</th>
							<th>����</th>
							<th>�ݲ�</th>
								<%if (identity!=null &&identity.equals("admin")) {%>
								<th>�޸�</th><th>ɾ��</th>
								<%}%>
						</tr>
						<%
							//System.out.println(al);
							if (list != null)
								if (!list.isEmpty()) {
									for (Book book : list) {
						%><tr>
							<td><%=book.getISBN()%></td>
							<td><%=book.getName()%></td>
							<td><%=book.getAuthor()%></td>
							<td><%=book.getCategory()%></td>
							<td><%=book.getPrice()%></td>
							<td><%=book.getNumber()%></td>
							<%
								if (identity!=null &&identity.equals("admin")) {
							%> <td><form method="post" action="BookManage">
									<input type="hidden" name="ISBN" value="<%=book.getISBN()%>">
									<input type="hidden" name="action" value="update">
									<input type="submit" name="button" id="button" value="�޸�" >
								</form><%
							%><td><form method="post" action="BookManage">
									<input type="hidden" name="ISBN" value="<%=book.getISBN()%>">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="searchText" value="<%=searchText%>">
									<input type="hidden" name="pageNow" value="<%=pageNow%>">
									<input type="hidden" name="pageCount" value="<%=pageCount%>">
									<input type="submit" name="button" id="button" value="ɾ��" onClick="return delBook();"> </form><%
										}
									%></tr>
							<%
								}
							} else {
							%><tr><td colspan="9" align="center">��ɶҲû�ҵ�ѽ��(�R�n �Q) </td></tr><%									}

						%>

						<tr>
							<td colspan="9" align="center"><%
								if (pageNow != 1) {
								%><a href="BookSearch?searchText=<%=searchText%>&pageNow=1">��ҳ</a><%
								%>&nbsp;<%
								%><a href="BookSearch?searchText=<%=searchText%>&pageNow=<%=pageNow - 1%>">��һҳ</a><%
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
								%><a href="BookSearch?searchText=<%=searchText%>&pageNow=<%=i%>">[<%=i%>]</a><%
								} else {
								%>><%=i%><<%
									}
								%>&nbsp;<%
									}
									if (pageNow != pageCount & pageCount != 0) {
								%><a href="BookSearch?searchText=<%=searchText%>&pageNow=<%=pageNow + 1%>">��һҳ</a><%
								%>&nbsp;<%
								%><a href="BookSearch?searchText=<%=searchText%>&pageNow=<%=pageCount%>">ĩҳ</a><%
									}
								%></td>
						</tr>
					</table></td>
			</tr>
		</table>

    </body>
</html>
