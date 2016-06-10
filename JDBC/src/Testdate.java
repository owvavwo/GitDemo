import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Testdate {
 
	public static void main(String[] args) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://127.0.0.1:3306/bbs?user=root&password=root");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from article ");
		while(rs.next()) {
			//Date d = rs.getDate("pdate"); //Date����ֻ�ܻ�ȡ���ڲ��ܻ�ȡ���ݿ�ʱ�䣬��û��ʱ���֡���
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");			
			//System.out.println(sdf.format(d));
			
			/*Calendar c = Calendar.getInstance();
			System.out.print(c.get(Calendar.MONTH)+1 + "-"); //Calendar.MONTH��ֵ�Ǵ�0~11�����Լǵü���1			 
			System.out.println(c.get(Calendar.DAY_OF_YEAR));*/
			
			Timestamp ts = rs.getTimestamp("pdate");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //����ȱʡʱ��Ϊ����ʱ���ʽ
			System.out.println(sdf.format(ts));
		 	 
			
		}
		rs.close();
		stmt.close();
		conn.close();

	}

}
