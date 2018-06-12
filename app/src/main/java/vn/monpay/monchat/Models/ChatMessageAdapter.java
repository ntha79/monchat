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
import vn.monpay.monchat.Utilities.TestAutoMessage;

public class ChatMessageAdapter  extends BaseAdapter {

    private List<ChatMessageItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private boolean isShowInMap = false;

    public ChatMessageAdapter(Context aContext,  List<ChatMessageItem> listDataValue) {
        this.context = aContext;
        this.listData = listDataValue;

        layoutInflater = LayoutInflater.from(aContext);

    }
    public ChatMessageAdapter(Context aContext,  String JSONlistData) {

        listData = new ArrayList<ChatMessageItem>();
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

                    ChatMessageItem it = new ChatMessageItem();
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

    }

    public void SetIsShowInMap(boolean value)
    {
        isShowInMap = value;
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
        vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem holder;

        ChatMessageItem contactItem = this.listData.get(position);
        if(isShowInMap)
        {
            if (contactItem.getMsgType() == 1) //qc
            {
                convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_inmap_adv, null);
                holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_adv_avata);
                holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_adv_fullname);
                holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_adv_message);
                holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_adv_time);
                holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_adv_status);
                holder.imageView_chat_msg_item_avata.setImageResource(R.drawable.ic_tag_discount);
                convertView.setTag(holder);
            } else {
                if (contactItem.getFromId() == contactItem.getOwnerId()) {
                    convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_inmap_right, null);
                    holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                    holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_right_avatar);
                    holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_right_fullname);
                    holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_right_message);
                    holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_right_time);
                    holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_right_status);
                    convertView.setTag(holder);


                } else {
                    convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_inmap_left, null);
                    holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                    holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_left_avata);
                    holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_left_fullmane);
                    holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_left_message);
                    holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_inmap_left_time);
                    holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_inmap_left_status);
                    convertView.setTag(holder);


                }

                holder.imageView_chat_msg_item_status.setImageResource(R.drawable.ic_done_all);
                holder.textView_chat_msg_item_fullmane.setText(contactItem.getFromFullName());
                holder.imageView_chat_msg_item_avata.setImageResource(R.drawable.border_cricle);
                Bitmap bmp = TestAutoMessage.getBitmapAvatar(context, contactItem.getFromId());
                if (bmp != null)
                    holder.imageView_chat_msg_item_avata.setImageBitmap(bmp);


            }
            holder.textView_chat_msg_item_message.setText(contactItem.getMessage());
            holder.textView_chat_msg_item_time.setText(contactItem.getLastTime());
        }
        else {

            if (contactItem.getMsgType() == 1) //qc
            {
                convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_adv, null);
                holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_adv_avata);
                holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_adv_fullname);
                holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_adv_message);
                holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_adv_time);
                holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_adv_status);
                holder.imageView_chat_msg_item_avata.setImageResource(R.drawable.ic_tag_discount);
                convertView.setTag(holder);
            } else {
                if (contactItem.getFromId() == contactItem.getOwnerId()) {
                    convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_right, null);
                    holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                    holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_right_avatar);
                    holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_right_fullname);
                    holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_right_message);
                    holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_right_time);
                    holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_right_status);
                    convertView.setTag(holder);


                } else {
                    convertView = layoutInflater.inflate(R.layout.layout_chat_message_item_left, null);
                    holder = new vn.monpay.monchat.Models.ChatMessageAdapter.ViewChatMessageItem();
                    holder.imageView_chat_msg_item_avata = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_left_avata);
                    holder.textView_chat_msg_item_fullmane = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_left_fullmane);
                    holder.textView_chat_msg_item_message = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_left_message);
                    holder.textView_chat_msg_item_time = (TextView) convertView.findViewById(R.id.textView_chat_msg_item_left_time);
                    holder.imageView_chat_msg_item_status = (ImageView) convertView.findViewById(R.id.imageView_chat_msg_item_left_status);
                    convertView.setTag(holder);


                }

                holder.imageView_chat_msg_item_status.setImageResource(R.drawable.ic_done_all);
                holder.textView_chat_msg_item_fullmane.setText(contactItem.getFromFullName());
                holder.imageView_chat_msg_item_avata.setImageResource(R.drawable.border_cricle);
                Bitmap bmp = TestAutoMessage.getBitmapAvatar(context, contactItem.getFromId());
                if (bmp != null)
                    holder.imageView_chat_msg_item_avata.setImageBitmap(bmp);


            }
            holder.textView_chat_msg_item_message.setText(contactItem.getMessage());
            holder.textView_chat_msg_item_time.setText(contactItem.getLastTime());
        }
        return convertView;
    }


    static class ViewChatMessageItem
    {
        private ImageView imageView_chat_msg_item_avata;
        private TextView textView_chat_msg_item_fullmane;
        private TextView textView_chat_msg_item_message;
        private TextView textView_chat_msg_item_time;
        private ImageView imageView_chat_msg_item_status;

    }

}