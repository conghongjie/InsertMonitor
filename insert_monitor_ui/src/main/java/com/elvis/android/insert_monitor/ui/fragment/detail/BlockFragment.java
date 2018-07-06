package com.elvis.android.insert_monitor.ui.fragment.detail;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.UIAnalysisImpl;
import com.elvis.android.insert_monitor.ui.view.m_text_view.MTextView;
import com.elvis.android.insert_monitor.utils.StackUtils;
import com.elvis.android.insert_monitor.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/7/5.
 */

public class BlockFragment extends Fragment{




    LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.insert_monitor_detail_fragment_block, container, false);
        list_block  = (ListView) view.findViewById(R.id.list_block);
        blockInfos.clear();
        blockInfos.addAll(UIAnalysisImpl.getBlockInfos());
        list_block.setAdapter(mBlockListAdapter);
        return view;
    }


    /**
     * list_block
     */
    ListView list_block;
    ArrayList<BlockInfo> blockInfos = new ArrayList<>();
    BaseAdapter mBlockListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return blockInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return blockInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View blockItem = mInflater.inflate(R.layout.insert_monitor_detail_fragment_block_item_block, null);
            BlockInfo blockInfo = (BlockInfo) getItem(position);
            if (blockInfo!=null){
                TextView txt_item_block_take = (TextView) blockItem.findViewById(R.id.txt_item_block_take);
                TextView txt_item_block_time = (TextView) blockItem.findViewById(R.id.txt_item_block_time);
                MTextView txt_item_block_stack = (MTextView) blockItem.findViewById(R.id.txt_item_block_stack);
                txt_item_block_take.setText(""+(blockInfo.endTime-blockInfo.startTime)+"ms");
                txt_item_block_time.setText(TimeUtils.formatToSecond(blockInfo.startTime-UIAnalysisImpl.getFirstDataTime()));
                txt_item_block_stack.setMText(StackUtils.getEffectiveCode(blockInfo.stackInfos));
                txt_item_block_stack.setTextSize(14);
                txt_item_block_stack.setTextColor(Color.parseColor("#bbbbbb"));
            }
            return blockItem;
        }
    };


    /**
     * list_message
     */
    BlockInfo now_block;
    ListView list_message;
    BaseAdapter mMessageListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (now_block!=null){
                return now_block.messageInfos.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (now_block!=null){
                return now_block.messageInfos.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    };






}
