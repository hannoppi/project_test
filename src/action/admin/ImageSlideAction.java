package action.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.ImageSlideDAO;
import dto.ImageSlideVO;

public class ImageSlideAction implements Action {

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		ImageSlideDAO isDao = ImageSlideDAO.getInstance();
		ImageSlideVO isVo = new ImageSlideVO();
		List<ImageSlideVO> imageSlideList = new ArrayList<ImageSlideVO>();
		
		String where = request.getParameter("where");
		System.out.println("[ImageSlideAction.java] where: " + where);
		
		String[] strChkArr = null;
		int[] intChkArr = null;
		
		ActionForward forward = new ActionForward();
		
		int[] numberArr = null;
		String[] fileArr = null;
		String[] subjectArr = null;
		String[] contentArr = null;
		String[] existingFile = null;
		
		try {
			if (where != null) {
				MultipartRequest multi = null;
								
				String realPath = "";
				String savePath = "/admin/upload/slide/main/images";
				int maxFileSize = 5 * 1024 * 1024;
				
				realPath = request.getRealPath(savePath);
				System.out.println("[ImageSlideAction.java] realPath : " + realPath);
				
				List<String> saveFiles = null;
				
				multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
				
				if ("write".equals(where)) {
					System.out.println("[ImageSlideAction.java] 이미지 슬라이드 등록 프로세서에 접근합니다.");
					
					File files = null;
					
					strChkArr = multi.getParameterValues("go");
					System.out.println("[ImageSlideAction.java] strChkArr: " + strChkArr);
					
					intChkArr = new int[strChkArr.length];
					numberArr = new int[strChkArr.length];
					fileArr = new String[strChkArr.length];
					subjectArr = new String[strChkArr.length];
					contentArr = new String[strChkArr.length];
					existingFile = new String[strChkArr.length];
					
					for (int i = 0; i < strChkArr.length; i++) {
						saveFiles = new ArrayList<String>();
						
						System.out.println("[ImageSlideAction.java]  strChkArr[" + i + "]: " + strChkArr[i]);
						
						intChkArr[i] = Integer.parseInt(strChkArr[i]);
						System.out.println("[ImageSlideAction.java]  intChkArr[" + i + "]: " + intChkArr[i]);
						
						System.out.println("[ImageSlideAction.java] intChkArr[" + i + "] - 1: " + (intChkArr[i] - 1));
						
						numberArr[i] = Integer.parseInt(multi.getParameterValues("number")[intChkArr[i] - 1]);
						System.out.println("[ImageSlideAction.java]  numberArr[" + i + "]: " + numberArr[i]);
						
						subjectArr[i] = multi.getParameterValues("subject")[intChkArr[i] - 1];
						System.out.println("[ImageSlideAction.java]  subjectArr[" + i + "]: " + subjectArr[i]);
						
						contentArr[i] = multi.getParameterValues("content")[intChkArr[i] - 1];
						System.out.println("[ImageSlideAction.java]  contentArr[" + i + "]: " + contentArr[i]);
						
						String[] test =  multi.getParameterValues("upload_file");
						System.out.print("[ImageSlideAction.java] test: " + test);
						
						for (int j = 0; j < fileArr.length; j++) {
							System.out.println("[ImageSlideAction.java] fileArr[" + j + "]: " + fileArr[j]);
						}
						
						files = multi.getFile("upload_file" + (intChkArr[i]));
						System.out.println("[ImageSlideAction.java] files : " + files);
						
						String fileName = multi.getFilesystemName("upload_file" + (intChkArr[i]));
						System.out.println("[ImageSlideAction.java] fileName: " + fileName);
						
						System.out.println("[ImageSlideAction.java] multi.getFilesystemName(fileName): " + multi.getFilesystemName(fileName));
						
						saveFiles.add(multi.getFilesystemName("upload_file" + (intChkArr[i])));
						System.out.println("[ImageSlideAction.java] saveFiles: " + saveFiles);
						
	                    StringBuffer buffer = new StringBuffer();
	                    
	                    System.out.println("[ImageSlideAction.java] saveFiles: " + saveFiles);
	                    
	                    System.out.println("[ImageSlideAction.java] saveFiles.size(): " + saveFiles.size());
	                    
	                    buffer.append(saveFiles.get(0));
						System.out.println("[ImageSlideAction.java] buffer : " + buffer);
	                    
	                    String str = buffer.toString();
	                    System.out.println("[ImageSlideAction.java] str: " + str);
	                    
	                    if (str.charAt(str.length() - 1) == ',') {
	                        str = str.substring(0, str.length() - 1);
	                        System.out.println("[ImageSlideAction.java] str: " + str);
	                    }
	                    
	                    isVo.setNumber(numberArr[i]);
	                    isVo.setFile(str);
	                    isVo.setSubject(subjectArr[i]);
	                    isVo.setContent(contentArr[i]);

	                    System.out.println("[ImageSlideAction.java] 게시물을 등록합니다.");

	                    isVo = isDao.setImageSlideWirte(isVo);
					}
				}
				
				if ("modify".equals(where)) {
					System.out.println("[ImageSlideAction.java] 이미지 슬라이드 수정 프로세서에 접근합니다.");
					
					File files = null;
					
					strChkArr = multi.getParameterValues("check");
					System.out.println("[ImageSlideAction.java] strChkArr: " + strChkArr);
					
					if (strChkArr != null) {
						intChkArr = new int[strChkArr.length];
						numberArr = new int[strChkArr.length];
						fileArr = new String[strChkArr.length];
						subjectArr = new String[strChkArr.length];
						contentArr = new String[strChkArr.length];
						existingFile = new String[strChkArr.length];
						
						for (int i = 0; i < strChkArr.length; i++) {
							saveFiles = new ArrayList<String>();
							
							System.out.println("[ImageSlideAction.java] strChkArr[" + i + "]: " + strChkArr[i]);
							
							intChkArr[i] = Integer.parseInt(strChkArr[i]);
							System.out.println("[ImageSlideAction.java] intChkArr[" + i + "]: " + intChkArr[i]);
							
							System.out.println("[ImageSlideAction.java] intChkArr[" + i + "] - 1: " + (intChkArr[i] - 1));
							
							numberArr[i] = Integer.parseInt(multi.getParameterValues("number")[intChkArr[i] - 1]);
							System.out.println("[ImageSlideAction.java] numberArr[" + i + "]: " + numberArr[i]);
							
							subjectArr[i] = multi.getParameterValues("subject")[intChkArr[i] - 1];
							System.out.println("[ImageSlideAction.java] subjectArr[" + i + "]: " + subjectArr[i]);
							
							contentArr[i] = multi.getParameterValues("content")[intChkArr[i] - 1];
							System.out.println("[ImageSlideAction.java] contentArr[" + i + "]: " + contentArr[i]);
							
							existingFile[i] = multi.getParameterValues("existingFile")[intChkArr[i] - 1];
							System.out.println("[ImageSlideAction.java] existingFile[" + i + "]: " + existingFile[i]);
							
							for (int j = 0; j < existingFile.length; j++) {
								System.out.println("[ImageSlideAction.java] existingFile[" + j + "]: " + existingFile[j]);
							}
							
							files = multi.getFile("file" + (intChkArr[i]));
							System.out.println("[ImageSlideAction.java] files : " + files);
							
							System.out.println("[ImageSlideAction.java] intChkArr[i]: " + intChkArr[i]);
							
							String fileName = multi.getFilesystemName("file" + (intChkArr[i]));
							System.out.println("[ImageSlideAction.java] fileName: " + fileName);
							
							System.out.println("[ImageSlideAction.java] multi.getFilesystemName(fileName): " + multi.getFilesystemName(fileName));
							
							if (fileName == null) {
								if (!multi.getParameterValues("existingFile")[intChkArr[i] - 1].equals("")) {
									System.out.println("[ImageSlideAction.java] existingFile 값이 존재합니다.");
									
									saveFiles.add(multi.getParameterValues("existingFile")[intChkArr[i] - 1] + ",");
								}
								
								System.out.println("[ImageSlideAction.java] saveFiles: " + saveFiles);
							} else {
								saveFiles.add(multi.getFilesystemName("file" + (intChkArr[i])));
								System.out.println("[ImageSlideAction.java] saveFiles: " + saveFiles);
							}
							
							StringBuffer buffer = new StringBuffer();
							
							System.out.println("[ImageSlideAction.java] saveFiles: " + saveFiles);
							System.out.println("[ImageSlideAction.java] saveFiles.size(): " + saveFiles.size());
							
							buffer.append(saveFiles.get(0));
							System.out.println("[ImageSlideAction.java] buffer : " + buffer);
							
							String str = buffer.toString();
							System.out.println("[ImageSlideAction.java] str: " + str);
							
							if (str.charAt(str.length() - 1) == ',') {
								str = str.substring(0, str.length() - 1);
								System.out.println("[ImageSlideAction.java] str: " + str);
							}
							
							isVo.setNumber(numberArr[i]);
							isVo.setFile(str);
							isVo.setSubject(subjectArr[i]);
							isVo.setContent(contentArr[i]);

                            System.out.println("[ImageSlideAction.java] 게시물을 수정합니다.");
                            
                            isVo = isDao.setImageSlideModify(isVo, intChkArr[i]);
                            
                            System.out.println("[ImageSlideAction.java] " + (i + 1) + " 번째 실행문 탈출!");
                            System.out.println("[ImageSlideAction.java] &nbsp;");
                            System.out.println("[ImageSlideAction.java] &nbsp;");
						}
					}
				}
				
				System.out.println("[ImageSlideAction.java] isVo : " + isVo);
				
				if (isVo != null) {
					System.out.println("[ImageSlideAction.java] 작업이 성공적으로 처리되었습니다.");
					
					request.setAttribute("imageSlideList", isVo);
					
					url = "/admin/slide.do";
				} else {
					System.out.println("[ImageSlideAction.java] 작업이 실패하였습니다.");
					
					url = "/admin/slide.do";
				}
				
				forward.setRedirect(true);
			} else {
				System.out.println("[ImageSlideAction.java] 이미지 슬라이드 목록 추출 프로세서에 접근합니다.");
				
				int currentPage = 1; // 최초 시작 페이지
				int limit = 10; // 화면에 노출할 게시물 개수
				
				String keyword = request.getParameter("keyword");
				System.out.println("[ImageSlideAction.java] keyword: " + keyword);
				
				String option = request.getParameter("option");
				System.out.println("[ImageSlideAction.java] option: " + option);
				
				System.out.println("[ImageSlideAction.java] request.getParameter(\"page\"): " + request.getParameter("page"));
				
				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정
					System.out.println("[ImageSlideAction.java] currentPage : " + currentPage);
				}
				
				int listCount = isDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출
				System.out.println("[ImageSlideAction.java] listCount : " + listCount);
				
				imageSlideList = isDao.getImageSlideList(currentPage, limit, keyword, option); // 목록을 추출
				System.out.println("[ImageSlideAction.java] imageSlideList: " + imageSlideList);
				
				// 전체 페이지 개수
				int maxPage = (int) ((double) listCount / limit + 0.95);
				
				// 현재 페이지에 노출할 시작 페이지 개수(1, 11, 21)
				int startPage = (((int) ((double) currentPage / 5 + 0.9)) - 1) * 5 + 1;
				
				// 현재 페이지에 노출할 마지막 페이지 수(10, 20, 30) 5 페이지씩
				int endPage = startPage + 5 - 1;
				
				if (endPage > maxPage) {
					endPage = maxPage;
				}
				
				if (imageSlideList != null) {
					request.setAttribute("imageSlideList", imageSlideList);
					
					url = "/admin/html/slide/list.jsp";
				} else {
					System.out.println("[ImageSlideAction.java] 게시물의 목록을 추출하는데 실패하였습니다.");
				}
				
				forward.setRedirect(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		forward.setPath(url);
		
		return forward;
	}
}