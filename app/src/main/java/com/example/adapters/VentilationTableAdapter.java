package com.example.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.DataTimeSelectLayout;
import com.example.pojo.RoadwayType;
import com.example.uicustomviews.R;

import java.util.ArrayList;
import java.util.List;

public class VentilationTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VentilationTableAdapter";

    private Context mContext;
    private Object[] mVentilationFields;
    private final static int FIRST_VIEW_TYPE = 1;//第一种布局标识
    private final static int SECOND_VIEW_TYPE = 2;//第二种布局标识
    private final static int THIRD_VIEW_TYPE = 3;//第二种布局标识


    //显示条数
    private final int mSimpleNum = 20;

    //ViewHolder 创建次数
    private int mCreateTimes = 0;

    //ViewHolder 绑定次数
    private int mBindTimes = 0;

     //已经绑定文本变化监听器
    private final boolean mBoundWatcher = true;

     //输入框文本缓存
    private SparseArray<String> mTextCache = new SparseArray<>();



    public interface  SaveEditListener{
        void saveEdit(String fieldName, String data);
    }


    public VentilationTableAdapter(Object[] fields, Context mContext){
        this.mVentilationFields = fields;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        String fieldStr = (String)mVentilationFields[position];
        switch (fieldStr){
            case "roadway":
                return SECOND_VIEW_TYPE;
            case "routingDate":
                return THIRD_VIEW_TYPE;
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

            view.setTag(editHolder);
            Log.i("onCreateViewHolder", "CreateTimes == " + mCreateTimes++);

            return  editHolder;

        }else if(viewType == SECOND_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datafillingspinner_layout,
                    parent, false);
            SpinnerHolder spinnerHolder = new SpinnerHolder(view);
            Log.i("onCreateViewHolder", "CreateTimes == " + mCreateTimes++);
            return  spinnerHolder;
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datefilling_layout,
                    parent, false);
            DateTimeSelectHolder dateTimeSelectHolder = new DateTimeSelectHolder(view);
            Log.i("onCreateViewHolder", "CreateTimes == " + mCreateTimes++);
            return  dateTimeSelectHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "BindTimes == " + mBindTimes++ + " Position == " + holder.getAdapterPosition());

        Log.i("TEXT_SHOW", "ShowPosition == " + holder.getAdapterPosition());
        String fieldNameString = (String)mVentilationFields[position];
        if(holder instanceof EditHolder){
            EditHolder editHolder = (EditHolder)holder;



            editHolder.editText.setText(mTextCache.get(holder.getAdapterPosition()));

            // 如果已经绑定文本变化监听器不再次绑定
            if(editHolder.editText.getTag()!=null && editHolder.editText.getTag() instanceof TextWatcher){
                return;
            }
//            holder.setIsRecyclable(false);





            //添加editText的监听事件
            TextWatcher textWatcher = new TextSwitcher(editHolder);
            editHolder.editText.addTextChangedListener(textWatcher);
            editHolder.editText.setTag(textWatcher);



            editHolder.fieldName.setText(fieldNameString);





        }else if(holder instanceof SpinnerHolder){
            ((SpinnerHolder)holder).fieldName.setText(fieldNameString);

            List<RoadwayType> exampleRoadwayTypes = new ArrayList<RoadwayType>();
            exampleRoadwayTypes.add(new RoadwayType("巷道形状1"));
            exampleRoadwayTypes.add(new RoadwayType("巷道形状2"));
            exampleRoadwayTypes.add(new RoadwayType("巷道形状3"));
            exampleRoadwayTypes.add(new RoadwayType("填写自定义形状"));
//            RoadwayType[] exampleRoadwayTypes = {
//                    new RoadwayType("巷道形状1"),
//                    new RoadwayType("巷道形状2"),
//                    new RoadwayType("巷道形状3"),
//                    new RoadwayType("填写自定义形状"),
//            };


            Spinner spinner = ((SpinnerHolder)holder).spinner;
            RoadwayTypeSpinnerAdapter adapter = new RoadwayTypeSpinnerAdapter(exampleRoadwayTypes);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(spinner.getContext(), R.layout.roadway_type_spinner_item, exampleRoadwayTypes);
            spinner.setAdapter(adapter);
        }else{
            ((DateTimeSelectHolder)holder).fieldName.setText(fieldNameString);
        }
    }


    @Override
    public int getItemCount() {
        return mVentilationFields.length;
    }

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

    static class DateTimeSelectHolder extends RecyclerView.ViewHolder{
        TextView fieldName;
        EditText dateEdit;
        EditText timeEdit;
        public DateTimeSelectHolder(View view){
            super(view);
            fieldName = (TextView) view.findViewById(R.id.field_text);
            dateEdit = (EditText) view.findViewById(R.id.date_edit);
            timeEdit = (EditText) view.findViewById(R.id.time_edit);
        }
    }

    class TextSwitcher implements TextWatcher {

        private EditHolder mHolder;

        public TextSwitcher(EditHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
            // 如果填入数据与缓存数据相同返回
            if (TextUtils.equals(mTextCache.get(mHolder.getAdapterPosition()), s.toString())) {
                return;
            }

            mTextCache.put(mHolder.getAdapterPosition(), s.toString());
            Log.i("TEXT_PUT", "PutPosition == " + mHolder.getAdapterPosition());


            SaveEditListener listener = (SaveEditListener) mContext;
            if (s != null) {
                listener.saveEdit(mHolder.fieldName.getText().toString(), s.toString());
            }
        }
    }

}
