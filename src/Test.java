import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) {
		String url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements busan_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(2) > td:nth-child(5)");
			
			String busan_vaccine = busan_elm.text();
			//System.out.println();
			busan_vaccine = busan_vaccine.format("%.2f%%%n", Double.parseDouble(busan_vaccine.replace(",",""))  / 9505868 * 100.0);
			System.out.println(busan_vaccine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String test1 = "7,495,966";
//		System.out.println(test1.replace(",",""));
	}

}
