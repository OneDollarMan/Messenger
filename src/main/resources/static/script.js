function sendMessage() {
    const XHR = new XMLHttpRequest();
    const FD = new FormData(sendMessageForm);
    XHR.addEventListener( "load", function(event) {
    } );
    XHR.addEventListener("error", function( event) {
        console.log('Oops! Something went wrong.');
    } );
    XHR.open("POST", sendMessageForm.action);
    XHR.send(FD);
}

var stompClient = null;

function connect() {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var name = document.getElementById('name').value;
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}