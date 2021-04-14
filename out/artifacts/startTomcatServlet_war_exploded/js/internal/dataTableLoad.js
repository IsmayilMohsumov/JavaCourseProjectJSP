$(document).ready( function () {
    $('#table_id').DataTable();
    $('#userDiv').hide();

    $("#add-user-dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons: {
            "Add User": addUser,
            Cancel: function() {
                $("#add-user-dialog-form").dialog( "close" );
            }
        },
        close: function() {
            $("#add-user-dialog-form").dialog( "close" );
        }
    });

    $("#update-user-dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons: {
            "Edit User": updateUser,
            Cancel: function() {
                $("#update-user-dialog-form").dialog( "close" );
            }
        },
        close: function() {
            $("#update-user-dialog-form").dialog( "close" );
        }
    });


} );