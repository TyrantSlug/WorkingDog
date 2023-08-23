package mjc.ftamongft.warkingdog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MatchingFragment extends Fragment {

    private ArrayList<MatchingList> matchingList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mattching, container, false);

        ListView listView = v.findViewById(R.id.matching_list);

        MatchingList matchingList1 = new MatchingList(R.drawable.ic_person_black_24dp, "명지대 근처 산책", "방문객1", "2019-04-01");
        MatchingList matchingList2 = new MatchingList(R.drawable.ic_person_black_24dp, "이번주에 산책하실 분", "방문객2", "2019-03-25");
        MatchingList matchingList3 = new MatchingList(R.drawable.ic_person_black_24dp, "월드컵공원에서 산책하실 분", "방문객3", "2019-03-20");
        MatchingList matchingList4 = new MatchingList(R.drawable.ic_person_black_24dp, "10시 새절역", "방문객4", "2019-03-18");
        MatchingList matchingList5 = new MatchingList(R.drawable.ic_person_black_24dp, "산책파티구함", "방문객5", "2019-03-17");

        matchingList.add(matchingList1);
        matchingList.add(matchingList2);
        matchingList.add(matchingList3);
        matchingList.add(matchingList4);
        matchingList.add(matchingList5);

        MatchingAdapter adapter = new MatchingAdapter(getContext(), R.layout.mattching_content, matchingList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), Mattching_post.class);
                intent.putExtra("profile", Integer.toString(matchingList.get(position).getProfile()));
                intent.putExtra("title", matchingList.get(position).getTitle());
                intent.putExtra("name", matchingList.get(position).getName());
                intent.putExtra("date", matchingList.get(position).getDate());
                startActivity(intent);
            }
        });

        return v;
    }
}
