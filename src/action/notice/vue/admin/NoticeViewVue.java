package action.notice.vue.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeViewVue implements Action {
	
	/*
	public String getJSON(List<NoticeVO> cVo) {
		List<NoticeVO> list = cVo;
		
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": [");
		
		System.out.println("[NoticeViewVue.java] (getJSON) list.get(0).getRegdate(): " + list.get(0).getRegdate());
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		System.out.println("[NoticeViewVue.java] (getJSON) regdate.format(list.get(0).getRegdate()): " + regdate.format(list.get(0).getRegdate()));
		
		result.append("{\"number\": \"" + list.get(0).getNumber() + "\",");
		result.append("\"id\": \"" + list.get(0).getId() + "\",");
		result.append("\"file\": \"" + list.get(0).getFile() + "\",");
		result.append("\"name\": \"" + list.get(0).getName() + "\",");
		result.append("\"category\": \"" + list.get(0).getCategory() + "\",");
		result.append("\"subject\": \"" + list.get(0).getSubject() + "\",");
		result.append("\"content\": \"" + list.get(0).getContent().replace("\"", "'") + "\",");
		result.append("\"reply_reference\": \"" + list.get(0).getReplyReference() + "\",");
		result.append("\"reply_depth\": \"" + list.get(0).getReplyDepth() + "\",");
		result.append("\"reply_sequence\": \"" + list.get(0).getReplySequence() + "\",");
		result.append("\"count\": \"" + list.get(0).getCount() + "\",");
		result.append("\"regdate\": \"" + regdate.format(list.get(0).getRegdate()) + "\"}");

		result.append("]}");
		
		System.out.println("[NoticeViewVue.java] (getJSON) result: " + result);
		return result.toString();
	}
	*/

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[NoticeViewVue.java] number : " + number);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		List<NoticeVO> nVo = new ArrayList<NoticeVO>();
		
		nDao.countUpdate(number);
		nVo = nDao.getNoticeViewVue(number);
		
		JSONObject obj= new JSONObject();
		JSONArray jArray = new JSONArray();
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		/*
		if (nVo != null) {
			System.out.println("[NoticeViewVue.java] 게시물을 호출합니다.");
			
			response.getWriter().write(getJSON(nVo));
		} else {
			System.out.println("[NoticeViewVue.java] 게시물 호출에 실패하였습니다.");
		}
		*/
		
		for (int i = 0; i < nVo.size(); i++) {
			JSONObject data = new JSONObject();
			System.out.println("[nVoView.java] nVo.get(i).getCategory(): " + nVo.get(i).getCategory());
			
			data.put("number", nVo.get(i).getNumber());
			data.put("category", nVo.get(i).getCategory());
			data.put("subject", nVo.get(i).getSubject());
			data.put("name", nVo.get(i).getName());
			data.put("content", nVo.get(i).getContent());
			data.put("address", nVo.get(i).getAddress());
			data.put("count", nVo.get(i).getCount());
			data.put("reply_reference", nVo.get(i).getReplyReference());
			data.put("reply_depth", nVo.get(i).getReplyDepth());
			data.put("reply_sequence", nVo.get(i).getReplySequence());
			data.put("regdate", regdate.format(nVo.get(i).getRegdate()));
			
			jArray.add(i, data);
		}
		
		System.out.println("[nVoView.java] jArray: " + jArray);
		obj.put("result", jArray);
		System.out.println("[nVoView.java] obj: " + obj);
		
		System.out.println("[NoticeViewVue.java] 게시물의 목록을 추출하였습니다.");
		
		response.getWriter().write(obj.toString());
		
		return null;
	}
}