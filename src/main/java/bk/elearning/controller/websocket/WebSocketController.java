package bk.elearning.controller.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import bk.elearning.controller.websocket.ChatMessage.MessageType;
import bk.elearning.entity.dto.StudentResultQuestionDTO;
import bk.elearning.entity.dto.TimeCoundown;
import bk.elearning.service.IExamService;
import bk.elearning.service.impl.ExamServiceImpl;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Util;

@Controller
public class WebSocketController {
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	IExamService examService;

	ObjectMapper objectMapper = new ObjectMapper();
	/*
	 * @Scheduled(fixedDelay = 1000) public void publishUpdates() {
	 * template.convertAndSend("/check-connect/do-exam", "0"); }
	 */

	@MessageMapping("/connect.sendMessage")
	// @SendTo("/topic/publicChatRoom")
	public void sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

		try {
			template.convertAndSend("/check-connect/do-exam", Constant.STATUS_SUCCESS);
			Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
			String httpSessionId = sessionAttributes.get("sessionId").toString();
			HttpSession httpSession = ExamServiceImpl.getSessionProcess().get(httpSessionId);
			if (chatMessage.getType() == MessageType.START_TIME) {
			
				TimeCoundown tc = (TimeCoundown) (httpSession.getAttribute(Constant.SESSION_TIME_COUNTDOWN));
				tc.setTimeStart(Util.getDate().getTime());
				tc.setTimeEnd(tc.getTimeStart() + tc.getTimeLeft() * 60 * 1000 + Constant.DELAY_TIME);
				httpSession.setAttribute(Constant.SESSION_TIME_COUNTDOWN, tc);

			}
			// update data
			else if (chatMessage.getType() == MessageType.UPDATE) {
				try {
					String json = chatMessage.getContent();
					StudentResultQuestionDTO[] results = objectMapper.readValue(json, StudentResultQuestionDTO[].class);
					
					TimeCoundown tc = (TimeCoundown) (httpSession.getAttribute(Constant.SESSION_TIME_COUNTDOWN));
					if (Util.getDate().getTime() <= tc.getTimeEnd()) {
						for (StudentResultQuestionDTO studentResultQuestionDTO : results) {
							examService.updateResult(studentResultQuestionDTO);
						}
					}
					else
					{
						
						tc.setTimeLeft(0);
						
						examService.updateResult(tc);
						httpSession.removeAttribute(Constant.SESSION_TIME_COUNTDOWN);
						//ExamServiceImpl.getSessionProcess().remove(httpSessionId);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			else if (chatMessage.getType() == MessageType.FINISH_ATTEMPT) {
				try {
					TimeCoundown tc=(TimeCoundown) httpSession.getAttribute(Constant.SESSION_TIME_COUNTDOWN);
					
					tc.setTimeLeft(0);
					headerAccessor.getSessionAttributes().put("FINISH_ATTEMPT", true);
					examService.updateResult(tc);
					httpSession.removeAttribute(Constant.SESSION_TIME_COUNTDOWN);
					//ExamServiceImpl.getSessionProcess().remove(httpSessionId);
					} catch (Exception e) {
					// TODO: handle exception
						System.out.println(e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@MessageMapping("/connect.addUser")
	@SendTo("/check-connect/do-exam")
	public String addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		System.out.println("add user " + chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return "JOIN";
	}

}