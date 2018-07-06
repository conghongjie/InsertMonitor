package com.elvis.android.insert_monitor.ui.fragment.detail;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.elvis.android.insert_monitor.obj.info.InflateInfo;
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

public class InflateFragment extends Fragment{




    LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.insert_monitor_detail_fragment_inflate, container, false);
        list_inflate  = (ListView) view.findViewById(R.id.list_db);
        inflateInfos.clear();
        inflateInfos.addAll(UIAnalysisImpl.getInflateInfos());
        list_inflate.setAdapter(mInflateListAdapter);
        return view;
    }


    /**
     * list_block
     */
    ListView list_inflate;
    ArrayList<InflateInfo> inflateInfos = new ArrayList<>();
    BaseAdapter mInflateListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return inflateInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return inflateInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflateItem = mInflater.inflate(R.layout.insert_monitor_detail_fragment_inflate_item, null);
            final InflateInfo inflateInfo = (InflateInfo) getItem(position);
            if (inflateInfo!=null){
                TextView txt_item_inflate_time = (TextView) inflateItem.findViewById(R.id.txt_item_inflate_time);
                TextView txt_item_inflate_take = (TextView) inflateItem.findViewById(R.id.txt_item_inflate_take);
                MTextView txt_item_inflate_method = (MTextView) inflateItem.findViewById(R.id.txt_item_inflate_method);
                MTextView txt_item_inflate_code = (MTextView) inflateItem.findViewById(R.id.txt_item_inflate_code);

                txt_item_inflate_time.setText(TimeUtils.formatToSecond(inflateInfo.startTime-UIAnalysisImpl.getFirstDataTime()));
                txt_item_inflate_take.setText(""+(inflateInfo.endTime-inflateInfo.startTime)+"ms");
                txt_item_inflate_method.setMText("方法："+inflateInfo.methodName);
                txt_item_inflate_method.setTextSize(14);
                txt_item_inflate_method.setTextColor(Color.parseColor("#bbbbbb"));
                txt_item_inflate_code.setMText("定位："+StackUtils.getInflateCode(inflateInfo.stack));
                txt_item_inflate_code.setTextSize(14);
                txt_item_inflate_code.setTextColor(Color.parseColor("#bbbbbb"));
            }
            inflateItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<StackInfo> stackInfos = new ArrayList<>();
                    StackInfo stackInfo = new StackInfo(inflateInfo.startTime);
                    stackInfo.time = inflateInfo.startTime;
                    stackInfo.stack = inflateInfo.stack;
                    stackInfos.add(stackInfo);
                    StackSeeActivity.startStackSeeActivity(getActivity(),stackInfos);
                }
            });
            return inflateItem;
        }
    };







}
