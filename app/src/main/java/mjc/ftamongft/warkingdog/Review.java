package mjc.ftamongft.warkingdog;

import java.io.Serializable;
import java.util.ArrayList;

public class Review implements Serializable { // 리뷰 데이터베이스 검색 결과
    String id; // 리뷰 식별번호
    String parkId; // 리뷰에 해당하는 공원 식별번호
    String parkName; // 리뷰에 해당하는 공원 이름
    String title; // 제목
    String writer; // 작성자
    String writeDate; // 작성날짜
    String content; // 리뷰내용
    String[] imageUrl; // 이미지 url

    static String[] writers = {"홍길동","아무개","댕댕이","냥냥이"};
    static int i = 0;

    public static void readDB(ArrayList<Review> reviewList, Park park){
        // 리뷰 객체 생성 후 서버 DB로 부터 읽은 정보를 리뷰 객체에 대입한다. (현재 DB 처리를 하지않고 임시 값 대입)
        for(; i < writers.length; i++) {
            Review review = new Review();
            review.id = "";
            review.parkId = park.id;
            review.parkName = park.name;
            review.title = review.parkName + "의 리뷰" + i;
            review.writer = writers[i];
            review.writeDate = "2019-05-27";
            review.content = review.title + "에 대한 내용입니다.";
            review.imageUrl = new String[]{"url", "url2"};
            reviewList.add(review);
        }
    }
}
