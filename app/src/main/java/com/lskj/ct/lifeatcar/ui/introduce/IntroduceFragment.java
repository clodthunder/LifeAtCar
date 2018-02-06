package com.lskj.ct.lifeatcar.ui.introduce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.ui.main.MainActivity;

/**
 * Created by thunder on 2018/2/5.
 */

public class IntroduceFragment extends Fragment {
    public static String KEY_LAYOUT_ID = "key_layout_id";
    private Context context;
    //    private static volatile IntroduceFragment instance;
    private int layoutId;


    public static IntroduceFragment getInstance(Bundle bundle) {
        IntroduceFragment instance = new IntroduceFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        bundle 获取布局id
        Bundle bundle = getArguments();
        layoutId = bundle.getInt(KEY_LAYOUT_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        if (layoutId != 0 && layoutId == R.layout.intr_view_04) {
            view.findViewById(R.id.btn_2mainNow).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mintent = new Intent(context, MainActivity.class);
                    context.startActivity(mintent);
                    getActivity().finish();
                }
            });
        }
        return view;
    }

}
