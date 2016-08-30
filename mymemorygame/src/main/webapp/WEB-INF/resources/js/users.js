/**
 * Created by bondarm on 23.08.16.
 */

var deleteuser = function (id) {
    var confirmdelete = function() {
        return result;
    };
    if (confirm("Are you sure you want to delete user?")) {
        $.ajax({
            url: _ctx + "/users/" + id,
            type: 'delete',
            success: function (data) {
                deletesuccess(id, data);
            },
            error: function () {
                deleteerror(id);
            }
        });
    } else alert("false");
};

function deletesuccess(id, data) {
    $('[id="row'+id+'"]').animate(
        {
            "background-color" : "#D68044",
            "color" : "#D68044",
            "border" : "D6A944 2px solid"
        },
    800).animate({
        "opacity" : "0"
    },
    200,
    function () {
        $(this).hide();
    })
}

function deleteerror(id) {
    alert("Something went wrong while user id=" + id + " deleting")
}