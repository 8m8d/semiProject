package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.ApprovalVO;
import vo.BoardVO;
import vo.EmployeeVO;
import vo.MailVO;

public class MailDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
	String user = "userhj";
	String password = "user12";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public MailDAO() {

		try {
			// 2.드라이버로딩
			Class.forName(driver);
			// 3.Connection
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
	}

	// 전체 페이징 처리
	public int getTotalCount() {
		sb.setLength(0);
		sb.append("SELECT COUNT(*) FROM MAIL");
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<MailVO> selectAllRecive(int startNo, int endNo, int number) {
		// 최근 데이터 10개만 가져오기
		ArrayList<MailVO> list = new ArrayList<>();
		sb.setLength(0);

		/* sb.append("SELECT * FROM MAIL"); */

		sb.append(
				"select rn, m_number, m_title, m_content, m_file, m_cc, m_regdate, m_wtype, m_rtype, e_number2, e_number ");
		sb.append(
				"from ( select rownum rn, m_number,  m_title, m_content, m_file, m_cc, m_regdate, m_wtype, m_rtype, e_number2, e_number ");
		sb.append(
				"from ( select m_number, m_title, m_content, m_file, m_cc, m_regdate, m_wtype, m_rtype, e_number2, e_number ");
		sb.append("from mail where e_number= ? ");
		sb.append("order by m_number desc ) ");
		sb.append("where rownum <= ?) ");
		sb.append("where rn >= ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, number);
			pstmt.setInt(2, endNo);
			pstmt.setInt(3, startNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int mNumber = rs.getInt("M_Number");
				String mTitle = rs.getString("m_Title");
				String mContent = rs.getString("m_Content");
				String mFile = rs.getString("m_File");
				String mCc = rs.getString("m_Cc");
				Date mRegdate = rs.getDate("m_Regdate");
				int mWType = rs.getInt("m_wtype");
				int mRType = rs.getInt("m_rtype");

				int eNumber = rs.getInt("e_Number");
				int eNumber2 = rs.getInt("e_Number2");

				MailVO vo = new MailVO(mNumber, mTitle, mContent, mFile, mCc, mRegdate, mWType, mRType, eNumber,
						eNumber2);
				/*
				 * MailVO vo = new MailVO(); vo.setMNumber(mNumber); vo.setMTitle(mTitle);
				 * vo.setMContent(mContent); vo.setMFile(mFile); vo.setMCc(mCc);
				 * vo.setMRegdate(mRegdate); vo.setMBoard(mBoard); vo.setENumber(eNumber);
				 * vo.setENumber(eNumber2);
				 */

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	

	public void writeMail(MailVO vo) {
		sb.setLength(0);

		sb.append("INSERT INTO MAIL VALUES(MAIL_SEQ.nextval, ?, ?, ?, ?, sysdate, ?, ?, ?, ?) ");
		// 상태정보 : 1 정살글
		// 2 블라인드처리
		// 3 경찰요청
		try {
			pstmt = conn.prepareStatement(sb.toString());

			// 메일번호는 시퀀스번호로
			// pstmt.setInt(1, vo.getMNumber());

			// 2타이틀
			pstmt.setString(1, vo.getMTitle());
			// 3컨텐츠
			pstmt.setString(2, vo.getMContent());
			// 4파일
			pstmt.setString(3, vo.getMFile());
			// 5참조
			pstmt.setString(4, vo.getMCc());
			// 6.regdate

			// 7. mBoardType
			pstmt.setInt(5, vo.getMWType());
			// 8. mBoardType
			pstmt.setInt(6, vo.getMRType());
			// 9/.E_number
			pstmt.setInt(7, vo.getENumber());
			// 10.E_number2
			pstmt.setInt(8, vo.getENumber2());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public EmployeeVO getOneReciver(String eId) {
		EmployeeVO vo = null;
		sb.append("SELECT e_number FROM employee WHERE e_id=?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, eId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int eNumber = rs.getInt("e_number");
				String eName = rs.getString("e_name");
				String ePassword = rs.getString("e_password");
				String ePhonenumber = rs.getString("e_phone_number");
				int ePostalCode = rs.getInt("e_postal_code");
				String eAddress1 = rs.getString("e_address1");
				String eAddress2 = rs.getString("e_address2");
				String eBirth = rs.getString("e_birth");
				String eDateJoiningCompany = rs.getString("e_date_joining_company");
				String eRetirementDate = rs.getString("e_retirement_date");
				String eAccountInformation = rs.getString("e_account_information");
				String eGender = rs.getString("e_gender");
				String eOfficialResponsibilities = rs.getString("e_official_responsibilities");
				int eTotalVacation = rs.getInt("e_total_vacation");
				String eServing = rs.getString("e_serving");
				String dName = rs.getString("d_name");
				vo = new EmployeeVO(eNumber, eName, eId, ePassword, ePhonenumber, ePostalCode, eAddress1, eAddress2,
						eBirth, eDateJoiningCompany, eRetirementDate, eAccountInformation, eGender,
						eOfficialResponsibilities, eTotalVacation, eServing, dName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	} // getOne() end

}
