package action.file.admin;

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

public class FileModifyAction implements Action {

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
			System.out.println("[FileModifyAction.java] realPath : " + realPath); // 파일이 저장되는 주소입니다.
			
			// 서버 상의 물리적인 업로드 경로를 추출합니다.
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[FileModifyAction.java] files : " + files);
			
			String[] existingFile = multi.getParameterValues("existingFile");
			
			for (int i = 0; i < existingFile.length; i++) {
				System.out.println("[FileModifyAction.java] existingFile: " + existingFile[i]);
			}
			
			int index = existingFile.length;
			System.out.println("[FileModifyAction.java] index: " + index);

			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("[FileModifyAction.java] fileName : " + fileName);
				System.out.println("[FileModifyAction.java] fileName.length() : " + fileName.length());
				
				if (multi.getFilesystemName(fileName) == null) {
					System.out.println("[FileModifyAction.java] existingFile[index - 1]: " + existingFile[index - 1]);
					
					if (!existingFile[index - 1].equals("")) {
						System.out.println("[FileModifyAction.java] existingFile 값이 존재합니다.");
						
						saveFiles.add(existingFile[index - 1] + ",");
					}
					
					System.out.println("[FileModifyAction.java] saveFiles: " + saveFiles);
				} else {
					saveFiles.add(multi.getFilesystemName(fileName) + ",");
					System.out.println("[FileModifyAction.java] saveFiles: " + saveFiles);
				}
				
				index--;
			}
			
			StringBuffer buffer = new StringBuffer();

			System.out.println("[FileModifyAction.java] saveFiles.size(): " + saveFiles.size());

			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				buffer.append(saveFiles.get(i));
				
				System.out.println("[FileModifyAction.java] buffer : " + buffer);
			}
			
			String str = buffer.toString();
			System.out.println("[FileModifyAction.java] str: " + str);

			if (str.charAt(str.length() - 1) == ',') {
				str = str.substring(0, str.length() - 1);
				
				System.out.println("[FileModifyAction.java] str: " + str);
			}
			
			int number = Integer.parseInt(multi.getParameter("number"));
			
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
			// fVo.setFile(buffer.toString());
			fVo.setFile(str);
			
			fVo = fDao.setFileModify(number, fVo);
			System.out.println("[FileModifyAction.java] fVo : " + fVo);
			
			if (fVo != null) {
				System.out.println("[FileModifyAction.java] 게시물 수정에 성공하였습니다.");
				
				request.setAttribute("fileModify", fVo);
				
				url = "/admin/file/view.do?number=" + number;
			} else {
				System.out.println("[FileModifyAction.java] 게시물 수정에 실패하였습니다.");
				
				url = "/admin/file/list.do";
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