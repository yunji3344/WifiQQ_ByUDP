package com.example.wifiqq_byudp;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 消息显示列表适配器
 */
public class MsginfoAdapter extends BaseAdapter {
	 
	private List<MsgInfo> mData;
    private LayoutInflater mInflater;
    private Context context;
    
    public static final String TAG = MsginfoAdapter.class.getSimpleName();

    public MsginfoAdapter(Context context) {
    	this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void clearItem(){
    	mData.clear();
    	this.notifyDataSetInvalidated();
    }
    
    public void addItem(MsgInfo item) {
        mData.add(item);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public MsgInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        //BoLog.e(TAG,"getView " + position + " " + convertView);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_msginfo, null);
            holder = new ViewHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            holder.tvContent = (TextView)convertView.findViewById(R.id.tvContent);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        if(mData==null||mData.size() <= position)
        {
        	return convertView;
        }
        holder.tvName.setText(mData.get(position).getName());
        holder.tvContent.setText(mData.get(position).getContent());
        return convertView;
    }

	public void setData(List<MsgInfo> imgData) {
		this.mData = imgData;
	}
}

class ViewHolder {
    public TextView tvName;
    public TextView tvContent;
}

