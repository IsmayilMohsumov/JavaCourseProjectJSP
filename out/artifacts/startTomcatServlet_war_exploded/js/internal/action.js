$(function () {
    $('#registerBtn').click(function () {
        $('#contentId').load('pages/tabForRegister/tab.html',function () {
            $('#tabs').tabs();
        })
    })
    $('#homeButton').click(function () {
    userList();
    });
    $('#addPersonId').click(function () {
        $('#add-user-dialog-form').load('pages/dialog/addUser.jsp',function () {
            $( "#add-user-dialog-form" ).dialog("open");
        })
    })
});


function loadCombo(dicKey,idFromEditUser) {
    //bele etdik cunki bazada UpdatePersonType adinda table yada column yoxdur axi
    var realDicKey= dicKey;

    if(dicKey=='personType'||dicKey=='updatePersonType'){
        realDicKey='personType';
    }

    $.ajax({
        url:'cnt?action=loadCombo',
        type:'get',
        data: 'dicKey='+realDicKey,
        contentType:'html',
        success:function (data) {
            if (dicKey=='personType'){
                $('#personType').html(data)
            }else if(dicKey=='updatePersonType'){
                $('#updatePersonType').html(data)
                $('#updatePersonType').prop('selectedIndex',idFromEditUser);
            }
        }
    })
}
function userList() {
        $.ajax({
            url:'cnt?action=userList',
            type:'get',
            contentType:'html',
            success:function (data) {
                $('#userRows').html(data);
                $('#userDiv').show();
            }
        })

}

function removeUser(id) {
    var a =confirm("Are you sure you want to delete this User?");
    if (a){
    $.ajax({
        url: 'cnt?action=removeUser',
        type: 'post',
        //data nece ki servletden data gelir burdanda gondermek olur hemcinin(get parameter edeceyimiz hisse)
        data: 'id='+id,
        success: function () {
            userList();
        }
    })
    }else {
        userList();
    }
}

function editUser(id) {
    var userId= id;
        $.ajax({
            url: 'cnt?action=editUser',
            type: 'get',
            //data nece ki servletden data gelir burdanda gondermek olur hemcinin(get parameter edeceyimiz hisse)
            data: 'userId='+userId,
            contentType:'html',
            success: function (data) {
                $('#update-user-dialog-form').html(data);
                $('#update-user-dialog-form').dialog('open');

            }
        })
}

function addUser() {
    //deyisenleri tanimliyiriq gelen deyerleri menimsetmek ucun
    var name = $('#name').val();
    var surname = $('#surname').val();
    var pin = $('#pin').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var personType =$('#personType').val();
    // alert(personType);

    $.ajax({
        url: 'cnt?action=addUser',
        type: 'post',
        //data nece ki servletden data gelir burdanda gondermek olur hemcinin(get parameter edeceyimiz hisse)
        data: 'name=' + name + '&surname=' + surname + '&pin=' + pin + '&email=' + email + '&password=' + password+'&personType='+personType,
        success: function () {
            $("#add-user-dialog-form").dialog("close");
            userList();
        }
    })
}

function updateUser() {
    //bu id ile bazada hansi useri deyiseceyimizi sececiyik ( where id=?)
    var mainID =$('#personMainId').val();

    //deyerleri gotururuk deyisilmish deyerleri
    var name = $('#updateName').val();
    var surname = $('#updateSurname').val();
    var pin = $('#updatePin').val();
    var email = $('#updateEmail').val();
    var personType = $('#updatePersonType').val();


    $.ajax({
        url: 'cnt?action=updateUser',
        type: 'post',
        data: 'id='+ mainID + '&name=' + name + '&surname=' + surname + '&pin=' + pin + '&email=' + email +'&personType='+personType,
        success: function () {
            $("#update-user-dialog-form").dialog("close");
            userList();
        }
    })
}

