function sendMessage(form) {
    const XHR = new XMLHttpRequest();
    const FD = new FormData(form);
    XHR.addEventListener("load", function(event) {
    } );
    XHR.addEventListener("error", function( event) {
        console.log('Oops! Something went wrong.');
    } );
    XHR.open("POST", form.action);
    XHR.send(FD);
}