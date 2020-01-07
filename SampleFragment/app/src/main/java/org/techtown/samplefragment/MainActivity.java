package org.techtown.samplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainFragment는 activity_main에 태그로 올라가있으니까 아이디로 찾을 수 있음 (뷰가 아니라서 findFragmentById 사용)
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        // 반면에 얘는 객체로 만들어둠
        menuFragment = new MenuFragment();
    }

    // 프래그먼트에서 호출할 수 있게 정의해 둔 것. 파라미터를 통해 작동
    public void onFragmentChanged(int index) {
        if (index == 0) {
            // replace()함수의 첫번째 인자는 프래그먼트를 담고있는 레이아웃의 id
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        }
    }
}
