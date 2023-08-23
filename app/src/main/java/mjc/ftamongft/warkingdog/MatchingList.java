package mjc.ftamongft.warkingdog;

public class MatchingList {
    private int profile;
    private String title;
    private String name;
    private String date;

    public int getProfile(){
        return  profile;
    }
    public String getTitle(){
        return title;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }

    public MatchingList (int profile, String title, String name, String date){
        this.profile = profile;
        this.title = title;
        this.name = name;
        this.date = date;
    }
}
