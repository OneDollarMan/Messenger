function sendMessage(form) {
    const XHR = new XMLHttpRequest();
    const FD = new FormData(form);
    XHR.addEventListener("load", function(event) {
        message_input.value = ''
        console.log(XHR.responseText)
    } );
    XHR.addEventListener("error", function( event) {
        console.log('Oops! Something went wrong.');
    } );
    XHR.open("POST", form.action);
    XHR.send(FD);
}

function updateMessages(id) {
    setTimeout(updateMessages, 300);
}
