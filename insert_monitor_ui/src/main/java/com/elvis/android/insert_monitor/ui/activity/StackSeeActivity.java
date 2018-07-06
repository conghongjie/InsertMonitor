package com.elvis.android.insert_monitor.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.elvis.android.insert_monitor.InsertMonitor;
import com.elvis.android.insert_monitor.obj.info.DBInfo;
import com.elvis.android.insert_monitor.obj.info.StackInfo;
import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.UIAnalysisImpl;
import com.elvis.android.insert_monitor.ui.fragment.detail.BlockFragment;
import com.elvis.android.insert_monitor.ui.fragment.detail.DBFragment;
import com.elvis.android.insert_monitor.ui.fragment.detail.InflateFragment;
import com.elvis.android.insert_monitor.ui.view.m_text_view.MTextView;
import com.elvis.android.insert_monitor.utils.StackUtils;
import com.elvis.android.insert_monitor.utils.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/7/4.
 */

public class StackSeeActivity extends Activity{



    public static void startStackSeeActivity(Context context,ArrayList<StackInfo> stackInfos){
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<stackInfos.size();i++){
            jsonArray.put(InsertMonitor.getIJson().toJson(stackInfos.get(i)));
        }
        Intent intent = new Intent(context,StackSeeActivity.class);
        intent.putExtra(KEY_STACK_INFO_LIST,jsonArray.toString());
        if (!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }



    public static final String KEY_STACK_INFO_LIST = "stack_info_list";
    ArrayList<StackInfo> stackInfos = new ArrayList<>();

    LayoutInflater mInflater;

    ImageView img_back;
    TextView txt_title;
    ListView list_stack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_monitor_stack_activity);

        Intent intent  = getIntent();
        if (intent!=null){
            JSONArray jsonArray;
            String jsonArrayString = intent.getStringExtra(KEY_STACK_INFO_LIST);
            try{
                jsonArray = new JSONArray(jsonArrayString);
            }catch (Exception e){
                jsonArray = new JSONArray();
            }
            for (int i=0;i<jsonArray.length();i++){
                try {
                    stackInfos.add(InsertMonitor.getIJson().fromJson(jsonArray.get(i).toString(),StackInfo.class));
                }catch (JSONException e){}
            }
        }

        mInflater = LayoutInflater.from(StackSeeActivity.this);

        img_back = (ImageView) findViewById(R.id.img_back);
        txt_title = (TextView) findViewById(R.id.txt_title);
        list_stack = (ListView) findViewById(R.id.list_stack);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("代码栈信息：");
        list_stack.setAdapter(mStackListAdapter);

    }




    /**
     * list_block
     */
    BaseAdapter mStackListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return stackInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return stackInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View dbItem = mInflater.inflate(R.layout.insert_monitor_stack_activity_item, null);
            StackInfo stackInfo = (StackInfo) getItem(position);
            if (stackInfo!=null){
                TextView txt_item_stack_time = (TextView) dbItem.findViewById(R.id.txt_item_stack_time);
                txt_item_stack_time.setText(TimeUtils.formatToSecond(stackInfo.time- UIAnalysisImpl.getFirstDataTime()));

                TextView txt_item_stack_stack = (TextView) dbItem.findViewById(R.id.txt_item_stack_stack);
                txt_item_stack_stack.setText(""+ stackInfo.stack);

//                MTextView txt_item_stack_stack = (MTextView) dbItem.findViewById(R.id.txt_item_stack_stack);
//                txt_item_stack_stack.setMText("栈："+ stackInfo.stack);
//                txt_item_stack_stack.setTextSize(14);
//                txt_item_stack_stack.setTextColor(Color.parseColor("#bbbbbb"));
            }
            return dbItem;
        }
    };


}
