package action.member.vue.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;

public class MemberConfirmIdActionVue implements Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		System.out.println("[MemberConfirmIdActionVue.java] id: " + id);
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmId(id);
		System.out.println("[MemberConfirmIdActionVue.java] result: " + result);
		
		JSONObject obj= new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject data = new JSONObject();
		
		data.put("data", result);
		
		jArray.add(data);
		System.out.println("[MemberConfirmIdActionVue.java] jArray: " + jArray);
		
		obj.put("result", jArray);
		
		response.getWriter().write(obj.toString());
		
		System.out.println("[MemberConfirmIdActionVue.java] 성공적으로 처리되었습니다.");
	
		return null;
	}

}
