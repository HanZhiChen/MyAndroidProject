package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pojo.RoadwayType;
import com.example.uicustomviews.R;

import java.util.ArrayList;
import java.util.List;

public class RoadwayTypeSpinnerAdapter extends BaseAdapter {

    private List<RoadwayType> mRdwTypeList;

    public RoadwayTypeSpinnerAdapter() {
    }

    public RoadwayTypeSpinnerAdapter(List<RoadwayType> rdwTypeList) {
        this.mRdwTypeList = rdwTypeList;

    }

    @Override
    public int getCount() {
        return mRdwTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roadway_type_spinner_item
                    , parent, false);
            holder = new ViewHolder();

            holder.txt_content = (TextView) convertView.findViewById(R.id.roadwaytype_status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_content.setText(mRdwTypeList.get(position).getStatus());
        return convertView;
    }

    //添加一个元素
    public void add(RoadwayType data) {
        if (mRdwTypeList == null) {
            mRdwTypeList = new ArrayList<>();
        }
        mRdwTypeList.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position,RoadwayType data){
        if (mRdwTypeList == null) {
            mRdwTypeList = new ArrayList<>();
        }
        mRdwTypeList.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(RoadwayType data) {
        if (mRdwTypeList == null) {
            mRdwTypeList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mRdwTypeList == null) {
            mRdwTypeList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if(mRdwTypeList != null) {
            mRdwTypeList.clear();
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {

        TextView txt_content;
    }
}
