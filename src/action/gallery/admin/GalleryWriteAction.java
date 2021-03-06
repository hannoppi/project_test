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

public class GalleryWriteAction implements Action {

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
			System.out.println("[GalleryWriteAction.java] realPath : " + realPath); // 파일이 저장되는 주소입니다.
			
			// 서버 상의 물리적인 업로드 경로를 추출합니다.
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[GalleryWriteAction.java] files : " + files);
			
			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("fileName : " + fileName);
				
				/*
				* input type="file"의 이름 다음 이름이 더 있다면..
				* 즉, 지금 enumeration에서 가져온 이름이 마지막 요소가 아니라면..
				* 마지막 파일이 아니면 파일명 뒤에 콤마(,)를 붙여서 추가하고
				* 마지막 파일이면 콤마(,)를 붙이지 않고 추가합니다.
				*/
				
				if (files.hasMoreElements()) {
					saveFiles.add(',' + multi.getFilesystemName(fileName));
					System.out.println("[GalleryWriteAction.java] 다음 이미지가 존재합니다. saveFiles: " + saveFiles);
				} else {
					saveFiles.add(multi.getFilesystemName(fileName));
					System.out.println("[GalleryWriteAction.java] 다음 이미지가 존재하지 않습니다. saveFiles: " + saveFiles);
				}
			}
			
			StringBuffer buffer = new StringBuffer();
			
			System.out.println("[GalleryWriteAction.java] saveFiles.size(): " + saveFiles.size());
			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				System.out.println("[GalleryWriteAction.java] saveFiles.get(i): " + saveFiles.get(i));
				
				buffer.append(saveFiles.get(i));
				System.out.println("[GalleryWriteAction.java] buffer : " + buffer);
			}
			
			String str = buffer.toString();
			System.out.println("[GalleryWriteAction.java] str: " + str);
			
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
			/// gVo.setImage(buffer.toString());
			gVo.setImage(str);
			
			gVo = gDao.setGalleryWirte(gVo);
			System.out.println("[GalleryWriteAction.java] gVo : " + gVo);
			
			if (gVo != null) {
				System.out.println("[GalleryWriteAction.java] 게시물 등록에 성공하였습니다.");
				
				request.setAttribute("galleryView", gVo);
				
				url = "/admin/gallery/list.do";
			} else {
				System.out.println("[GalleryWriteAction.java] 게시물 등록에 실패하였습니다.");
				
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
