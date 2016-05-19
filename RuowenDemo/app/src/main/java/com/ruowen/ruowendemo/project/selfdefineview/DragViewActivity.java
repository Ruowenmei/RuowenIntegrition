package com.ruowen.ruowendemo.project.selfdefineview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.selfdefineview.view.DragView;

public class DragViewActivity extends BaseActivity implements View.OnClickListener{

    private final int OFFSET = 50;
    private DragView dragView;
    private Button btnMove;
    private Button btnMoveBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_drag_view);
    }

    @Override
    protected void initViewById() {
        dragView = (DragView)findViewById(R.id.dv_view);
        btnMove = (Button)findViewById(R.id.btnMove);
        btnMoveBack = (Button)findViewById(R.id.btnMoveBack);
    }

    @Override
    protected void setListener() {
        btnMove.setOnClickListener(this);
        btnMoveBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMove:{
                dragView.layout(dragView.getLeft()+OFFSET,
                        dragView.getTop()+OFFSET,
                        dragView.getRight()+OFFSET,
                        dragView.getBottom()+OFFSET);
            }
            break;
            case R.id.btnMoveBack:{
                dragView.layout(dragView.getLeft()-OFFSET,
                        dragView.getTop()-OFFSET,
                        dragView.getRight()-OFFSET,
                        dragView.getBottom()-OFFSET);
            }
            break;
        }
    }

    @Override
    protected void processLogic() {

    }
}
