package bk.elearning.controller.websocket;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import bk.elearning.entity.dto.TimeCoundown;
import bk.elearning.service.IExamService;
import bk.elearning.service.impl.ExamServiceImpl;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Util;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	IExamService examService;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out.println("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());


		String username = (String) headerAccessor.getSessionAttributes().get("username");

		if (username != null) {

			try {

				HttpSession httpSession =(HttpSession) SimpMessageHeaderAccessor.getSessionAttributes(event.getMessage().getHeaders())
						.get("httpSession");
				//ExamServiceImpl.getSessionProcess().remove(sessionId);
				TimeCoundown tc=(TimeCoundown) httpSession.getAttribute(Constant.SESSION_TIME_COUNTDOWN);
				//update time
				if(tc!=null)
				{
					Long currentTime=Util.getDate().getTime();
					int timeLeft=0;
					if(currentTime<tc.getTimeEnd())
					{
						timeLeft=(int) ((tc.getTimeEnd()-currentTime)/(60*1000));
						if(timeLeft<0) timeLeft=0;
					}
					tc.setTimeLeft(timeLeft);
					examService.updateResult(tc);
				//	httpSession.removeAttribute(Constant.SESSION_TIME_COUNTDOWN);
				}
				
				//close session
				//httpSession.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			

		}
	}

}
