<%@ page import="ModelDTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<UserDTO> users=(List<UserDTO>)request.getAttribute("userList");
    for (UserDTO user: users) {
%>
<tr>
    <td><%=user.getId()%></td>
    <td><%=user.getName()%></td>
    <td><%=user.getSurname()%></td>
    <td><%=user.getPin()%></td>
    <td><%=user.getEmail()%></td>
    <td><%=user.getPersonLabel()%></td>
    <td>10/20/20</td>
    <td><a id="removeUserId" href="javascript:removeUser(<%=user.getId()%>)" style="color: red" class="fa-trash">Remove User</a> <br> <a id="editUserId" href="javascript:editUser(<%=user.getId()%>)" style="color: gold;word-break: break-all" class="fa-trash">Edit User</a></td>

</tr>

<%}%>

