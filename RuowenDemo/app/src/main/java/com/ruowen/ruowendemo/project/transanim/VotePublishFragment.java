package com.ruowen.ruowendemo.project.transanim;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ruowen.ruowendemo.R;


/**
 * Created by ruowen on 2017/5/15.
 */

public class VotePublishFragment extends Fragment {

    private ImageView iv;

    public static VotePublishFragment newInstance() {
        VotePublishFragment fragment = new VotePublishFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote_publish, container, false);
        iv = (ImageView)view.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                iv.getGlobalVisibleRect(rect);
                ShareAnimData shareAnimData = new ShareAnimData();
                shareAnimData.setRect(rect);
                Bitmap bitmap=((BitmapDrawable)iv.getDrawable()).getBitmap();
                shareAnimData.setBitmap(bitmap);
                OnPublishSuccessListener listener = (OnPublishSuccessListener)getActivity();
                listener.onPublishSuccess(shareAnimData);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface OnPublishSuccessListener{
        void onPublishSuccess(ShareAnimData shareAnimData);
    }
}
