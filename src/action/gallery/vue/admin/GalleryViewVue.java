package action.gallery.vue.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryViewVue implements Action {
	
	public String getJSON(List<GalleryVO> cVo) {
		List<GalleryVO> list = cVo;
		
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": [");
		
		System.out.println("[BoardViewVue.java] (getJSON) list.get(0).getRegdate(): " + list.get(0).getRegdate());
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		System.out.println("[BoardViewVue.java] (getJSON) regdate.format(list.get(0).getRegdate()): " + regdate.format(list.get(0).getRegdate()));
		
		result.append("{\"number\": \"" + list.get(0).getNumber() + "\",");
		result.append("\"id\": \"" + list.get(0).getId() + "\",");
		result.append("\"name\": \"" + list.get(0).getName() + "\",");
		result.append("\"category\": \"" + list.get(0).getCategory() + "\",");
		result.append("\"subject\": \"" + list.get(0).getSubject() + "\",");
		result.append("\"content\": \"" + list.get(0).getContent().replace("\"", "'") + "\",");
		// result.append("\"reply_reference\": \"" + list.get(0).getReplyReference() + "\",");
		// result.append("\"reply_depth\": \"" + list.get(0).getReplyDepth() + "\",");
		// result.append("\"reply_sequence\": \"" + list.get(0).getReplySequence() + "\",");
		result.append("\"count\": \"" + list.get(0).getCount() + "\",");
		result.append("\"regdate\": \"" + regdate.format(list.get(0).getRegdate()) + "\"}");

		result.append("]}");
		
		System.out.println("[BoardViewVue.java] (getJSON) result: " + result);
		return result.toString();
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[GalleryView.java] number : " + number);
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		List<GalleryVO> gVo = new ArrayList<GalleryVO>();
		
		gDao.countUpdate(number);
		gVo = gDao.getGalleryViewVue(number);
		
		if (gVo != null) {
			System.out.println("[GalleryView.java] 게시물을 호출합니다.");
			
			response.getWriter().write(getJSON(gVo));
		} else {
			System.out.println("[GalleryView.java] 게시물 호출에 실패하였습니다.");
		}
		
		return null;
	}
}