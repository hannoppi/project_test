package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.GalleryVO;
import utility.Manager;

public class GalleryDAO {
	private GalleryDAO() {}
	
	private static GalleryDAO instance = new GalleryDAO();
	
	public static GalleryDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 조회수 업데이트
	public void countUpdate(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "";
		
		try {
			System.out.println("[GalleryDAO.java] (countUpdate) 조회 수를 업데이트합니다.");
			
			sql = "UPDATE GALLERY_BOARD SET count = count + 1 WHERE number = " + number;
			
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
		
		String sql = "SELECT COUNT(*) FROM GALLERY_BOARD";
		
		int result = -1;
		
		if (keyword != null && !keyword.equals("")){
			System.out.println("[GalleryDAO] (getListCount) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%'";
		}
		
		System.out.println("[GalleryDAO] (getListCount) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getInt(1);
				System.out.println("[GalleryDAO] (getListCount) result : " + result);
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
	public List<GalleryVO> getGalleryList(int currentPage, int limit, String keyword, String option) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM GALLERY_BOARD";
		
		// limit: 한 페이지에 노출할 게시물의 개수입니다.
		// int start = (currentPage - 1) * limit + 1; // 읽기 시작할 row 번호입니다.
		int start = (currentPage - 1) * limit; // 읽기 시작할 row 번호입니다.
		System.out.println("[GalleryDAO.java] (getGalleryList) start : " + start);
		
		// int end = start + limit - 1; // 읽을 마지막 row 번호입니다.
		int end = -1;
		
		System.out.println("[GalleryDAO.java] (getGalleryList) currentPage == 1 : " + (currentPage == 1));
		if (currentPage == 1) {
			end = start + limit; // 읽을 마지막 row 번호입니다.
		} else {
			end = start; // 읽을 마지막 row 번호입니다.
		}
		
		System.out.println("[GalleryDAO.java] (getGalleryList) end : " + end);
		
		GalleryVO gVo = null;
		List<GalleryVO> galleryList = new ArrayList<GalleryVO>();
		
		System.out.println("[GalleryDAO] (getGalleryList) keyword: " + keyword);
		System.out.println("[GalleryDAO] (getGalleryList) option: " + option);
		
		if (keyword != null && !keyword.equals("")) {
			System.out.println("[GalleryDAO] (getGalleryList) 키워드가 존재하여 진입하였습니다.");
			
			sql += " " + "WHERE " + option.trim() + " LIKE '%" + keyword.trim() + "%' ORDER BY number DESC limit ?, ?";
		} else {
			System.out.println("[GalleryDAO] (getGalleryList) 키워드가 존재하지 않아 진입하지 못하였습니다.");
			
			 sql += " " + "ORDER BY number DESC limit ?, ?";
		}
		
		System.out.println("[GalleryDAO] (getGalleryList) sql: " + sql);
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, start - 1);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				gVo = new GalleryVO();
				
				gVo.setNumber(resultSet.getInt("number"));
				gVo.setName(resultSet.getString("name"));
				gVo.setCategory(resultSet.getString("category"));
				gVo.setSubject(resultSet.getString("subject"));
				gVo.setContent(resultSet.getString("content"));
				gVo.setAddress(resultSet.getString("address"));
				gVo.setCount(resultSet.getInt("count"));
				gVo.setRegdate(resultSet.getTimestamp("regdate"));
				gVo.setImage(resultSet.getString("image"));
				
				galleryList.add(gVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[GalleryDAO] (getGalleryList) galleryList : " + galleryList);
		return galleryList;
	} // getGalleryList();
	
	// 게시물의 상세 페이지를 호출
	public GalleryVO getGalleryView(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM GALLERY_BOARD WHERE number = ?";
		
		GalleryVO gVo = null;
		
		try {
			System.out.println("[GalleryDAO.java] (getGalleryView) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				gVo = new GalleryVO();
				gVo.setNumber(resultSet.getInt("number"));
				gVo.setId(resultSet.getString("id"));
				gVo.setName(resultSet.getString("name"));
				gVo.setCategory(resultSet.getString("category"));
				gVo.setSubject(resultSet.getString("subject"));
				gVo.setContent(resultSet.getString("content"));
				gVo.setAddress(resultSet.getString("address"));
				gVo.setCount(resultSet.getInt("count"));
				gVo.setRegdate(resultSet.getTimestamp("regdate"));
				gVo.setImage(resultSet.getString("image"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[GalleryDAO] (getGalleryView) gVo : " + gVo);
		return gVo;
	} // getGalleryView();
	
	// 게시물의 상세 페이지를 호출
	public List<GalleryVO> getGalleryViewVue(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM GALLERY_BOARD WHERE number = ?";
		
		GalleryVO gVo = null;
		List<GalleryVO> galleryView = new ArrayList<GalleryVO>();
		
		try {
			System.out.println("[GalleryDAO.java] (getGalleryViewVue) 게시물의 상세 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				gVo = new GalleryVO();
				gVo.setNumber(resultSet.getInt("number"));
				gVo.setId(resultSet.getString("id"));
				gVo.setName(resultSet.getString("name"));
				gVo.setCategory(resultSet.getString("category"));
				gVo.setSubject(resultSet.getString("subject"));
				gVo.setContent(resultSet.getString("content"));
				gVo.setAddress(resultSet.getString("address"));
				gVo.setCount(resultSet.getInt("count"));
				gVo.setRegdate(resultSet.getTimestamp("regdate"));
				gVo.setImage(resultSet.getString("image"));
				
				galleryView.add(gVo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[GalleryDAO.java] (getGalleryViewVue) galleryView : " + galleryView);
		return galleryView;
	} // getGalleryViewVue();
	
	// 게시물을 등록
	@SuppressWarnings("resource")
	public GalleryVO setGalleryWirte(GalleryVO gVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		GalleryVO write = new GalleryVO();
		
		int number = 1;
		
		String sql = "SELECT MAX(number) FROM GALLERY_BOARD";
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("[GalleryDAO] (setGalleryWirte) resultSet.getInt(1) : " + resultSet.getInt(1));
				
				number = resultSet.getInt(1) + 1;
				System.out.println("[GalleryDAO] (setGalleryWirte) number = resultSet.getInt(1) + 1 : " + number);
			} else {
				number = 1;
				System.out.println("[GalleryDAO] (setGalleryWirte) number = 1 : " + number);
			}
			
			sql = "INSERT INTO GALLERY_BOARD(number, id, name, category, subject, content, regdate, image) values("+ number + ", ?, ?, ?, ?, ?, now(), ?)";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, gVo.getId());
			preparedStatement.setString(2, gVo.getName());
			preparedStatement.setString(3, gVo.getCategory());
			preparedStatement.setString(4, gVo.getSubject());
			preparedStatement.setString(5, gVo.getContent());
			preparedStatement.setString(6, gVo.getImage());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[GalleryDAO.java] (setGalleryWirte) write : " + write);
		
		return write;
	} // setGalleryWirte();
	
	// 게시물을 수정
	@SuppressWarnings("resource")
	public GalleryVO setGalleryModify(int number, GalleryVO gVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		GalleryVO write = new GalleryVO();
		
		String sql = "SELECT id FROM GALLERY_BOARD WHERE number = ?";
		
		try {
			System.out.println("[GalleryDAO.java] (setGalleryModify) 게시물 수정 페이지를 호출합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (gVo.getId().equals(resultSet.getString("id"))) {
					sql = "UPDATE GALLERY_BOARD SET id = ?, name = ?, category = ?, subject = ?, content = ?, image = ? WHERE number = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, gVo.getId());
					preparedStatement.setString(2, gVo.getName());
					preparedStatement.setString(3, gVo.getCategory());
					preparedStatement.setString(4, gVo.getSubject());
					preparedStatement.setString(5, gVo.getContent());
					preparedStatement.setString(6, gVo.getImage());
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
		
		System.out.println("[GalleryDAO.java] (setGalleryModify) write : " + write);
		return write;
	} // setGalleryModify();
	
	// 게시물을 삭제
	@SuppressWarnings("resource")
	public int galleryDelete(int number, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int result = -1;
		String sql = "SELECT id FROM GALLERY_BOARD WHERE number = ?";
		
		try {
			System.out.println("[GalleryDAO.java] (galleryDelete) 게시물을 삭제합니다.");
			
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (id.equals(resultSet.getString("id"))) {
					sql = "DELETE FROM GALLERY_BOARD WHERE number = ?";
					
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
		
		System.out.println("[GalleryDAO.java] (galleryDelete) result : " + result);
		return result;
	} // galleryDelete();
	
}
