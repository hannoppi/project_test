package action.file.vue.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.FileDAO;
import dto.FileVO;

public class FileModifyActionVue implements Action {
	
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
					System.out.println("bufferedReader.close();");
					bufferedReader.close();
					
				} catch (IOException exception) {
					throw exception;
					
				}
			}
			
		}

		body = stringBuilder.toString();
		System.out.println("body: " + body);
		
		return body;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jsonStr = getBody(request);
		System.out.println("jsonStr.getClass().getName(): " + jsonStr.getClass().getName());
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(jsonStr);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println("jsonObj.getClass().getName(): " + jsonObj.getClass().getName());
		
		request.setCharacterEncoding("utf-8");
		
		FileDAO fDao = FileDAO.getInstance();
		FileVO fVo = new FileVO();
		
		try {
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/board/file";
			int maxFileSize = 5 * 1024 * 1024;
			
			realPath = request.getRealPath(savePath);
			System.out.println("[FileModifyAction.java] realPath : " + realPath);
			
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
			} else {
				System.out.println("[FileModifyAction.java] 게시물 수정에 실패하였습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}