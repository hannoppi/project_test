package action.board.vue.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class BoardModifyActionVue implements Action {
	
	public static String getBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		
		try {
			InputStream inputStream = request.getInputStream();
			
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				char[] charBuffer = new char[128];
				
				int bytesRead = -1;
				
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
				
			} else {
				stringBuilder.append("");
			}
			
		} catch (IOException exception) {
			throw exception;
			
		} finally {
			if (bufferedReader != null) {
				try {
					System.out.println("[BoardModifyActionVue.java] bufferedReader.close();");
					bufferedReader.close();
					
				} catch (IOException exception) {
					throw exception;
					
				}
			}
			
		}

		body = stringBuilder.toString();
		System.out.println("[BoardModifyActionVue.java] body: " + body);
		
		return body;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jsonStr = getBody(request);
		System.out.println("[BoardModifyActionVue.java] jsonStr.getClass().getName(): " + jsonStr.getClass().getName());
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(jsonStr);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println("[BoardModifyActionVue.java] jsonObj.getClass().getName(): " + jsonObj.getClass().getName());
		
		request.setCharacterEncoding("utf-8");
		
		int number = Integer.parseInt((String) jsonObj.get("number"));
		System.out.println("[BoardModifyActionVue.java] number : " + number);
		
		String id = (String) jsonObj.get("id");
		System.out.println("[BoardModifyActionVue.java] id : " + id);
		
		String name = (String) jsonObj.get("name");
		System.out.println("[BoardModifyActionVue.java] name : " + name);
		
		String category = (String) jsonObj.get("category");
		System.out.println("[BoardModifyActionVue.java] category : " + category);
		
		String subject = (String) jsonObj.get("subject");
		System.out.println("[BoardModifyActionVue.java] subject : " + subject);
		
		String content = (String) jsonObj.get("content");
		System.out.println("[BoardModifyActionVue.java] content : " + content);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bVo.setId(id);
		bVo.setName(name);
		bVo.setCategory(category);
		bVo.setSubject(subject);
		bVo.setContent(content);
		
		bVo = bDao.setBoardModify(number, bVo);
		System.out.println("[BoardModifyActionVue.java] bVo : " + bVo);
		
		if (bVo != null) {
			System.out.println("[BoardModifyActionVue.java] 게시물 수정에 성공하였습니다.");
		} else {
			System.out.println("[BoardModifyActionVue.java] 게시물 수정에 실패하였습니다.");
		}
		
		return null;
	}
}
