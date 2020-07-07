package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicustomviews.R;

import java.util.List;

public class VentilationTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VentilationTableAdapter";
    private List<String> mVentilationField;
    private final static int FIRST_VIEW_TYPE = 1;//第一种布局标识
    private final static int SECOND_VIEW_TYPE = 2;//第二种布局标识

    static class EditHolder extends RecyclerView.ViewHolder{
        TextView fieldName;
        EditText editText;

        public EditHolder(View view){
            super(view);
            fieldName = (TextView) view.findViewById(R.id.field_text);
            editText = (EditText) view.findViewById(R.id.data_edit);
        }
    }

    static class SpinnerHolder extends RecyclerView.ViewHolder{
        TextView fieldName;
        Spinner spinner;
        public SpinnerHolder(View view){
            super(view);
            fieldName = (TextView) view.findViewById(R.id.field_text);
            spinner = (Spinner) view.findViewById(R.id.data_spinner);
        }
    }

    public VentilationTableAdapter(List<String> fieldList){
        mVentilationField = fieldList;
    }

    @Override
    public int getItemViewType(int position) {
        String fieldStr = mVentilationField.get(position);
        switch (fieldStr){
            case "roadway":
                return SECOND_VIEW_TYPE;
            case "routingDate":
                return SECOND_VIEW_TYPE;
            default:
                return FIRST_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         这一点需要说明，viewType是怎么来的，每次进入这个Adapter,getItemViewType会先执行，
         每执行一次getItemViewType就会走一次onCrateViewHolder,然后载入对应的布局，有多少条数据，
         就会执行多少次，这一块我们使用时可以打个断点看一下，非常清晰
        */

        if(viewType == FIRST_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datafillingedit_layout,
                    parent, false);
            EditHolder editHolder = new EditHolder(view);
            return  editHolder;

        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datafillingspinner_layout,
                    parent, false);
            SpinnerHolder spinnerHolder = new SpinnerHolder(view);
            return  spinnerHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String fieldNameString = mVentilationField.get(position);
        if(holder instanceof EditHolder){
            ((EditHolder)holder).fieldName.setText(fieldNameString);
        }else if(holder instanceof SpinnerHolder){
            ((SpinnerHolder)holder).fieldName.setText(fieldNameString);
        }
    }


    @Override
    public int getItemCount() {
        return mVentilationField.size();
    }
}
