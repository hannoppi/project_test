package action.notice.vue.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeWriteActionVue implements Action {
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		try {
			NoticeDAO nDao = NoticeDAO.getInstance();
			NoticeVO nVo = new NoticeVO();
			
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/board/file";
			int maxFileSize = 5 * 1024 * 1024;
			
			realPath = request.getRealPath(savePath);
			System.out.println("[NoticeWriteActionVue.java] realPath : " + realPath);
			
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[NoticeWriteActionVue.java] files : " + files);
			
			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("[NoticeWriteActionVue.java] fileName : " + fileName);
				
				if (files.hasMoreElements()) {
					saveFiles.add(multi.getFilesystemName(fileName));
					System.out.println("[NoticeWriteActionVue.java] saveFiles: " + saveFiles);
				} else {
					saveFiles.add(multi.getFilesystemName(fileName));
					System.out.println("[NoticeWriteActionVue.java] saveFiles: " + saveFiles);
				}
			}
			
			StringBuffer buffer = new StringBuffer();
			
			System.out.println("[NoticeWriteActionVue.java] saveFiles.size(): " + saveFiles.size());
			
			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				buffer.append(saveFiles.get(i));
				System.out.println("[NoticeWriteActionVue.java] buffer : " + buffer);
			}
			
			String id = multi.getParameter("id");
			System.out.println("[NoticeWriteActionVue.java] id : " + id);
			
			String name = multi.getParameter("name");
			System.out.println("[NoticeWriteActionVue.java] name : " + name);
			
			String category = multi.getParameter("category");
			System.out.println("[NoticeWriteActionVue.java] category: " + category);
			
			String subject = multi.getParameter("subject");
			System.out.println("[NoticeWriteActionVue.java] subject: " + subject);
			
			String content = multi.getParameter("content");
			System.out.println("[NoticeWriteActionVue.java] content: " + content);
			
			System.out.println("buffer.toString(): " + buffer.toString());
			
			nVo.setId(id);
			nVo.setName(name);
			nVo.setCategory(category);
			nVo.setSubject(subject);
			nVo.setContent(content);
			nVo.setFile(buffer.toString());
			
			nVo = nDao.setNoticeWirte(nVo);
			System.out.println("nVo : " + nVo);
			
			if (nVo != null) {
				System.out.println("[NoticeWriteActionVue.java] 게시물 등록에 성공하였습니다.");
			} else {
				System.out.println("[NoticeWriteActionVue.java] 게시물 등록에 실패하였습니다.");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}