package com.elvis.android.insert_monitor.ui.fragment.detail;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.obj.info.DBInfo;
import com.elvis.android.insert_monitor.obj.info.StackInfo;
import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.UIAnalysisImpl;
import com.elvis.android.insert_monitor.ui.activity.StackSeeActivity;
import com.elvis.android.insert_monitor.ui.view.m_text_view.MTextView;
import com.elvis.android.insert_monitor.utils.StackUtils;
import com.elvis.android.insert_monitor.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/7/5.
 */

public class DBFragment extends Fragment{




    LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.insert_monitor_detail_fragment_db, container, false);
        list_db  = (ListView) view.findViewById(R.id.list_db);
        dbInfos.clear();
        dbInfos.addAll(UIAnalysisImpl.getDBInfos());
        list_db.setAdapter(mDBListAdapter);
        list_db.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DBInfo dbInfo = (DBInfo) mDBListAdapter.getItem(position);
                ArrayList<StackInfo> stackInfos = new ArrayList<>();
                StackInfo stackInfo = new StackInfo(dbInfo.startTime);
                stackInfo.time = dbInfo.startTime;
                stackInfo.stack = dbInfo.stack;
                stackInfos.add(stackInfo);
                StackSeeActivity.startStackSeeActivity(getActivity(),stackInfos);
            }
        });
        return view;
    }


    /**
     * list_block
     */
    ListView list_db;
    ArrayList<DBInfo> dbInfos = new ArrayList<>();
    BaseAdapter mDBListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return dbInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return dbInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View dbItem = mInflater.inflate(R.layout.insert_monitor_detail_fragment_db_item, null);
            final DBInfo dbInfo = (DBInfo) getItem(position);
            if (dbInfo!=null){
                TextView txt_item_db_time = (TextView) dbItem.findViewById(R.id.txt_item_db_time);
                txt_item_db_time.setText(TimeUtils.formatToSecond(dbInfo.startTime-UIAnalysisImpl.getFirstDataTime()));
                TextView txt_item_db_method = (TextView) dbItem.findViewById(R.id.txt_item_db_method);
                txt_item_db_method.setText("方法："+dbInfo.methodName);
                TextView txt_item_db_code = (TextView) dbItem.findViewById(R.id.txt_item_db_code);
                txt_item_db_code.setText("定位："+StackUtils.getDBCode(dbInfo.stack));
            }
//            dbItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    ArrayList<StackInfo> stackInfos = new ArrayList<>();
////                    StackInfo stackInfo = new StackInfo(dbInfo.startTime);
////                    stackInfo.time = dbInfo.startTime;
////                    stackInfo.stack = dbInfo.stack;
////                    stackInfos.add(stackInfo);
////                    StackSeeActivity.startStackSeeActivity(getActivity(),stackInfos);
//                }
//            });
            return dbItem;
        }
    };







}
