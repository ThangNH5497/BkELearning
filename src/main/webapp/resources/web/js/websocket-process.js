$(document).ready(function() {

	connect();
	
});
'use strict';

/*var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');*/

var stompClient = null;
var username = null;

var colors = [
  '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
  username = userLoged.id;

  if(username) {
      var socket = new SockJS(rootLocation+'ws');
      stompClient = Stomp.over(socket);

      stompClient.connect({}, onConnected, onError);
  }
}


function onConnected() {
  // Subscribe to the Public Topic
  stompClient.subscribe('/check-connect/do-exam', onMessageReceived);

  // Tell your username to the server
  stompClient.send("/app/connect.addUser",
      {},
      JSON.stringify({sender: username, type: 'JOIN'})
  )

}


function onError(error) {
  alert('erro :'+error)
}


function sendMessage(msg,type) {

  if(msg && stompClient) {
      var chatMessage = {
          sender: username,
          content: msg,
          type: type
      };
      stompClient.send("/app/connect.sendMessage", {}, JSON.stringify(chatMessage));
 
  }
}


function onMessageReceived(payload) {
	var msg=payload.body;
	if(msg=='JOIN')
	{
		//send time start for server
		var date = new Date();
		sendMessage(date.getTime(),'START_TIME');
		$('.loader').addClass('hidden');
		$('#main-content').removeClass('hidden');
		timeCount(timeLeft);
		
	}
	/*msg=JSON.parse(msg);
	alert(msg.content);*/
	
}



