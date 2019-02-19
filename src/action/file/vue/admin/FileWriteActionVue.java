package action.file.vue.admin;

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
import dao.FileDAO;
import dto.FileVO;

public class FileWriteActionVue implements Action {

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		FileDAO fDao = FileDAO.getInstance();
		FileVO fVo = new FileVO();
		
		try {
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/board/file";
			int maxFileSize = 5 * 1024 * 1024;
			
			realPath = request.getRealPath(savePath);
			System.out.println("[FileWriteAction.java] realPath : " + realPath);
			
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[FileWriteAction.java] files : " + files);
			
			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("[FileWriteAction.java] fileName : " + fileName);
				
				if (files.hasMoreElements()) {
					saveFiles.add(multi.getFilesystemName(fileName));
					System.out.println("[FileWriteAction.java] saveFiles: " + saveFiles);
				} else {
					// saveFiles.add(multi.getFilesystemName(fileName) + ",");
					saveFiles.add(multi.getFilesystemName(fileName));
					System.out.println("[FileWriteAction.java] saveFiles: " + saveFiles);
				}
			}
			
			StringBuffer buffer = new StringBuffer();
			
			System.out.println("[FileWriteAction.java] saveFiles.size(): " + saveFiles.size());
			
			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				buffer.append(saveFiles.get(i));
				System.out.println("[FileWriteAction.java] buffer : " + buffer);
			}
			
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			String category = multi.getParameter("category");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			fVo.setId(id);
			fVo.setName(name);
			fVo.setCategory(category);
			fVo.setSubject(subject);
			fVo.setContent(content);
			fVo.setFile(buffer.toString());
			
			fVo = fDao.setFileWirte(fVo);
			System.out.println("[FileWriteAction.java] fVo : " + fVo);
			
			if (fVo != null) {
				System.out.println("[FileWriteAction.java] 게시물 등록에 성공하였습니다.");
				
				// request.setAttribute("fileView", fVo);
				
				// url = "/admin/file/list.do";
			} else {
				System.out.println("[FileWriteAction.java] 게시물 등록에 실패하였습니다.");
				
				// url = "/admin/file/list.do";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}