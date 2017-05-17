package com.ruowen.ruowendemo.project.transanim;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ruowen.ruowendemo.R;

public class TransanimActivity extends FragmentActivity
        implements VotePublishFragment.OnPublishSuccessListener{

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transanim);

        initView();
    }

    private void initView(){
        fragmentManager = getSupportFragmentManager();
        VotePublishFragment votePublishFragment = VotePublishFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.vote_pub_container, votePublishFragment).commitAllowingStateLoss();
    }

    @Override
    public void onPublishSuccess(ShareAnimData shareAnimData) {
        VotePubSuccessFragment votePubSuccessFragment = VotePubSuccessFragment.newInstance();
        votePubSuccessFragment.setShareAnimData(shareAnimData);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.replace(R.id.vote_pub_container, votePubSuccessFragment).commitAllowingStateLoss();
    }
}
