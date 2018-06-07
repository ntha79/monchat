package vn.monpay.monchat.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.R;
import vn.monpay.monchat.Utilities.F;

public class ChatTitleAdapter  extends BaseAdapter {

    private List<ChatTitleItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ChatTitleAdapter(Context aContext,  List<ChatTitleItem> listDataValue) {
        this.context = aContext;
        this.listData = listDataValue;
        layoutInflater = LayoutInflater.from(aContext);

    }
    public ChatTitleAdapter(Context aContext,  String JSONlistData) {

        listData = new ArrayList<ChatTitleItem>();
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
        if(!F.isEmpty(JSONlistData))
        {
            try {
                JSONArray array;
                array = new JSONArray(JSONlistData);
                int myJsonArraySize = array.length();
                for (int i = 0; i < myJsonArraySize; i++) {
                    JSONObject myJsonObject = (JSONObject) array.get(i);
                    if (myJsonObject == null || myJsonObject.isNull("branchCode"))
                        continue;

                    ChatTitleItem it = new ChatTitleItem();
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

    }

    public static List<ChatTitleItem> Search(List<ChatTitleItem> listValue, String searchValue) {

        if(F.isEmpty(searchValue))
        {
            return listValue;
        }
        List<ChatTitleItem>  resultListData = new ArrayList<ChatTitleItem>();

        try {

            for (ChatTitleItem curObject:listValue
                    ) {
                if(curObject.getFullName().contains(searchValue)||
                        curObject.getLastMessage().contains(searchValue)||curObject.getFullName().contains(searchValue.toUpperCase())||curObject.getFullName().contains(searchValue.toLowerCase())
                        )
                    resultListData.add(curObject);
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return resultListData;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewChatTitleItem holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_chat_title, null);
            holder = new ViewChatTitleItem();
            holder.imageView_chattitle_avatar = (ImageView)convertView.findViewById(R.id.imageView_chattitle_avatar);
            holder.textView_chattitle_name = (TextView)convertView.findViewById(R.id.textView_chattitle_name);
            holder.imageView_chattitle_secret = (ImageView)convertView.findViewById(R.id.imageView_chattitle_secret);
            holder.textView_chattitle_fullname = (TextView)convertView.findViewById(R.id.textView_chattitle_fullname);
            holder.imageView_chattitle_status = (ImageView)convertView.findViewById(R.id.imageView_chattitle_status);
            holder.textView_chattitle_time = (TextView)convertView.findViewById(R.id.textView_chattitle_time);
            holder.textView_chattitle_lastmessage = (TextView)convertView.findViewById(R.id.textView_chattitle_lastmessage);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewChatTitleItem) convertView.getTag();
        }

        ChatTitleItem contactItem = this.listData.get(position);
        holder.imageView_chattitle_avatar.setImageResource(R.drawable.border_cricle);
        if(contactItem.getToId()==2018001)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_g_01);
            Bitmap cbmp = F.GetBitmapCricleFromBitmap(tempBMP,50,50,R.color.colorPrimary);
            holder.imageView_chattitle_avatar.setImageBitmap(cbmp);
        }
        else if(contactItem.getToId()==1528127143)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_b_01);
            Bitmap cbmp = F.GetBitmapCricleFromBitmap(tempBMP,50,50,R.color.colorPrimary);
            holder.imageView_chattitle_avatar.setImageBitmap(cbmp);
        }
        else if(contactItem.getToId()==1528127144)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_g_02);
            Bitmap cbmp = F.GetBitmapCricleFromBitmap(tempBMP,50,50,R.color.colorPrimary);
            holder.imageView_chattitle_avatar.setImageBitmap(cbmp);
        }
        else if(contactItem.getToId()==1528127145)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_b_02);
            Bitmap cbmp = F.GetBitmapCricleFromBitmap(tempBMP,50,50,R.color.colorPrimary);
            holder.imageView_chattitle_avatar.setImageBitmap(cbmp);
        }

        holder.textView_chattitle_name.setText(contactItem.getShortName());

        if(contactItem.getStatus()>0) {
            holder.imageView_chattitle_secret.setVisibility(View.VISIBLE);
            holder.imageView_chattitle_secret.setImageResource(R.drawable.ic_menu_group);
        }
        else if(contactItem.getIsSecret())
        {
            holder.imageView_chattitle_secret.setVisibility(View.VISIBLE);
            holder.imageView_chattitle_secret.setImageResource(R.drawable.ic_menu_secret);
        }
        else  holder.imageView_chattitle_secret.setVisibility(View.GONE);

        holder.textView_chattitle_fullname.setText(contactItem.getFullName());
        //holder.imageView_chattitle_status.setText(contactItem);
        holder.textView_chattitle_time.setText(contactItem.getLastTime());
        holder.textView_chattitle_lastmessage.setText(contactItem.getLastMessage());

        if(contactItem.getIsReceived()&&contactItem.getIsRead())
            holder.imageView_chattitle_status.setImageResource(R.drawable.ic_done_all);
        else if(contactItem.getIsReceived())
            holder.imageView_chattitle_status.setImageResource(R.drawable.ic_done_one);
        return convertView;
    }


    static class ViewChatTitleItem
    {
        private ImageView imageView_chattitle_avatar;
        private TextView textView_chattitle_name;
        private ImageView imageView_chattitle_secret;
        private TextView textView_chattitle_fullname;
        private ImageView imageView_chattitle_status;
        private TextView textView_chattitle_time;
        private TextView textView_chattitle_lastmessage;


    }

}