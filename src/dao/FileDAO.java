package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.FileVO;
import utility.Manager;

public class FileDAO {
	private FileDAO() {}
	
	private static FileDAO instance = new FileDAO();
	
	public static FileDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 조회수 업데이트
	public void countUpdate(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "";
		
		try {
			sql = "UPDATE DOWNLOAD_BOARD SET count = count + 1 WHERE number = " + number;
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
	} // countUpdate();
	
	// 게시물의 전체 개수를 추출
	public int getListCount(String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT COUNT(*) FROM DOWNLOAD_BOARD";
		
		int result = -1;
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[FileDAO.java] (getListCount) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%'";
		}
		
		System.out.println("[FileDAO.java] (getListCount) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getInt(1);
				System.out.println("[FileDAO.java] (getListCount) result : " + result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	} // getListCount();
	
	// 게시물의 목록을 추출
	public List<FileVO> getFileList(int currentPage, int limit, String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM DOWNLOAD_BOARD";
		
		// limit: 한 페이지에 노출할 게시물의 개수입니다.
		// int start = (currentPage - 1) * limit + 1; // 읽기 시작할 row 번호입니다.
		int start = (currentPage - 1) * limit; // 읽기 시작할 row 번호입니다.
		System.out.println("[FileDAO.java] (getFileList) start : " + start);
		
		// int end = start + limit - 1; // 읽을 마지막 row 번호입니다.
		int end = -1;
		
		if (currentPage == 1) {
			System.out.println("if");
			end = start + limit; // 읽을 마지막 row 번호입니다.
		} else {
			System.out.println("else");
			end = start; // 읽을 마지막 row 번호입니다.
		}
		
		System.out.println("[FileDAO.java] (getFileList) end : " + end);
		
		FileVO fVo = null;
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		System.out.println("[FileDAO.java] (getFileList) keyword: " + keyword);
		System.out.println("[FileDAO.java] getFileList() option: " + option);
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[FileDAO.java] (getFileList) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%' ORDER BY number DESC limit ?, ?";
		} else {
			System.out.println("[FileDAO.java] (getFileList) 키워드가 존재하지 않아 진입하지 못하였습니다.");
			
			 sql += " " + "ORDER BY number DESC limit ?, ?";
		}
		
		System.out.println("[FileDAO.java] (getFileList) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, start - 1);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				fVo = new FileVO();
				
				fVo.setNumber(resultSet.getInt("number"));
				fVo.setName(resultSet.getString("name"));
				fVo.setCategory(resultSet.getString("category"));
				fVo.setSubject(resultSet.getString("subject"));
				fVo.setContent(resultSet.getString("content"));
				fVo.setAddress(resultSet.getString("address"));
				fVo.setCount(resultSet.getInt("count"));
				fVo.setRegdate(resultSet.getTimestamp("regdate"));
				fVo.setFile(resultSet.getString("file"));
				
				fileList.add(fVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[FileDAO.java] (getFileList) fileList : " + fileList);
		return fileList;
	} // getFileList();
	
	// 게시물의 상세 페이지를 호출
	public FileVO getFileView(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM DOWNLOAD_BOARD WHERE number = ?";
		
		FileVO fVo = null;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				fVo = new FileVO();
				fVo.setNumber(resultSet.getInt("number"));
				fVo.setId(resultSet.getString("id"));
				fVo.setName(resultSet.getString("name"));
				fVo.setCategory(resultSet.getString("category"));
				fVo.setSubject(resultSet.getString("subject"));
				fVo.setContent(resultSet.getString("content"));
				fVo.setAddress(resultSet.getString("address"));
				fVo.setCount(resultSet.getInt("count"));
				fVo.setRegdate(resultSet.getTimestamp("regdate"));
				fVo.setFile(resultSet.getString("file"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[FileDAO.java] (getFileView) fVo : " + fVo);
		return fVo;
	} // getFileView();
		
	// 게시물의 상세 페이지를 호출
	public List<FileVO> getFileViewVue(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM DOWNLOAD_BOARD WHERE number = ?";
		
		FileVO fVo = null;
		List<FileVO> fileView = new ArrayList<FileVO>();
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				fVo = new FileVO();
				fVo.setNumber(resultSet.getInt("number"));
				fVo.setId(resultSet.getString("id"));
				fVo.setName(resultSet.getString("name"));
				fVo.setCategory(resultSet.getString("category"));
				fVo.setSubject(resultSet.getString("subject"));
				fVo.setContent(resultSet.getString("content"));
				fVo.setAddress(resultSet.getString("address"));
				fVo.setCount(resultSet.getInt("count"));
				fVo.setRegdate(resultSet.getTimestamp("regdate"));
				fVo.setFile(resultSet.getString("file"));
				
				fileView.add(fVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return fileView;
	} // getFileViewVue();
	
	// 게시물을 등록
	@SuppressWarnings("resource")
	public FileVO setFileWirte(FileVO fVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		FileVO write = new FileVO();
		
		int number = 1;
		
		String sql = "SELECT MAX(number) FROM DOWNLOAD_BOARD";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[FileDAO.java] (setFileWirte) resultSet.getInt(1) : " + resultSet.getInt(1));
				
				number = resultSet.getInt(1) + 1;
				System.out.println("[FileDAO.java] (setFileWirte) if number = resultSet.getInt(1) + 1 : " + number);
			} else {
				number = 1;
				System.out.println("[FileDAO.java] (setFileWirte) else number = 1 : " + number);
			}
			
			sql = "INSERT INTO DOWNLOAD_BOARD(number, id, name, category, subject, content, reply_reference, regdate, file) values(?, ?, ?, ?, ?, ?, ?, now(), ?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, fVo.getId());
			preparedStatement.setString(3, fVo.getName());
			preparedStatement.setString(4, fVo.getCategory());
			preparedStatement.setString(5, fVo.getSubject());
			preparedStatement.setString(6, fVo.getContent());
			preparedStatement.setInt(7, number);
			preparedStatement.setString(8, fVo.getFile());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[FileDAO.java] (setFileWirte) write : " + write);
		
		return write;
	} // setFileWirte();
	
	// 게시물을 수정
	@SuppressWarnings("resource")
	public FileVO setFileModify(int number, FileVO fVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		FileVO write = new FileVO();
		
		String sql = "SELECT id FROM DOWNLOAD_BOARD WHERE number = ?";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (fVo.getId().equals(resultSet.getString("id"))) {
					
					System.out.println("[FileDAO.java] (setFileModify) fVo.getFile(): " + fVo.getFile());
					sql = "UPDATE DOWNLOAD_BOARD SET id = ?, name = ?, category = ?, subject = ?, content = ?, file = ? WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, fVo.getId());
					preparedStatement.setString(2, fVo.getName());
					preparedStatement.setString(3, fVo.getCategory());
					preparedStatement.setString(4, fVo.getSubject());
					preparedStatement.setString(5, fVo.getContent());
					preparedStatement.setString(6, fVo.getFile());
					preparedStatement.setInt(7, number);
					preparedStatement.executeUpdate();
				}
			} else {
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return write;
	} // setFileModify();
	
	// 게시물을 삭제
	@SuppressWarnings("resource")
	public int fileDelete(int number, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int result = -1;
		String sql = "SELECT id FROM DOWNLOAD_BOARD WHERE number = ?";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (id.equals(resultSet.getString("id"))) {
					sql = "DELETE FROM DOWNLOAD_BOARD WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, number);
					preparedStatement.executeUpdate();
					
					result = 1;
				}
			} else {
				result = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[FileDAO.java] (fileDelete) result : " + result);
		return result;
	} // fileDelete();
}
