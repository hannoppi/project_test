package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.ImageSlideVO;
import utility.Manager;

public class ImageSlideDAO {
	private ImageSlideDAO() {}
	
	private static ImageSlideDAO instance = new ImageSlideDAO();
	
	public static ImageSlideDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 게시물의 전체 개수를 추출
	public int getListCount(String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT COUNT(*) FROM IMAGE_SLIDE_BOARD";
		
		int result = -1;
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[ImageSlideDAO.java] (getListCount) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%'";
		}
		
		System.out.println("[ImageSlideDAO.java] (getListCount) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getInt(1);
				System.out.println("[ImageSlideDAO.java] (getListCount) result : " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	} // getListCount();
	
	// 이미지 슬라이드의 목록을 추출
	public List<ImageSlideVO> getImageSlideList(int currentPage, int limit, String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ImageSlideVO write = null;
		List<ImageSlideVO> imageSlideList = new ArrayList<ImageSlideVO>();
		
		String sql = "";

		try {			
			sql = "SELECT * FROM IMAGE_SLIDE_BOARD";
			System.out.println("[ImageSlideDAO.java] (getImageSlideList) sql: " + sql);
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				write = new ImageSlideVO();
				
				write.setNumber(resultSet.getInt("number"));
				write.setFile(resultSet.getString("file"));
				write.setSubject(resultSet.getString("subject"));
				write.setContent(resultSet.getString("content"));
				
				imageSlideList.add(write);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[ImageSlideDAO.java] (getImageSlideList) imageSlideList : " + imageSlideList);
		
		return imageSlideList;
	} // getImageSlideList()
	
	// 이미지 슬라이드를 등록
	@SuppressWarnings("resource")
	public ImageSlideVO setImageSlideWirte(ImageSlideVO sVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ImageSlideVO write = new ImageSlideVO();
		
		int number = 1;
		
		String sql = "SELECT MAX(number) FROM IMAGE_SLIDE_BOARD";

		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) if resultSet.next()");
				System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) if resultSet.getInt(1) : " + resultSet.getInt(1));
				
				number = resultSet.getInt(1) + 1;
				System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) if number = resultSet.getInt(1) + 1 : " + number);
			} else {
				System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) else resultSet.next()");
				
				number = 1;
				System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) else number = 1 : " + number);
			}
			
			sql = "INSERT INTO IMAGE_SLIDE_BOARD(number, file, subject, content, regdate) values("+ number + ", ?, ?, ?, now())";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, sVo.getFile());
			preparedStatement.setString(2, sVo.getSubject());
			preparedStatement.setString(3, sVo.getContent());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[ImageSlideDAO.java] (setImageSlideWirte) write : " + write);
		
		return write;
	} // setImageSlideWirte();
	
	// 이미지 슬라이드를 수정
	@SuppressWarnings("resource")
	public ImageSlideVO setImageSlideModify(ImageSlideVO sVo, int check) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ImageSlideVO write = new ImageSlideVO();
		
		System.out.println("[ImageSlideDAO.java] (setImageSlideModify) check: " + check);
		
		String sql = "SELECT * FROM IMAGE_SLIDE_BOARD WHERE number = ?";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, check);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[ImageSlideDAO.java] (setImageSlideModify) resultSet.next()");
				
				// if (sVo.getId().equals(resultSet.getString("id"))) {
					
					sql = "UPDATE IMAGE_SLIDE_BOARD SET file = ?, subject = ?, content = ? WHERE number = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, sVo.getFile());
					preparedStatement.setString(2, sVo.getSubject());
					preparedStatement.setString(3, sVo.getContent());
					preparedStatement.setInt(4, check);
					preparedStatement.executeUpdate();
				// }
			} else {
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[ImageSlideDAO.java] (setImageSlideModify) 종료");
		return write;
	} // setImageSlideModify();
}
