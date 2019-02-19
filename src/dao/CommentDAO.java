package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.CommentVO;
import utility.Manager;

public class CommentDAO {
	private CommentDAO() {}
	
	private static CommentDAO instance = new CommentDAO();
	
	public static CommentDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 댓글의 목록을 추출
	public ArrayList<CommentVO> select(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM COMMENT_BOARD WHERE board = ? ORDER BY together ASC, sequence ASC";
		
		CommentVO cVo = null;
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				cVo = new CommentVO();
				
				cVo.setBoard(resultSet.getInt("board"));
				cVo.setNumber(resultSet.getInt("number"));
				cVo.setId(resultSet.getString("id"));
				cVo.setName(resultSet.getString("name"));
				cVo.setContent(resultSet.getString("content"));
				cVo.setReference(resultSet.getInt("reference"));
				cVo.setSequence(resultSet.getInt("sequence"));
				cVo.setLevel(resultSet.getInt("level"));
				cVo.setTogether(resultSet.getInt("together"));
				cVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				commentList.add(cVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[CommentDAO] (select) commentList: " + commentList);
		return commentList;
	} // select
	
	// 댓글을 등록
	@SuppressWarnings("resource")
	public List<CommentVO> comment(CommentVO cVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "";
		
		sql = "SELECT COUNT(number),";
		sql += "(SELECT COUNT(sequence) FROM COMMENT_BOARD WHERE number = ? AND board= ?)"; // 댓글 번호에 해당하는 댓글 순서 개수 값을 추출합니다.
		sql += "FROM COMMENT_BOARD";
		
		CommentVO result = null;
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();

		int reNum = 0;
		int reSeq = 0;
		int reTogether = 0;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getNumber());
			preparedStatement.setInt(2, cVo.getBoard());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				reNum = resultSet.getInt(1) + 1;
				System.out.println("[CommentDAO] (comment) reNum: " + reNum);
				
				reTogether = resultSet.getInt(1) + 1;
				System.out.println("[CommentDAO] (comment) reTogether: " + reTogether);
				
				reSeq = resultSet.getInt(2) + 1;
				System.out.println("[CommentDAO] (comment) reSeq: " + reSeq);
			}
			
			sql = "INSERT INTO COMMENT_BOARD(board, number, id, name, content, reference, sequence, level, together, regdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getBoard());
			preparedStatement.setInt(2, reNum);
			preparedStatement.setString(3, cVo.getId());
			preparedStatement.setString(4, cVo.getName());
			preparedStatement.setString(5, cVo.getContent());
			preparedStatement.setInt(6, 0);
			preparedStatement.setInt(7, reSeq);
			preparedStatement.setInt(8, cVo.getLevel());
			preparedStatement.setInt(9, reTogether);
			preparedStatement.executeUpdate();
			
			sql = "SELECT * FROM COMMENT_BOARD WHERE board = ? ORDER BY number DESC LIMIT 1";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getBoard());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = new CommentVO();
				
				result.setBoard(resultSet.getInt("board"));
				result.setNumber(resultSet.getInt("number"));
				result.setId(resultSet.getString("id"));
				result.setName(resultSet.getString("name"));
				result.setContent(resultSet.getString("content"));
				result.setReference(resultSet.getInt("reference"));
				result.setSequence(resultSet.getInt("sequence"));
				result.setLevel(resultSet.getInt("level"));
				result.setTogether(resultSet.getInt("together"));
				result.setRegdate(resultSet.getTimestamp("regdate"));
				
				commentList.add(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[CommentDAO] (comment) commentList: " + commentList);
		return commentList;
	} // comment
	
	@SuppressWarnings("resource")
	public List<CommentVO> reply(CommentVO cVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "";
		
		sql += "SELECT";
		sql += "(SELECT COUNT(number) FROM COMMENT_BOARD WHERE board = ?),"; // 1. 현재 댓글 개수를 추출합니다. (다음 댓글 번호를 할당할 때 사용합니다.)
		sql += "(SELECT reference FROM COMMENT_BOARD WHERE number = ? AND board = ?),"; // 2. number에 해당하는 reference 값을 추출합니다.
		sql += "(SELECT together FROM COMMENT_BOARD WHERE number = ? AND board = ?),"; // 3. number에 해당하는 together 값을 추출합니다.
		sql += "(SELECT sequence FROM COMMENT_BOARD WHERE number = ? AND board = ?),"; // 4. number에 해당하는 sequence 값을 추출합니다.
		sql += "(SELECT level FROM COMMENT_BOARD WHERE number = ? AND board = ?)"; // 5. number에 해당하는 level 값을 추출합니다.
		sql += "FROM COMMENT_BOARD WHERE number = ? AND board = ?;"; // number에 해당하는 값을 추출합니다.
		
		CommentVO result = null;
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		
		int reNum = 0;
		int reRef = 0;
		int reTogether = 0;
		int reSeq = 0;
		int reLev = 0;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getBoard());
			preparedStatement.setInt(2, cVo.getNumber());
			preparedStatement.setInt(3, cVo.getBoard());
			preparedStatement.setInt(4, cVo.getNumber());
			preparedStatement.setInt(5, cVo.getBoard());
			preparedStatement.setInt(6, cVo.getNumber());
			preparedStatement.setInt(7, cVo.getBoard());
			preparedStatement.setInt(8, cVo.getNumber());
			preparedStatement.setInt(9, cVo.getBoard());
			preparedStatement.setInt(10, cVo.getNumber());
			preparedStatement.setInt(11, cVo.getBoard());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				reNum = resultSet.getInt(1);
				System.out.println("1. [CommentDAO] (reply) reNum: " + reNum);
				
				reRef = resultSet.getInt(2);
				System.out.println("2. [CommentDAO] (reply) reRef: " + reRef);
				
				reTogether = resultSet.getInt(3);
				System.out.println("3. [CommentDAO] (reply) reTogether: " + reTogether);
				
				reSeq = resultSet.getInt(4);
				System.out.println("4. [CommentDAO] (reply) reSeq: " + reSeq);
				
				reLev = resultSet.getInt(5);
				System.out.println("5. [CommentDAO] (reply) reLev: " + reLev);
			}
			
			sql = "UPDATE COMMENT_BOARD SET sequence = sequence + 1 WHERE together = ? AND sequence > ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reTogether);
			preparedStatement.setInt(2, reSeq);
			preparedStatement.executeUpdate();
			
			sql = "INSERT INTO COMMENT_BOARD(board, number, id, name, content, reference, sequence, level, together, regdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getBoard());
			preparedStatement.setInt(2, reNum + 1);
			preparedStatement.setString(3, cVo.getId());
			preparedStatement.setString(4, cVo.getName());
			preparedStatement.setString(5, cVo.getContent());
			preparedStatement.setInt(6, reRef + 1);
			preparedStatement.setInt(7, reSeq + 1);
			preparedStatement.setInt(8, reLev + 1);
			preparedStatement.setInt(9, reTogether);
			preparedStatement.executeUpdate();
			
			sql = "SELECT * FROM COMMENT_BOARD WHERE board = ? ORDER BY number DESC LIMIT 1";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cVo.getBoard());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = new CommentVO();
				
				result.setBoard(resultSet.getInt("board"));
				result.setNumber(resultSet.getInt("number"));
				result.setId(resultSet.getString("id"));
				result.setName(resultSet.getString("name"));
				result.setContent(resultSet.getString("content"));
				result.setReference(resultSet.getInt("reference"));
				result.setSequence(resultSet.getInt("sequence"));
				result.setLevel(resultSet.getInt("level"));
				result.setTogether(resultSet.getInt("together"));
				result.setRegdate(resultSet.getTimestamp("regdate"));
				
				commentList.add(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[CommentDAO] (reply) commentList: " + commentList);
		return commentList;
	} // reply
}
