package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crypt.BCrypt;
import crypt.SHA256;
import dto.MemberVO;
import utility.Manager;

public class MemberDAO {
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	} // getInstance();
	
	// 로그인
	public int login(String id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT name, password FROM MEMBER WHERE id = ?";
		
		int result = -1;
		
		try {
			connection = Manager.getConnection();
			
			String originalPassword = password;
			// System.out.println("[MemberDAO.java] (login) originalPassword: " + originalPassword);
			
			System.out.println("[MemberDAO.java] (login) originalPassword.getBytes(): " + originalPassword.getBytes());
			
			String shaPassword = sha.getSha256(originalPassword.getBytes());
			System.out.println("[MemberDAO.java] (login) shaPassword: " + shaPassword);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				/*
				if (password.equals(resultSet.getString("password"))) {
					System.out.println(resultSet.getString("name") + "님께서 로그인하였습니다.");
					
					result = 1;
				} else {
					System.out.println("패스워드가 일치하지 않습니다.");
					
					result = 0;
				}
				*/
				
				String dataBasePassword = resultSet.getString("password");
				System.out.println("[MemberDAO.java] (login) dataBasePassword: " + dataBasePassword);
				
				if (BCrypt.checkpw(shaPassword, dataBasePassword)) {
					System.out.println("[MemberDAO.java] (login) " + resultSet.getString("name") + "님께서 로그인하였습니다.");
					
					result = 1;
				} else {
					System.out.println("[MemberDAO.java] (login) 패스워드가 일치하지 않습니다.");
					
					result = 0;
				}
			} else {
				System.out.println("[MemberDAO.java] (login) 아이디가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	} // login();
	
	// 회원정보 조회
	public MemberVO inquiry(String id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT * FROM MEMBER WHERE id = ?";
		
		MemberVO mVo = null;
		
		try {
			connection = Manager.getConnection();
			
			String originalPassword = password;
			// System.out.println("[MemberDAO.java] (inquiry) originalPassword: " + originalPassword);
			
			System.out.println("[MemberDAO.java] (inquiry) originalPassword.getBytes(): " + originalPassword.getBytes());
			
			String shaPassword = sha.getSha256(originalPassword.getBytes());
			System.out.println("[MemberDAO.java] (inquiry) shaPassword: " + shaPassword);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				// if (id.equals(resultSet.getString("id")) && password.equals(resultSet.getString("password"))) {
				
				String dataBasePassword = resultSet.getString("password");
				System.out.println("[MemberDAO.java] (inquiry) dataBasePassword: " + dataBasePassword);
				
				if (BCrypt.checkpw(shaPassword, dataBasePassword)) {
					mVo = new MemberVO();
					
					mVo.setId(resultSet.getString("id"));
					mVo.setPassword(resultSet.getString("password"));
					mVo.setQuiz(resultSet.getString("quiz"));
					mVo.setName(resultSet.getString("name"));
					mVo.setBirthday(resultSet.getString("birthday"));
					mVo.setTel(resultSet.getString("tel"));
					mVo.setPhone(resultSet.getString("phone"));
					mVo.setPostcode(resultSet.getString("postcode"));
					mVo.setAddress(resultSet.getString("address"));
					mVo.setEmail(resultSet.getString("email"));
					mVo.setLevel(resultSet.getInt("level"));
					mVo.setRegdate(resultSet.getTimestamp("regdate"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[MemberDAO.java] (inquiry) mVo: " + mVo);
		return mVo;
	} // inquiry();
	
	public List<MemberVO> inquiryVue(String id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT * FROM MEMBER WHERE id = ?";
		
		MemberVO mVo = null;
		List<MemberVO> result = new ArrayList<MemberVO>();
		
		try {
			connection = Manager.getConnection();
			
			String originalPassword = password;
			// System.out.println("[MemberDAO.java] (inquiry) originalPassword: " + originalPassword);
			
			System.out.println("[MemberDAO.java] (inquiryVue) originalPassword.getBytes(): " + originalPassword.getBytes());
			
			String shaPassword = sha.getSha256(originalPassword.getBytes());
			System.out.println("[MemberDAO.java] (inquiryVue) shaPassword: " + shaPassword);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				// if (id.equals(resultSet.getString("id")) && password.equals(resultSet.getString("password"))) {
				
				String dataBasePassword = resultSet.getString("password");
				System.out.println("[MemberDAO.java] (inquiryVue) dataBasePassword: " + dataBasePassword);
				
				if (BCrypt.checkpw(shaPassword, dataBasePassword)) {
					mVo = new MemberVO();
					
					mVo.setId(resultSet.getString("id"));
					mVo.setPassword(resultSet.getString("password"));
					mVo.setQuiz(resultSet.getString("quiz"));
					mVo.setName(resultSet.getString("name"));
					mVo.setBirthday(resultSet.getString("birthday"));
					mVo.setTel(resultSet.getString("tel"));
					mVo.setPhone(resultSet.getString("phone"));
					mVo.setPostcode(resultSet.getString("postcode"));
					mVo.setAddress(resultSet.getString("address"));
					mVo.setEmail(resultSet.getString("email"));
					mVo.setLevel(resultSet.getInt("level"));
					mVo.setRegdate(resultSet.getTimestamp("regdate"));
					
					result.add(mVo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[MemberDAO.java] (inquiryVue) result: " + result);
		return result;
	} // inquiryVue();
	
	// 회원가입
	public Boolean insert(MemberVO mVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "INSERT INTO MEMBER(id, password, quiz, name, birthday, postcode, tel, phone, email, address, regdate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		
		Boolean result = false;
		
		try {
			connection = Manager.getConnection();
			
			String originalPassword = mVo.getPassword();
			// System.out.println("[MemberDAO.java] (insert) originalPassword: " + originalPassword);
			
			System.out.println("[MemberDAO.java] (insert) originalPassword.getBytes(): " + originalPassword.getBytes());
			
			String shaPassword = sha.getSha256(originalPassword.getBytes());
			System.out.println("[MemberDAO.java] (insert) shaPassword: " + shaPassword);
			
			String bcrypt = BCrypt.hashpw(shaPassword, BCrypt.gensalt());
			System.out.println("[MemberDAO.java] (insert) bcrypt: " + bcrypt);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mVo.getId());
			// preparedStatement.setString(2, mVo.getPassword());
			preparedStatement.setString(2, bcrypt);
			preparedStatement.setString(3, mVo.getQuiz());
			preparedStatement.setString(4, mVo.getName());
			preparedStatement.setString(5, mVo.getBirthday());
			preparedStatement.setString(6, mVo.getPostcode());
			preparedStatement.setString(7, mVo.getTel());
			preparedStatement.setString(8, mVo.getPhone());
			preparedStatement.setString(9, mVo.getEmail());
			// preparedStatement.setInt(10, mVo.getLevel());
			preparedStatement.setString(10, mVo.getAddress());
			// preparedStatement.setTimestamp(12, mVo.getRegdate());
			preparedStatement.executeUpdate();
			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		return result;
	} // insert()
	
	// 아이디 중복확인
	public int confirmId(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT id FROM member WHERE id = ?";
		
		int result = -1;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[MemberDAO.java] (confirmId): " + result);
		return result;
	} // confirmId()
	
	// 아이디 찾기
	public MemberVO findId(String name, String birthday, String phone) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM MEMBER WHERE name = ? AND birthday = ? AND phone = ?";
		System.out.println("[MemberDAO.java] (findId) sql: " + sql);
		
		MemberVO mVo = null;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, birthday);
			preparedStatement.setString(3, phone);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				mVo = new MemberVO();
				
				mVo.setId(resultSet.getString("id"));
				mVo.setRegdate(resultSet.getTimestamp("regdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		return mVo;
	} // findId()
	
	// 패스워드 변경을 위한 사용자 검증
	public MemberVO validation(String id, String quiz) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM MEMBER WHERE id = ? AND quiz = ?";
		System.out.println("[MemberDAO.java] (validation) sql: " + sql);
		
		MemberVO mVo = null;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, quiz);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				mVo = new MemberVO();
				
				mVo.setId(resultSet.getString("id"));
				mVo.setPassword(resultSet.getString("password"));
				mVo.setName(resultSet.getString("name"));
				mVo.setRegdate(resultSet.getTimestamp("regdate"));
				
				System.out.println("[MemberDAO.java] (validation) 패스워드를 변경할 아이디를 성공적으로 검증하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		System.out.println("[MemberDAO.java] (validation) mVo: " + mVo);
		return mVo;
	} // validation()
	
	// 패스워드 변경
	@SuppressWarnings("resource")
	public Boolean changePassword(String id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT id FROM member WHERE id = ?";
		System.out.println("[MemberDAO.java] (changePassword) sql: " + sql);
		
		Boolean result = false;
		
		try {
			connection = Manager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String originalPassword = password;
				System.out.println("[MemberDAO.java] (changePassword) originalPassword: " + originalPassword);
				
				System.out.println("[MemberDAO.java] (changePassword) originalPassword.getBytes(): " + originalPassword.getBytes());
				
				String shaPassword = sha.getSha256(originalPassword.getBytes());
				System.out.println("[MemberDAO.java] (changePassword) shaPassword: " + shaPassword);
				
				String bcrypt = BCrypt.hashpw(shaPassword, BCrypt.gensalt());
				System.out.println("[MemberDAO.java] (changePassword) bcrypt: " + bcrypt);
				
				 sql = "UPDATE member SET password = ? WHERE id = ?";
				 
				 preparedStatement = connection.prepareStatement(sql);
				 preparedStatement.setString(1, bcrypt);
				 preparedStatement.setString(2, id);
				 preparedStatement.executeUpdate();
				 
				 result = true;
				 
				 System.out.println("[MemberDAO.java] (changePassword) 패스워드를 성공적으로 변경하였습니다.");
			} else {
				System.out.println("[MemberDAO.java] (changePassword) 패스워드 변경에 실패하였습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement);
		}
		
		return result;
	} // changePassword()
	
	// 회원정보 수정
	@SuppressWarnings("resource")
	public Boolean modify(MemberVO mVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT password FROM member WHERE id = ?";
		
		Boolean result = false;
		
		try {
			connection = Manager.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mVo.getId());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String originalPassword = mVo.getPassword();
				// System.out.println("[MemberDAO.java] (insert) originalPassword: " + originalPassword);
				
				System.out.println("[MemberDAO.java] (insert) originalPassword.getBytes(): " + originalPassword.getBytes());
				
				String shaPassword = sha.getSha256(originalPassword.getBytes());
				System.out.println("[MemberDAO.java] (insert) shaPassword: " + shaPassword);
				
				String bcrypt = BCrypt.hashpw(shaPassword, BCrypt.gensalt());
				System.out.println("[MemberDAO.java] (insert) bcrypt: " + bcrypt);
				
				// if (BCrypt.checkpw(shaPassword, dataBasePassword)) {
					sql = "UPDATE member SET password = ?, quiz = ?, birthday = ?, tel = ?, phone = ?, postcode = ?, email = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, bcrypt);
					preparedStatement.setString(2, mVo.getQuiz());
					preparedStatement.setString(3, mVo.getBirthday());
					preparedStatement.setString(4, mVo.getTel());
					preparedStatement.setString(5, mVo.getPhone());
					preparedStatement.setString(6, mVo.getPostcode());
					preparedStatement.setString(7, mVo.getEmail());
					preparedStatement.executeUpdate();
					
					result = true;
					
					System.out.println("[MemberDAO.java] (modify) 회원정보 수정에 성공하였습니다.");
				// } else {
					// System.out.println("[MemberDAO.java] (modify) 회원정보 수정에 실패하였습니다.");
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	} // modify()
	
	// 회원탈퇴
	@SuppressWarnings("resource")
	public int delete(String id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		String sql = "SELECT password FROM member WHERE id = ?";
		System.out.println("[MemberDAO.java] (delete) sql: " + sql);
		
		int result = -1;
		
		try {
			connection = Manager.getConnection();
			
			String originalPassword = password;
			// System.out.println("[MemberDAO.java] (insert) originalPassword: " + originalPassword);
			
			System.out.println("[MemberDAO.java] (delete) originalPassword.getBytes(): " + originalPassword.getBytes());
			
			String shaPassword = sha.getSha256(originalPassword.getBytes());
			System.out.println("[MemberDAO.java] (delete) shaPassword: " + shaPassword);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String dataBasePassword = resultSet.getString("password");
				System.out.println("[MemberDAO.java] (delete) dataBasePassword: " + dataBasePassword);
				
				if (BCrypt.checkpw(shaPassword, dataBasePassword)) {
					sql = "DELETE FROM member WHERE id = ?";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, id);
					preparedStatement.executeUpdate();
					
					result = 1;
					
					System.out.println("[MemberDAO.java] (delete) 회원탈퇴가 성공적으로 처리되었습니다.");
				} else {
					System.out.println("[MemberDAO.java] (delete) 회원탈퇴에 실패하였습니다..");
				}
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			Manager.close(connection, preparedStatement, resultSet);
		}
		
		System.out.println("[MemberDAO.java] (delete) result: " + result);
		return result;
	} // delete()
}