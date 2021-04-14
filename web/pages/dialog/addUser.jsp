<%@ page import="java.util.UUID" %>
<script>
    $(function () {
        loadCombo('personType');
    })
</script>
<!--    <form>-->
<!--        <fieldset>-->
<!--            <label for="name">Name</label>-->
<!--            <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">-->
<!--            <label for="surname">Surname</label>-->
<!--            <input type="text" name="surname" id="surname"  class="text ui-widget-content ui-corner-all">-->
<!--            <label for="email">Email</label>-->
<!--            <input type="text" name="email" id="email"  class="text ui-widget-content ui-corner-all">-->
<!--            <label for="pin">Pin</label>-->
<!--            <input type="text" name="pin" id="pin"  class="text ui-widget-content ui-corner-all">-->
<!--            <label for="password">Password</label>-->
<!--            <input type="password" name="password" id="password"  class="text ui-widget-content ui-corner-all">-->

<!--            &lt;!&ndash; Allow form submission with keyboard without duplicating the dialog button &ndash;&gt;-->
<!--            <input type="hidden" tabindex="-1" style="position:center; top:-500px">-->
<!--        </fieldset>-->
<!--    </form>-->
<%
    String generate =UUID.randomUUID().toString().substring(0,5);
%>
<form >
    <fieldset style="width: 200px">
        <div><label class="labelForUser" for="name"> Name </label><input id="name" type="text" name="name" class="text ui-widget-content ui-corner-all"></div>
        <label for="surname"> Surname </label><input id="surname" type="text"  name="surname" class="text ui-widget-content ui-corner-all">
        <div>  <label for="pin"> Pin</label><input id="pin" type="text"  name="pin" class="text ui-widget-content ui-corner-all"></div>
        <label for="email"> Email</label><input id="email" type="text"  name="email" class="text ui-widget-content ui-corner-all"><br>
        <label for="password"> Password</label><input id="password" type="password"  name="password" value="" class="text ui-widget-content ui-corner-all"><input type="button" id="generateBtn" value="Generate"><br>
        <select  id="personType">
        </select>
    </fieldset>
</form>

<script>
    $('#generateBtn').click(function () {
        $('#password').val("<%=generate%>");
    })
</script>
