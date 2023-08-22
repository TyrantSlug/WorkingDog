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

public class ChattingFragment extends Fragment {

    private ArrayList<ChattingList> chattingList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chatting, container, false);

        ListView listView = v.findViewById(R.id.chat_list);

        ChattingList chattingList1 = new ChattingList(R.drawable.ic_person_black_24dp, "최근 대화 내용1", "방문객1", "10 : 56 AM");
        ChattingList chattingList2 = new ChattingList(R.drawable.ic_person_black_24dp, "최근 대화 내용2", "방문객2", "09 : 10 AM");
        ChattingList chattingList3 = new ChattingList(R.drawable.ic_person_black_24dp, "최근 대화 내용3", "방문객3", "08 : 56 AM");
        ChattingList chattingList4 = new ChattingList(R.drawable.ic_person_black_24dp, "최근 대화 내용4", "방문객4", "07 : 10 AM");

        chattingList.add(chattingList1);
        chattingList.add(chattingList2);
        chattingList.add(chattingList3);
        chattingList.add(chattingList4);

        ChattingAdapter adapter = new ChattingAdapter(getContext(), R.layout.chat_content, chattingList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ChatClicked.class);
                intent.putExtra("profile", Integer.toString(chattingList.get(position).getProfile()));
                intent.putExtra("title", chattingList.get(position).getTitle());
                intent.putExtra("name", chattingList.get(position).getName());
                intent.putExtra("date", chattingList.get(position).getDate());
                startActivity(intent);
            }
        });

        return v;
    }
}
