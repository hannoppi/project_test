package action.gallery.vue.admin;

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
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryModifyActionVue implements Action {
	
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
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		GalleryVO gVo = new GalleryVO();
		
		try {
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/board/gallery/images";
			int maxFileSize = 5 * 1024 * 1024;
			
			realPath = request.getRealPath(savePath);
			System.out.println("[GalleryModifyActionVue.java] realPath : " + realPath);
			
			List<String> saveFiles = new ArrayList<String>();
			
			multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration<?> files = multi.getFileNames();
			System.out.println("[GalleryModifyActionVue.java] files : " + files);
			
			String[] existingFile = multi.getParameterValues("existingFile");
			System.out.println("[GalleryModifyActionVue.java] existingFile: " + existingFile);
			
			for (int i = 0; i < existingFile.length; i++) {
				System.out.println("[GalleryModifyActionVue.java] existingFile[" + i + "]: " + existingFile[i]);
			}
			
			int index = existingFile.length;
			System.out.println("index: " + index);

			while(files.hasMoreElements()) {
				String fileName = (String) files.nextElement();
				System.out.println("[GalleryModifyActionVue.java] fileName : " + fileName);
				System.out.println("[GalleryModifyActionVue.java] fileName.length() : " + fileName.length());				
				System.out.println("[GalleryModifyActionVue.java] multi.getFilesystemName(fileName): " + multi.getFilesystemName(fileName));
				
				if (multi.getFilesystemName(fileName) == null) {
					System.out.println("[GalleryModifyActionVue.java] existingFile[index - 1]: " + existingFile[index - 1]);
					
					if (!existingFile[index - 1].equals("")) {
						System.out.println("[GalleryModifyActionVue.java] existingFile 값이 존재합니다.");
						
						saveFiles.add(existingFile[index - 1] + ",");
					}
					
					System.out.println("[GalleryModifyActionVue.java] saveFiles: " + saveFiles);
				} else {
					saveFiles.add(multi.getFilesystemName(fileName) + ",");
					System.out.println("[GalleryModifyActionVue.java] saveFiles: " + saveFiles);
				}
				
				index--;
			}
			
			StringBuffer buffer = new StringBuffer();

			System.out.println("[GalleryModifyActionVue.java] saveFiles.size(): " + saveFiles.size());

			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				buffer.append(saveFiles.get(i));
				System.out.println("[GalleryModifyActionVue.java] buffer : " + buffer);
			}
			
			String str = buffer.toString();
			System.out.println("[GalleryModifyActionVue.java] str: " + str);

			if (str.charAt(str.length() - 1) == ',') {
				str = str.substring(0, str.length() - 1);
				System.out.println("[GalleryModifyActionVue.java] str: " + str);
			}
			
			int number = Integer.parseInt((String) jsonObj.get("number"));
			System.out.println("[GalleryModifyActionVue.java] number : " + number);
			
			String id = (String) jsonObj.get("id");
			System.out.println("[GalleryModifyActionVue.java] id : " + id);
			
			String name = (String) jsonObj.get("name");
			System.out.println("[GalleryModifyActionVue.java] name : " + name);
			
			String category = (String) jsonObj.get("category");
			System.out.println("[GalleryModifyActionVue.java] category : " + category);
			
			String subject = (String) jsonObj.get("subject");
			System.out.println("[GalleryModifyActionVue.java] subject : " + subject);
			
			String content = (String) jsonObj.get("content");
			System.out.println("[GalleryModifyActionVue.java] content : " + content);
			
			gVo.setId(id);
			gVo.setName(name);
			gVo.setCategory(category);
			gVo.setSubject(subject);
			gVo.setContent(content);
			// gVo.setImage(buffer.toString());
			gVo.setImage(str);
			
			gVo = gDao.setGalleryModify(number, gVo);
			System.out.println("[GalleryModifyActionVue.java] gVo : " + gVo);
			
			if (gVo != null) {
				System.out.println("[GalleryModifyActionVue.java] 게시물 수정에 성공하였습니다.");
			} else {
				System.out.println("[GalleryModifyActionVue.java] 게시물 수정에 실패하였습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}