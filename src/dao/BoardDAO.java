package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BoardVO;
import utility.Manager;

public class BoardDAO {
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 조회수 업데이트
	public void countUpdate(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "";
		
		try {
			System.out.println("[BoardDAO.java] (countUpdate) 조회 수를 업데이트합니다.");
			
			sql = "UPDATE BASIC_BOARD SET count = count + 1 WHERE number = " + number;
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
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
		
		String sql = "SELECT COUNT(*) FROM BASIC_BOARD";
		
		int result = -1;
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[BoardDAO] (getListCount) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%'";
		}
		
		System.out.println("[BoardDAO] (getListCount) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) result = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO] (getListCount) result : " + result);
		return result;
	} // getListCount();
	
	// 게시물의 목록을 추출
	public List<BoardVO> getBoardList(int currentPage, int limit, String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM BASIC_BOARD";
		
		// limit: 한 페이지에 노출할 게시물의 개수입니다.
		// int start = (currentPage - 1) * limit + 1; // 읽기 시작할 row 번호입니다.
		int start = (currentPage - 1) * limit; // 읽기 시작할 row 번호입니다.
		System.out.println("[BoardDAO.java] (getBoardList) start : " + start);
		
		// int end = start + limit - 1; // 읽을 마지막 row 번호입니다.
		int end = -1;
		
		System.out.println("[BoardDAO.java] (getNoticeList) currentPage == 1 : " + (currentPage == 1));
		if (currentPage == 1) {
			end = start + limit; // 읽을 마지막 row 번호입니다.
		} else {
			end = start; // 읽을 마지막 row 번호입니다.
		}
		
		System.out.println("[BoardDAO.java] (getBoardList) end : " + end);
		
		BoardVO bVo = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		System.out.println("[BoardDAO] (getBoardList) keyword: " + keyword);
		System.out.println("[BoardDAO] (getBoardList) option: " + option);
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[BoardDAO] (getBoardList) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%' ORDER BY number DESC limit ?, ?";
		} else {
			System.out.println("[BoardDAO] (getBoardList) 키워드가 존재하지 않아 진입하지 못하였습니다.");
			
			 // sql += " " + "ORDER BY number DESC limit ?, ?";
			sql += " " + "ORDER BY reply_reference DESC, reply_sequence ASC limit ?, ?";
		}
		
		System.out.println("[BoardDAO] (getBoardList) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, start - 1);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				bVo = new BoardVO();
				
				bVo.setNumber(resultSet.getInt("number"));
				bVo.setName(resultSet.getString("name"));
				bVo.setCategory(resultSet.getString("category"));
				bVo.setSubject(resultSet.getString("subject"));
				bVo.setContent(resultSet.getString("content"));
				bVo.setAddress(resultSet.getString("address"));
				bVo.setCount(resultSet.getInt("count"));
				bVo.setReplyReference(resultSet.getInt("reply_reference"));
				bVo.setReplyDepth(resultSet.getInt("reply_depth"));
				bVo.setReplySequence(resultSet.getInt("reply_sequence"));
				bVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				boardList.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (getNoticeList) boardList : " + boardList);
		return boardList;
	} // getBoardList();
	
	// 게시물의 상세 페이지를 호출
	public BoardVO getBoardView(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM BASIC_BOARD WHERE number = ?";
		
		BoardVO bVo = null;
		
		try {
			System.out.println("[BoardDAO.java] (getBoardView) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				bVo = new BoardVO();
				bVo.setNumber(resultSet.getInt("number"));
				bVo.setId(resultSet.getString("id"));
				bVo.setName(resultSet.getString("name"));
				bVo.setCategory(resultSet.getString("category"));
				bVo.setSubject(resultSet.getString("subject"));
				bVo.setContent(resultSet.getString("content"));
				bVo.setUri(resultSet.getString("uri"));
				bVo.setThumbnail(resultSet.getString("thumbnail"));
				bVo.setReplyReference(resultSet.getInt("reply_reference"));
				bVo.setReplyDepth(resultSet.getInt("reply_depth"));
				bVo.setReplySequence(resultSet.getInt("reply_sequence"));
				bVo.setAddress(resultSet.getString("address"));
				bVo.setCount(resultSet.getInt("count"));
				bVo.setRegdate(resultSet.getTimestamp("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (getBoardView) bVo : " + bVo);
		return bVo;
	} // getBoardView();
	
	// 게시물의 상세 페이지를 호출
	public List<BoardVO> getBoardViewVue(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM BASIC_BOARD WHERE number = ?";
		
		BoardVO bVo = null;
		List<BoardVO> boardView = new ArrayList<BoardVO>();
		
		try {
			System.out.println("[BoardDAO.java] (getBoardViewVue) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				bVo = new BoardVO();
				bVo.setNumber(resultSet.getInt("number"));
				bVo.setId(resultSet.getString("id"));
				bVo.setName(resultSet.getString("name"));
				bVo.setCategory(resultSet.getString("category"));
				bVo.setSubject(resultSet.getString("subject"));
				bVo.setContent(resultSet.getString("content"));
				bVo.setReplyReference(resultSet.getInt("reply_reference"));
				bVo.setReplyDepth(resultSet.getInt("reply_depth"));
				bVo.setReplySequence(resultSet.getInt("reply_sequence"));
				bVo.setAddress(resultSet.getString("address"));
				bVo.setCount(resultSet.getInt("count"));
				bVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				boardView.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (getBoardViewVue) boardView : " + boardView);
		return boardView;
	} // getBoardViewVue();
	
	// 게시물을 등록
	@SuppressWarnings("resource")
	public BoardVO setBoardWirte(BoardVO bVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		BoardVO write = new BoardVO();
		
		int number = 1;
		
		String sql = "SELECT MAX(number) FROM BASIC_BOARD";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[BoardDAO] (setBoardWirte) if resultSet.getInt(1) : " + resultSet.getInt(1));
				
				number = resultSet.getInt(1) + 1;
				System.out.println("[BoardDAO] (setBoardWirte) if number = resultSet.getInt(1) + 1 : " + number);
			} else {
				System.out.println("[BoardDAO] (setBoardWirte) else number = 1 : " + number);
			}
			
			sql = "INSERT INTO BASIC_BOARD(number, id, name, category, subject, content, uri, thumbnail, reply_reference, regdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, bVo.getId());
			preparedStatement.setString(3, bVo.getName());
			preparedStatement.setString(4, bVo.getCategory());
			preparedStatement.setString(5, bVo.getSubject());
			preparedStatement.setString(6, bVo.getContent());
			preparedStatement.setString(7, bVo.getUri());
			preparedStatement.setString(8, bVo.getThumbnail());
			preparedStatement.setInt(9, number);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[BoardDAO.java] (setBoardWirte) write : " + write);
		return write;
	} // setBoardWirte();
	
	// 게시물을 수정
	@SuppressWarnings("resource")
	public BoardVO setBoardModify(int number, BoardVO bVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		BoardVO write = new BoardVO();
		
		String sql = "SELECT id FROM BASIC_BOARD WHERE number = ?";
		
		try {
			System.out.println("[BoardDAO.java] (setBoardModify) 게시물 수정 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (bVo.getId().equals(resultSet.getString("id"))) {
					sql = "UPDATE BASIC_BOARD SET id = ?, name = ?, category = ?, subject = ?, content = ? WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, bVo.getId());
					preparedStatement.setString(2, bVo.getName());
					preparedStatement.setString(3, bVo.getCategory());
					preparedStatement.setString(4, bVo.getSubject());
					preparedStatement.setString(5, bVo.getContent());
					preparedStatement.setInt(6, number);
					preparedStatement.executeUpdate();
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (setBoardModify) write : " + write);
		return write;
	} // setBoardModify();
	
	// 게시물을 삭제
	@SuppressWarnings("resource")
	public int boardDelete(int number, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int result = -1;
		String sql = "SELECT id FROM BASIC_BOARD WHERE number = ?";
		
		System.out.println("[BoardDAO] (boardDelete) number: " + number);
		System.out.println("[BoardDAO] (boardDelete) id: " + id);
		
		try {
			System.out.println("[BoardDAO.java] (boardDelete) 게시물을 삭제합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (id.equals(resultSet.getString("id"))) {
					sql = "DELETE FROM BASIC_BOARD WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, number);
					preparedStatement.executeUpdate();
					
					result = 1;
				}
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (boardDelete) result : " + result);
		return result;
	} // boardDelete();
	
	// 게시물의 답변
	@SuppressWarnings("resource")
	public int boardReply(BoardVO bVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int number = 1;
		int result = -1;
		
		int replyReference = bVo.getReplyReference();
		int replyDepth = bVo.getReplyDepth();
		int replySequence = bVo.getReplySequence();
		
		String sql = "SELECT MAX(number) FROM BASIC_BOARD";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				number = resultSet.getInt(1) + 1;
			} else {
				
			}
			
			sql = "UPDATE BASIC_BOARD SET reply_sequence = reply_sequence + 1 WHERE reply_reference = ? AND reply_sequence > ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, replyReference);
			preparedStatement.setInt(2, replySequence);
			result = preparedStatement.executeUpdate();
			
			replySequence = replySequence + 1;
			replyDepth = replyDepth + 1;
			
			sql = "INSERT INTO BASIC_BOARD(number, id, name, category, subject, content, reply_reference, reply_depth, reply_sequence, regdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, bVo.getId());
			preparedStatement.setString(3, bVo.getName());
			preparedStatement.setString(4, bVo.getCategory());
			preparedStatement.setString(5, bVo.getSubject());
			preparedStatement.setString(6, bVo.getContent());
			preparedStatement.setInt(7, replyReference);
			preparedStatement.setInt(8, replyDepth);
			preparedStatement.setInt(9, replySequence);
			preparedStatement.executeUpdate();
			
			result = 1; 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[BoardDAO.java] (boardReply) result : " + result);
		return result;
	} // boardReply()
	
}
