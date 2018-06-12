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

public class ContactAdapter  extends BaseAdapter {

    private List<ContactItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ContactAdapter(Context aContext,  List<ContactItem> listDataValue) {
        this.context = aContext;
        this.listData = listDataValue;
        layoutInflater = LayoutInflater.from(aContext);

    }
    public ContactAdapter(Context aContext,  String JSONlistData) {

        listData = new ArrayList<ContactItem>();
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

                    ContactItem it = new ContactItem();
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

    }

    public static List<ContactItem> Search(List<ContactItem> listValue, String searchValue) {

        if(F.isEmpty(searchValue))
        {
            return listValue;
        }
        List<ContactItem>  resultListData = new ArrayList<ContactItem>();

        try {

            for (ContactItem curObject:listValue
                    ) {
                if(curObject.getShowName().contains(searchValue)||curObject.getFirstName().contains(searchValue)||curObject.getLastName().contains(searchValue)||
                        curObject.getEmail().contains(searchValue)||
                        curObject.getLastMessage().contains(searchValue)||curObject.getShowName().contains(searchValue.toUpperCase())||curObject.getShowName().contains(searchValue.toLowerCase())
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
        ViewContactItem holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_contact_item, null);
            holder = new ViewContactItem();
            holder.textView_contactitem_alphabet= (TextView)convertView.findViewById(R.id.textView_contactitem_alphabet);
            holder.imageView_contactitem_avatar= (ImageView)convertView.findViewById(R.id.imageView_contactitem_avatar);
            holder.textView_contactitem_name= (TextView)convertView.findViewById(R.id.textView_contactitem_name);
            holder.textView_contactitem_fullname= (TextView)convertView.findViewById(R.id.textView_contactitem_fullname);
            holder.imageView_contactitem_status= (ImageView)convertView.findViewById(R.id.imageView_contactitem_status);
            holder.textView_contactitem_lastmessage= (TextView)convertView.findViewById(R.id.textView_contactitem_lastmessage);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewContactItem) convertView.getTag();
        }

        ContactItem contactItem = this.listData.get(position);
        holder.imageView_contactitem_avatar.setImageResource(R.drawable.border_cricle);
        Bitmap bmp = TestAutoMessage.getBitmapAvatar(context,contactItem.getId());
        if(bmp!=null)
            holder.imageView_contactitem_avatar.setImageBitmap(bmp);

        holder.textView_contactitem_name.setText(contactItem.getShortName());
        holder.textView_contactitem_fullname.setText(contactItem.getShowName());
        holder.textView_contactitem_lastmessage.setText(contactItem.getLastMessage());
        if(position%3==0)
        {
            holder.textView_contactitem_alphabet.setText("A");
        }
        else
        {
            holder.textView_contactitem_alphabet.setText("");
        }
        return convertView;
    }


    static class ViewContactItem
    {
        private TextView textView_contactitem_alphabet;
        private ImageView imageView_contactitem_avatar;
        private TextView textView_contactitem_name;
        private TextView textView_contactitem_fullname;
        private ImageView imageView_contactitem_status;
        private TextView textView_contactitem_lastmessage;


    }

}