package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeVO;
import utility.Manager;

public class NoticeDAO {
	private NoticeDAO() {}
	
	private static NoticeDAO instance = new NoticeDAO();
	
	public static NoticeDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 조회수 업데이트
	public void countUpdate(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "";
		
		try {
			System.out.println("[NoticeDAO.java] (countUpdate) 조회 수를 업데이트합니다.");
			
			sql = "UPDATE NOTICE_BOARD SET count = count + 1 WHERE number = " + number;
			
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
		
		String sql = "SELECT COUNT(*) FROM NOTICE_BOARD";
		
		int result = -1;
		
		if (keyword != null && !keyword.equals("")) {
			System.out.println("[NoticeDAO.java] (getListCount) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%'";
		}
		
		System.out.println("[NoticeDAO.java] (getListCount) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) result = resultSet.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[NoticeDAO.java] (getListCount) result : " + result);
		return result;
	} // getListCount();
	
	// 게시물의 목록을 추출
	public List<NoticeVO> getNoticeList(int currentPage, int limit, String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM NOTICE_BOARD";
		
		// limit: 한 페이지에 노출할 게시물의 개수입니다.
		// int start = (currentPage - 1) * limit + 1; // 읽기 시작할 row 번호입니다.
		int start = (currentPage - 1) * limit; // 읽기 시작할 row 번호입니다.
		System.out.println("[NoticeDAO.java] (getNoticeList) start : " + start);
		
		// int end = start + limit - 1; // 읽을 마지막 row 번호입니다.
		int end = -1;
		
		System.out.println("[NoticeDAO.java] (getNoticeList) currentPage == 1 : " + (currentPage == 1));
		if (currentPage == 1) {
			end = start + limit; // 읽을 마지막 row 번호입니다.
		} else {
			end = start; // 읽을 마지막 row 번호입니다.
		}
		
		System.out.println("[NoticeDAO.java] (getNoticeList) end : " + end);
		
		NoticeVO nVo = null;
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		
		System.out.println("[NoticeDAO.java] (getNoticeList) keyword: " + keyword);
		System.out.println("[NoticeDAO.java] (getNoticeList) option: " + option);
		
		if (keyword != null && !keyword.equals("")) {
			System.out.println("[NoticeDAO.java] (getNoticeList) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%' ORDER BY number DESC limit ?, ?";
		} else {
			System.out.println("[NoticeDAO.java] (getNoticeList) 키워드가 존재하지 않아 진입하지 못하였습니다.");
			
			 sql += " " + "ORDER BY number DESC limit ?, ?";
		}
		
		System.out.println("[NoticeDAO.java] (getNoticeList) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				nVo = new NoticeVO();
				
				nVo.setNumber(resultSet.getInt("number"));
				nVo.setName(resultSet.getString("name"));
				nVo.setCategory(resultSet.getString("category"));
				nVo.setSubject(resultSet.getString("subject"));
				nVo.setContent(resultSet.getString("content"));
				nVo.setAddress(resultSet.getString("address"));
				nVo.setCount(resultSet.getInt("count"));
				nVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				noticeList.add(nVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[NoticeDAO.java] (getNoticeList) noticeList : " + noticeList);
		return noticeList;
	} // getNoticeList();
	
	// 게시물의 상세 페이지를 호출
	public NoticeVO getNoticeView(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM NOTICE_BOARD WHERE number = ?";
		
		NoticeVO nVo = null;
		
		try {
			System.out.println("[NoticeDAO.java] (getNoticeView) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				nVo = new NoticeVO();
				nVo.setNumber(resultSet.getInt("number"));
				nVo.setId(resultSet.getString("id"));
				nVo.setName(resultSet.getString("name"));
				nVo.setCategory(resultSet.getString("category"));
				nVo.setSubject(resultSet.getString("subject"));
				nVo.setContent(resultSet.getString("content"));
				nVo.setAddress(resultSet.getString("address"));
				nVo.setCount(resultSet.getInt("count"));
				nVo.setRegdate(resultSet.getTimestamp("regdate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[NoticeDAO.java] (getNoticeView) nVo : " + nVo);
		return nVo;
	} // getNoticeView();
	
	// 게시물의 상세 페이지를 호출
	public List<NoticeVO> getNoticeViewVue(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM NOTICE_BOARD WHERE number = ?";
		
		NoticeVO nVo = null;
		List<NoticeVO> noticeView = new ArrayList<NoticeVO>();
		
		try {
			System.out.println("[NoticeDAO.java] (getNoticeViewVue) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				nVo = new NoticeVO();
				nVo.setNumber(resultSet.getInt("number"));
				nVo.setId(resultSet.getString("id"));
				nVo.setName(resultSet.getString("name"));
				nVo.setCategory(resultSet.getString("category"));
				nVo.setSubject(resultSet.getString("subject"));
				nVo.setContent(resultSet.getString("content"));
				nVo.setFile(resultSet.getString("file"));
				nVo.setAddress(resultSet.getString("address"));
				nVo.setCount(resultSet.getInt("count"));
				nVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				noticeView.add(nVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (getNoticeView) noticeView : " + noticeView);
		return noticeView;
	} // getNoticeView();
	
	// 게시물을 등록
	@SuppressWarnings("resource")
	public NoticeVO setNoticeWirte(NoticeVO nVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		NoticeVO write = new NoticeVO();
		
		int number = 1;
		
		String sql = "SELECT MAX(number) FROM NOTICE_BOARD";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[NoticeDAO.java] (setNoticeWirte) if resultSet.getInt(1) : " + resultSet.getInt(1));
				
				number = resultSet.getInt(1) + 1;
				System.out.println("[NoticeDAO.java] (setNoticeWirte) if number = resultSet.getInt(1) + 1 : " + number);
			} else {
				number = 1;
				System.out.println("[NoticeDAO.java] (setNoticeWirte) else number = 1 : " + number);
			}
			
			sql = "INSERT INTO NOTICE_BOARD(number, id, name, category, subject, content, reply_reference, regdate, file) values(?, ?, ?, ?, ?, ?, ?, now(), ?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, nVo.getId());
			preparedStatement.setString(3, nVo.getName());
			preparedStatement.setString(4, nVo.getCategory());
			preparedStatement.setString(5, nVo.getSubject());
			preparedStatement.setString(6, nVo.getContent());
			preparedStatement.setInt(7, number);
			preparedStatement.setString(8, nVo.getFile());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[NoticeDAO.java] (setNoticeWirte) write : " + write);
		return write;
	} // setNoticeWirte();
	
	// 게시물을 수정
	@SuppressWarnings("resource")
	public NoticeVO setNoticeModify(int number, NoticeVO nVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		NoticeVO write = new NoticeVO();
		
		String sql = "SELECT id FROM NOTICE_BOARD WHERE number = ?";
		
		try {
			System.out.println("[NoticeDAO.java] (setNoticeModify) 게시물 수정 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (nVo.getId().equals(resultSet.getString("id"))) {
					sql = "UPDATE NOTICE_BOARD SET id = ?, name = ?, category = ?, subject = ?, content = ? WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, nVo.getId());
					preparedStatement.setString(2, nVo.getName());
					preparedStatement.setString(3, nVo.getCategory());
					preparedStatement.setString(4, nVo.getSubject());
					preparedStatement.setString(5, nVo.getContent());
					preparedStatement.setInt(6, number);
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
		
		System.out.println("[NoticeDAO.java] (setNoticeModify) write : " + write);
		return write;
	} // setNoticeModify();
	
	// 게시물을 삭제
	@SuppressWarnings("resource")
	public int noticeDelete(int number, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int result = -1;
		String sql = "SELECT id FROM NOTICE_BOARD WHERE number = ?";
		
		System.out.println("[NoticeDAO.java] (noticeDelete) number: " + number);
		System.out.println("[NoticeDAO.java] (noticeDelete) id: " + id);
		
		try {
			System.out.println("[NoticeDAO.java] (noticeDelete) 게시물을 삭제합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (id.equals(resultSet.getString("id"))) {
					sql = "DELETE FROM NOTICE_BOARD WHERE number = ?";
					
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
		
		System.out.println("[NoticeDAO.java] (noticeDelete) result : " + result);
		return result;
	} // noticeDelete();
	
}

