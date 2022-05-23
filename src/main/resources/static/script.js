function sendMessage(form) {
    const XHR = new XMLHttpRequest();
    const FD = new FormData(form);
    XHR.addEventListener("load", function(event) {
        message_input.value = ''
    } );
    XHR.addEventListener("error", function( event) {
        console.log('Oops! Something went wrong.');
    } );
    XHR.open("POST", form.action);
    XHR.send(FD);
}

function updateMessages() {
    setTimeout(updateMessages, 500);
    getMessages(dialog.getAttribute('value'))
}

window.onload = updateMessages

function getMessages(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/dialogs/getMessages/" + id);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = () => parseMessages(xhr.responseText);
    xhr.send();
}

function parseMessages(messages) {
    let msgs = JSON.parse(messages)
    var arr = Object.keys(msgs).map(key => [key, msgs[key]]).sort((a, b) => b[0]-a[0])
    messages_div.innerHTML = ''
    for(let el in arr) {
        let msg = createMessageDiv(arr[el][1]['author']['id'], arr[el][1]['author']['firstName'], arr[el][1]['date'], arr[el][1]['text'])
        messages_div.appendChild(msg)
    }
}

function createMessageDiv(author_id, author_firstName, date, text) {
    date = new Date(date)
    options = {
      year: 'numeric', month: 'numeric', day: 'numeric',
      hour: 'numeric', minute: 'numeric', second: 'numeric',
      hour12: false
    };
    let msdiv = document.createElement('div')
    msdiv.classList.add('message')
    msdiv.innerHTML = `<div class="message_inf_block">
                         <a href="/profile/${author_id}">${author_firstName}</a>
                         <div class="message_inf_date">
                             ${date.toLocaleString('en-US', options)}
                         </div>
                     </div>
                     <div class="message_text_block">
                         ${text}
                     </div>`
    return msdiv
}
