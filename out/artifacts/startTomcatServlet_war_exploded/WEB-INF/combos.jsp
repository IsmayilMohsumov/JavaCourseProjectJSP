<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<%@ page import="ModelDTO.DictionaryDTO" %>

<%
    Map<String, List<DictionaryDTO>> comboDatas = (Map<String, List<DictionaryDTO>>) request.getAttribute("comboDatas");

    for (Map.Entry<String, List<DictionaryDTO>> comboData : comboDatas.entrySet()) {
        if (comboData.getKey().equalsIgnoreCase("personType")||(comboData.getKey().equalsIgnoreCase("updatePersonType"))) { %>

<option>Person Type</option>
<%
    List<DictionaryDTO> values = comboData.getValue();
    for (DictionaryDTO datas : values) {
%>

<option value="<%=datas.getId()%>"> <%=datas.getDicVal()%></option>

<%
    }
%>

<% }
}
%>
