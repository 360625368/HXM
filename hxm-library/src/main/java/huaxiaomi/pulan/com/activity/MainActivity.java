package huaxiaomi.pulan.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.dialog.NormalDialog;
import huaxiaomi.pulan.com.dialog.SystemMenuDialog;
import huaxiaomi.pulan.com.dialog.TestDialog;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.ILoginPresent;
import huaxiaomi.pulan.com.mvp.i.IMainPresent;
import huaxiaomi.pulan.com.mvp.i.IMessageDaoPresenter;
import huaxiaomi.pulan.com.mvp.i.IMessagePresenter;
import huaxiaomi.pulan.com.mvp.i.IRecordPresenter;
import huaxiaomi.pulan.com.mvp.v.ILoginView;
import huaxiaomi.pulan.com.mvp.v.IMainView;
import huaxiaomi.pulan.com.mvp.v.IMessageDaoView;
import huaxiaomi.pulan.com.mvp.v.IMessageView;
import huaxiaomi.pulan.com.mvp.v.IRecordView;
import huaxiaomi.pulan.com.widget.RecordView;
import huaxiaomi.pulan.com.widget.RobotButton;
import huaxiaomi.pulan.com.widget.TitlebarLayout;

public class MainActivity extends BaseActivity implements IMainView,ILoginView,IMessageView,IRecordView,IMessageDaoView,View.OnClickListener {

    private TitlebarLayout titlebarLayout;
    private RecyclerView recyclerView;
    private MessageMultipleAdapter adapter;
    private List<Message> datas = new ArrayList<>();

    private SmartRefreshLayout smartRefreshLayout;

    private RecordView recordView;
    private RobotButton robotButton;

    private NormalDialog normalDialog;
    private SystemMenuDialog systemMenuDialog;

    private TestDialog testDialog;

    private IMainPresent mainPresent;
    private ILoginPresent loginPresent;
    private IMessagePresenter messagePresenter;
    private IRecordPresenter recordPresenter;
    private IMessageDaoPresenter messageDaoPresenter;

    private int page = 0;
    private static final int MESSAGE_PAGE_SIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_main);

        titlebarLayout = findViewById(R.id.titlebar);
        recyclerView = findViewById(R.id.rv);
        recordView = findViewById(R.id.record_view);
        robotButton = findViewById(R.id.robot_button);

        smartRefreshLayout = findViewById(R.id.srl);

        View searchLayout = findViewById(R.id.search_layout);
        View settingLayout = findViewById(R.id.setting_layout);
        View skillLayout = findViewById(R.id.skill_layout);

        searchLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
        skillLayout.setOnClickListener(this);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                int from = page * MESSAGE_PAGE_SIZE;
                int to = (page + 1) * MESSAGE_PAGE_SIZE;
                messageDaoPresenter.findMessageFromDao(from,to,true);
            }
        });

        titlebarLayout.setMoreClickListener(new TitlebarLayout.OnMoreClickListener() {
            @Override
            public void onMoreClick(View view) {
                systemMenuDialog.show();
            }
        });

        adapter = new MessageMultipleAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        normalDialog = new NormalDialog(this);
        normalDialog.setOnItemClickListener(new NormalDialog.OnItemClickListener() {
            @Override
            public void onItemClick(NormalDialog.Item item) {
                messagePresenter.sendTextMessage(item.title);
            }
        });

        systemMenuDialog = new SystemMenuDialog(this);
        systemMenuDialog.setMenuClickListener(new SystemMenuDialog.OnMenuClickListener() {
            @Override
            public void onMenuClick(int index) {
                if(index == 0){
                    loginPresent.requestLogin("bduser","bdtest");
                }
                if(index == 1){
                    messageDaoPresenter.deleteAllMessage();
                }
            }
        });

        testDialog = new TestDialog(this);
        testDialog.setOnSendClickListener(new TestDialog.OnSendClickListener() {
            @Override
            public void onSendClick(String content) {
                messagePresenter.sendTextMessage(content);
            }
        });

        loginPresent.requestLogin("luolp","");

        robotButton.setOnLongClickListener(new RobotButton.OnLongClickListener() {
            @Override
            public void onLongClick() {
                recordPresenter.startRecord();
            }

            @Override
            public void onUp() {
                recordPresenter.stopRecord();
            }
        });

        recordView.setVisibility(View.INVISIBLE);

        messageDaoPresenter.findMessageFromDao(0,MESSAGE_PAGE_SIZE,true);
    }

    @Override
    public void initDialogMenus(List<NormalDialog.Item> menus) {
        normalDialog.setItems(menus);
        normalDialog.show();
    }

    @Override
    public void initPresenter() {
        mainPresent = MvpFactory.buildeP(IMainPresent.class);
        loginPresent = MvpFactory.buildeP(ILoginPresent.class);
        messagePresenter = MvpFactory.buildeP(IMessagePresenter.class);
        recordPresenter = MvpFactory.buildeP(IRecordPresenter.class);
        messageDaoPresenter = MvpFactory.buildeP(IMessageDaoPresenter.class);
    }

    @Override
    public void attachView() {
        mainPresent.attachView(this);
        loginPresent.attachView(this);
        messagePresenter.attachView(this);
        recordPresenter.attachView(this);
        messageDaoPresenter.attachView(this);
    }

    @Override
    public void dettachView() {
        mainPresent.dettachView();
        loginPresent.dettachView();
        messagePresenter.dettachView();
        recordPresenter.dettachView();
        messageDaoPresenter.dettachView();
    }

    @Override
    public void loginResult(String message) {
        showToast(message);
    }

    @Override
    public void onMessageReceive(Message result) {
        datas.add(result);
        adapter.notifyDataSetChanged();
        if(datas.size() > 0){
            recyclerView.smoothScrollToPosition(datas.size() - 1);
        }
    }

    @Override
    public void setVoiceLevel(int level) {
        recordView.setVoiceLevel(level);
    }

    @Override
    public void setRecordEnable(boolean enable) {
        recordView.setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void recordResult(String result) {
        messagePresenter.sendTextMessage(result);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.search_layout){
            Intent intent = new Intent(this,DailyActivity.class);
            startActivity(intent);
        }
        if(id == R.id.setting_layout){
//            mainPresent.getSettingMenus();
            testDialog.show();
        }
        if(id == R.id.skill_layout){
            mainPresent.getSkillMenus();
        }
    }

    @Override
    public void onMessageFromDao(List<Message> messages) {
        smartRefreshLayout.finishRefresh();
        if(messages == null || messages.size() == 0){
            smartRefreshLayout.setEnableRefresh(false);
            return;
        }
        page++;
        Collections.reverse(messages);
        datas.addAll(0,messages);
        adapter.notifyDataSetChanged();
        if(datas.size() > 0 && page == 1){
            recyclerView.smoothScrollToPosition(datas.size() - 1);
        }
    }

    @Override
    public void onDeleteAllMessage() {
        datas.clear();
        adapter.notifyDataSetChanged();
    }
}
