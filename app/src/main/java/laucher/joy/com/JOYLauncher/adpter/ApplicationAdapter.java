package laucher.joy.com.JOYLauncher.adpter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import laucher.joy.com.JOYLauncher.R;
import laucher.joy.com.JOYLauncher.entity.AppBean;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ApplicationAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AppBean> lancherInfos;
    private AppBean lancher;

    public ApplicationAdapter(Context paramContext, ArrayList<AppBean> paramArrayList) {
        this.context = paramContext;
        this.lancherInfos = paramArrayList;
//        setLauncher(paramArrayList);
//        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.lancherInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lancherInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.i("ApplicationAdapter", "getView====null==");
            convertView = LayoutInflater.from(this.context).inflate(R.layout.app_list_item, null);

        }
        this.lancher = (AppBean) this.getItem(position);
        ImageView appIcon = (ImageView) convertView.findViewById(R.id.app_icon);
        TextView localTextView = (TextView) convertView.findViewById(R.id.app_name);
        appIcon.setImageDrawable(this.lancher.getIcon());
        localTextView.setText(this.lancher.getName());
        convertView.setLayoutParams(new AbsListView.LayoutParams(160, 160));
        Log.i("ApplicationAdapter", "getView======over==");
        return convertView;
    }

    private boolean isANewApp(AppBean paramAppBean) {
        return new File(paramAppBean.getDataDir()).lastModified() + 259200000L >= System.currentTimeMillis();
    }

    public void changeData(ArrayList<AppBean> paramArrayList) {
        this.lancherInfos = paramArrayList;
        notifyDataSetChanged();
    }


    public void setLauncher(ArrayList<AppBean> paramArrayList) {
        if (paramArrayList != null) {
            this.lancherInfos = paramArrayList;
            return;
        }
        this.lancherInfos = new ArrayList();
    }


}


