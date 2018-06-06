package vn.monpay.monchat.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            /*
            for (ChatTitleItem curObject:listValue
                    ) {
                if(curObject.getbranchCode().contains(searchValue)||
                        curObject.getbranchName().contains(searchValue)||
                        curObject.getbranchAddress().contains(searchValue)||
                        curObject.getbranchContactPhone().contains(searchValue)||
                        curObject.getdescription().contains(searchValue)
                        )
                    resultListData.add(curObject);
            }
            */
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
            //convertView = layoutInflater.inflate(R.layout.layout_bank_branch_item, null);
            holder = new ViewChatTitleItem();
            //holder.textViewBankBranchName = (TextView)convertView.findViewById(R.id.textViewBankBranchName);
            //holder.textViewBankBranchAddress = (TextView)convertView.findViewById(R.id.textViewBankBranchAddress);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewChatTitleItem) convertView.getTag();
        }

        ChatTitleItem contactItem = this.listData.get(position);
        //holder.textViewBankBranchName.setText(contactItem.getbranchName());
        //holder.textViewBankBranchAddress.setText(contactItem.getbranchAddress());
        return convertView;
    }


    static class ViewChatTitleItem
    {
        private TextView textViewBankBranchName;
        private TextView textViewBankBranchAddress;


    }

}