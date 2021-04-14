<%@ page import="java.util.UUID" %>
<%@ page import="ModelDTO.UserDTO" %>
<%
    UserDTO user= (UserDTO) request.getAttribute("user");  // deyerleri cekdik
%>
<script>
    $(function () {
        loadCombo('updatePersonType',<%=user.getPersonType()%>);
    })
</script>

<form >
    <fieldset style="width: 200px">
        <input type="hidden" value="<%=user.getId()%>" id="personMainId">
        <div><label class="labelForUser" for="updateName"> Name </label><input id="updateName" type="text" name="updateName" value="<%=user.getName()%>" class="text ui-widget-content ui-corner-all"></div>
        <label for="updateSurname"> Surname </label><input id="updateSurname" type="text" name="updateSurname" value="<%=user.getSurname()%>" class="text ui-widget-content ui-corner-all">
        <div>  <label for="updatePin"> Pin</label><input id="updatePin" type="text"  name="updatePin" value="<%=user.getPin()%>" class="text ui-widget-content ui-corner-all"></div>
        <label for="updateEmail"> Email</label><input id="updateEmail" type="text"  name="updateEmail" value="<%=user.getEmail()%>" class="text ui-widget-content ui-corner-all"><br>
<%--        <label for="updatePassword"> Password</label><input id="updatePassword" type="password"  name="updatePassword" value="" class="text ui-widget-content ui-corner-all"><input type="button" id="generateBtn" value="Generate"><br>--%>
        <select  id="updatePersonType">
        </select>
    </fieldset>
</form>


