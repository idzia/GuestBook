
function isEmpty() {
    var message = document.getElementById('message').value;
    var name = document.getElementById('name').value;
    var date = document.getElementById('date').value;
    if (message == "" || name == "" || date == "") {
        return true;
    } else return false;
}

function switchVisibility() {
    var sendButton = document.getElementById('submit');
    var check = isEmpty();
    console.log(check);
    if (check) {
        sendButton.setAttribute("disabled", "true");
    } else 
        sendButton.removeAttribute("disabled");      
}
