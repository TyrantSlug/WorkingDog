package mjc.ftamongft.warkingdog;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable{ // 리뷰 데이터베이스 검색 결과{
    String id; // 경로 식별번호
    String parkId; // 경로에 해당하는 공원 식별번호
    String parkName; // 경로에 해당하는 공원 이름
    String title; // 경로 이름
    String writer; // 작성자
    String writeDate; // 작성날짜
    String content; // 경로내용
    String likePoint; // 추천수
    String approval; // 관리자 승인여부
    String imageUrl; // 이미지 url

    static String[] writers = {"홍길동","아무개","댕댕이","냥냥이"};
    static int i = 0;

    public static void readDB(ArrayList<Route> routeList, Park park){
        // 리뷰 객체 생성 후 서버 DB로 부터 읽은 정보를 리뷰 객체에 대입한다. (현재 DB 처리를 하지않고 임시 값 대입)
        for(; i < writers.length; i++) {
            Route route = new Route();
            route.id = "";
            route.parkId = park.id;
            route.parkName = park.name;
            route.title = route.parkName + "의 추천 경로" + i;
            route.writer = writers[i];
            route.writeDate = "2019-05-27";
            route.content = route.title + "에 대한 내용입니다.";
            route.likePoint = "35";
            route.approval = "";
            route.imageUrl = "";
            routeList.add(route);
        }
    }
}
