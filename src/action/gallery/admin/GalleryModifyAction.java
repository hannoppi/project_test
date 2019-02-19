package action.gallery.admin;

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
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryModifyAction implements Action {

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		GalleryVO gVo = new GalleryVO();
		
		try {
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/board/gallery/images";
			int maxFileSize = 5 * 1024 * 1024;
			
			realPath = request.getRealPath(savePath);
			System.out.println("[GalleryModifyAction.java] realPath : " + realPath); // 파일이 저장되는 주소입니다.
			
			// 서버 상의 물리적인 업로드 경로를 추출합니다.
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[GalleryModifyAction.java] files : " + files);
			
			String[] existingFile = multi.getParameterValues("existingFile");
			System.out.println("[GalleryModifyAction.java] existingFile: " + existingFile);
			
			for (int i = 0; i < existingFile.length; i++) {
				System.out.println("[GalleryModifyAction.java] existingFile[" + i + "]: " + existingFile[i]);
			}
			
			int index = existingFile.length;
			System.out.println("index: " + index);
			
			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("[GalleryModifyAction.java] fileName : " + fileName);
				System.out.println("[GalleryModifyAction.java] fileName.length() : " + fileName.length());				
				System.out.println("[GalleryModifyAction.java] multi.getFilesystemName(fileName): " + multi.getFilesystemName(fileName));
				
				if (multi.getFilesystemName(fileName) == null) {
					System.out.println("[GalleryModifyAction.java] existingFile[index - 1]: " + existingFile[index - 1]);
					
					if (!existingFile[index - 1].equals("")) {
						System.out.println("[GalleryModifyAction.java] existingFile 값이 존재합니다.");
						
						saveFiles.add(existingFile[index - 1] + ",");
					}
					
					System.out.println("[GalleryModifyAction.java] saveFiles: " + saveFiles);
				} else {
					saveFiles.add(multi.getFilesystemName(fileName) + ",");
					System.out.println("[GalleryModifyAction.java] saveFiles: " + saveFiles);
				}
				
				index--;
			}
			
			StringBuffer buffer = new StringBuffer();

			System.out.println("[GalleryModifyAction.java] saveFiles.size(): " + saveFiles.size());

			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				buffer.append(saveFiles.get(i));
				System.out.println("[GalleryModifyAction.java] buffer : " + buffer);
			}
			
			String str = buffer.toString();
			System.out.println("[GalleryModifyAction.java] str: " + str);

			if (str.charAt(str.length() - 1) == ',') {
				str = str.substring(0, str.length() - 1);
				System.out.println("[GalleryModifyAction.java] str: " + str);
			}
			
			int number = Integer.parseInt(multi.getParameter("number"));
			
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			String category = multi.getParameter("category");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			gVo.setId(id);
			gVo.setName(name);
			gVo.setCategory(category);
			gVo.setSubject(subject);
			gVo.setContent(content);
			// gVo.setImage(buffer.toString());
			gVo.setImage(str);
			
			gVo = gDao.setGalleryModify(number, gVo);
			System.out.println("[GalleryModifyAction.java] gVo : " + gVo);
			
			if (gVo != null) {
				System.out.println("[GalleryModifyAction.java] 게시물 수정에 성공하였습니다.");
				
				request.setAttribute("galleryModify", gVo);
				
				url = "/admin/gallery/view.do?number=" + number;
			} else {
				System.out.println("[GalleryModifyAction.java] 게시물 수정에 실패하였습니다.");
				
				url = "/admin/gallery/list.do";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
