<%@ page import="java.io.File " %>
<%@ page import="java.io.* " %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<title>파일 다운로드</title>
	</head>
	<body>
		<%
			// 다운로드할 파일의 이름을 추출합니다.
			String fileName = request.getParameter("fileName");
			
			// 파일이 저장되어 있는 폴더 이름입니다.
			String saveFolder = "/admin/upload/board/file/";
			
			// Context에 대한 정보를 추출합니다.
			ServletContext context = getServletContext(); // Servlet에 대한 환경정보를 추출합니다.
			
			// 실제 파일이 저장되어 있는 폴더의 경로 추출합니다.
			String realFolder = context.getRealPath(saveFolder);
			
			// 다운로드할 파일의 전체 경로를 추출합니다.
			String filePath = realFolder + "\\" + fileName;
			
			try {
				out.clear(); // out → jsp 자체 객체입니다.
				
				out = pageContext.pushBody(); // out → jsp 자체 객체입니다.
				
				// 다운로드할 파일을 호출합니다.
				File file = new File(filePath);
				byte b[] = new byte[4096];
				
				// ContentType을 동적으로 바꾸기 위해 초기화합니다.
				response.reset();
				response.setContentType("application/octet-stream");
				
				String Encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
				
				// 링크를 클릭했을 때 다운로드 기능이 돌아가도록 합니다.
				response.setHeader("Content-Disposition", "attachment; filename = " + Encoding);
				
				// 파일의 상세 정보를 추출합니다.
				FileInputStream in = new FileInputStream(filePath);
				
				// 파일에서 추출한 상세 정보를 저장합니다.
				ServletOutputStream srvltOtptStrm = response.getOutputStream();
				
				int numRead;
				
				// byte 배열 b의 0번부터 numRead 번까지 파일에 출력합니다.
				while ((numRead = in.read(b, 0, b.length)) != -1) {
					srvltOtptStrm.write(b, 0, numRead);
				}
				
				srvltOtptStrm.flush();
				srvltOtptStrm.close();
				
				in.close();
			} catch (Exception e) {
				
			}
		%>
	</body>
</html>