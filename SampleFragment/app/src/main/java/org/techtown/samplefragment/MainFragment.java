package org.techtown.samplefragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프래그먼트에서는 getActivity()메서드를 호출하면 프래그먼트가 올라가 있는 액티비티가 어떤 것인지 확인할 수 있습니다.
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0); // 프래그먼트는 액티비티에서 관리하니까 액티비티에서 변환
            }
        });

        return rootView;
    }
}