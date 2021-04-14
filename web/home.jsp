<%@ page import="java.util.List" %>
<%@ page import="ModelDTO.UserDTO" %>
<%@ page import="ModelDTO.RoleDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavaScript</title>

    <link rel="stylesheet"  href="css/external/jquery.dataTables.min.css">
    <link rel="stylesheet"  href="css/external/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/internal/styleIndex.css">

    <script src="js/external/jquery3.4.1min.js" ></script>
    <script src="js/external/jquery-ui.js"></script>
    <script  src="js/external/jquery.dataTables.min.js"></script>
    <script src="js/internal/dataTableLoad.js"></script>
    <script src="js/internal/action.js"></script>

</head>
<body>

<%
    UserDTO user = (UserDTO) session.getAttribute("loginUser");
    if(user == null || user.getRoles()==null || user.getRoles().isEmpty()){
        response.sendRedirect("login.jsp");
        return;
    }
    List<RoleDTO> roles =user.getRoles(); // umumi role listini gotururuk
    System.out.println("Rollar cedveli::"+roles);
//    if(roles==null || roles.isEmpty()){
//        response.sendRedirect("login.jsp");
//        return;
//    }
    List<String> roleNamesTable = new ArrayList<>();  // bu liste ise ancaq table daki name leri menimsedeciyik (admin , user )
     if( roles!=null){
         for (RoleDTO roleDTO: roles ) {
             roleNamesTable.add(roleDTO.getName());  //listin icine GetName leri add edirik .

         }
     }


%>
<header>
    <a  href="lgin?action=logout"  style="float: right; font-size: 23px;color: #dcf5f5">Log Out</a>

    <img id="imageOfUser" src="upload/<%=user.getImageUrl()%>">



    <h2>Welcome : <%=user.getName()+" "+user.getSurname()%> </h2>
</header>


<section>
    <nav>
        <ul>
            <li><a id="homeButton" class="active" href="#" >Persons</a></li>
            <li><a id="registerBtn" href="#" >Registration</a></li>
            <li><a href="#contact" class="disabled" target="_blank">Contact</a></li>
            <li><a href="#about" class="disabled" target="_blank">About</a></li>
            <% if(roleNamesTable.contains("admin")){ %>
            <li><a href="#about"target="_blank">Payments</a></li>
            <%}%>
        </ul>
    </nav>

    <article id="contentId">


        <% if(user.getImageUrl()!=null){%>
        <form method="post" action="upload" enctype="multipart/form-data">
            <input name="file" type="file">
            <input type="submit" value="Change Photo">
        </form>
        <%}else{%>
        <form method="post" action="upload" enctype="multipart/form-data">
            <input name="file" type="file">
            <input type="submit" value="Click Me!">
        </form>
        <%}%>

    <div id="userDiv">
                <table id="table_id" class="display" >
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>SURNAME</th>
                        <th>PIN</th>
                        <th>EMAIL</th>
                        <th>POSITION</th>
                        <th>DATE</th>
                        <th style="width: 103px">ACTION</th>


                    </tr>
                    </thead>


                    <tbody id="userRows">
<%--                    //bura deyerler gelecek--%>
                    </tbody>

                </table>

    <div id="add-user-dialog-form" title="Create new user"></div>

<%--        //edit userinde dialog acilacaq--%>
    <div id="update-user-dialog-form" title="Update user"></div>


        <button id="addPersonId">Add User</button>
    </div>

    </article>
</section>

<footer>
<p>Copyright by Ismayil</p>
</footer>


</body>
</html>
