import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class TestDateAndTime {
 
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());//��1970����ʱ���������ʼ�����ڹ��˶��ٺ���
		
		Date d = new Date();		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(d) );
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
		
		String s = "2012-12-30 08:22:31.12";
		Timestamp ts = Timestamp.valueOf(s);
		System.out.println(ts);
		c.setTime(ts);  //ts�̳�date����һ��date���ͣ���setTime����һ��date����
		System.out.println(c.get(Calendar.YEAR));
		
		Calendar cJapan = new GregorianCalendar(TimeZone.getTimeZone("Japan")); //��ȡС�ձ����ĵ���ʱ��
		System.out.println(cJapan.get(Calendar.HOUR_OF_DAY));
		
		/*for(String str : TimeZone.getAvailableIDs()) {
			System.out.println(str); //��ȡTimeZone�������ID
		}
		*/
		
	}

}
