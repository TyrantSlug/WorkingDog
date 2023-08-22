package mjc.ftamongft.warkingdog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;


public class ChatmenuFragment extends Fragment {

    private ChattingFragment chattingFragment = new ChattingFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_menu, container, false);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.chat_frame_layout,
                new ChattingFragment()).commit();

        TabLayout tabLayout = v.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.chat_frame_layout,
                            new ChattingFragment()).commit();
                            break;
                    case 1 : getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.chat_frame_layout,
                            new MatchingFragment()).commit();
                             break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }
}
