package mjc.ftamongft.warkingdog;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Park implements Serializable { // 공원 데이터베이스 검색 결과
    String id; // 식별번호
    String name; // 이름
    String city; // 시,도명
    String address; //  주소
    String tel; // 전화번호
    String charge; // 입장료
    String parking; // 주차정보
    String[] imageUrl; // 이미지 url
    String url; // 홈페이지 url
    String openTime; // 운영시간
    String likePoint; // 추천수
    double latitude, longtitude; // 위도, 경도

    // 현재 DB 검색 로직이 없어서 테스트용 데이터를 임시로 등록
    static String[] cities = {"서울", "경기", "충청", "전라", "강원", "경상", "제주"};
    static String[] addresses = {"서울특별시 송파구 오륜동 올림픽로 424", "경기도 시흥시 연성동 동서로 287",
    "충청남도 아산시 영인면 월선길 20-42","전라남도 순천시 오천동 국가정원1호길 162-11",
    "강원도 삼척시 원덕읍 삼척로 1852-6","경상북도 문경시 문경읍 상초리 288-10","제주특별자치도 제주시 한림읍 한림로 300"};
    static String[] names = {"올림픽공원", "갯골생태공원","피나클랜드","순천만국가정원","해신당공원","문경새재도립공원",
    "한림공원"};
    static String[] urls = {"http://olympicpark.kspo.or.kr/", "http://www.siheung.go.kr/"
            ,"http://www.pinnacleland.net/","http://garden.sc.go.kr/","http://tour.samcheok.go.kr/02nice/02_04.jsp",
    "https://www.gbmg.go.kr/tour/contents.do?mId=0101010100","http://www.hallimpark.co.kr/"};
    static String[] tels = {"02-410-1114", "031-488-6900","041-534-2580","1577-2013","033-572-4429",
    "054-571-0709","064-796-0001"};

    static int[][] imageRes = {
            {R.drawable.park1,R.drawable.park2},{R.drawable.park3,R.drawable.park4},{R.drawable.park5,R.drawable.park6}
            ,{R.drawable.park7,R.drawable.park8},{R.drawable.park9,R.drawable.park10},{R.drawable.park11,R.drawable.park12},
            {R.drawable.park13,R.drawable.park14}

    };

    static double[] latitudes = {37.521594, 37.391412,36.874203,34.927860,37.268246,36.761825,33.389719};
    static double[] longtitudes = {127.121553, 126.780983,126.927021,127.499356,129.327407,128.076675,126.239280};

    static int i = 0;


    public static void readDB(ArrayList<Park> parkList) {
        // 공원 객체 생성 후 서버 DB로 부터 읽은 정보를 공원 객체에 대입한다. (현재 DB 처리를 하지않고 임시 값 대입)
        for (; i < names.length; i++) {
            Park park = new Park();
            park.id = String.valueOf(i);
            park.name = names[i];
            park.city = cities[i];
            park.address = addresses[i];
            park.tel = tels[i];
            park.charge = "5000";
            park.parking = "주차가능합니다. 주차비용은 5000원";
            park.imageUrl = new String[]{"url", "url2"};
            park.url = urls[i];
            park.openTime = "오전12:00시부터 오후 09:00시까지 운영합니다.";
            park.likePoint = "50";
            park.latitude = latitudes[i];
            park.longtitude = longtitudes[i];
            parkList.add(park);
        }
    }
}
