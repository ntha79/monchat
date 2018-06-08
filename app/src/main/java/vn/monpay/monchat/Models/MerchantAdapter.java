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

    public class MerchantAdapter  extends BaseAdapter {

        private List<MerchantItem> listData;
        private LayoutInflater layoutInflater;
        private Context context;

        public MerchantAdapter(Context aContext,  List<MerchantItem> listDataValue) {
            this.context = aContext;
            this.listData = listDataValue;
            layoutInflater = LayoutInflater.from(aContext);

        }
        public MerchantAdapter(Context aContext,  String JSONlistData) {

            listData = new ArrayList<MerchantItem>();
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

                        MerchantItem it = new MerchantItem();
                        listData.add(it);
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                }
            }

        }

        public static List<MerchantItem> Search(List<MerchantItem> listValue, String searchValue) {

            if(F.isEmpty(searchValue))
            {
                return listValue;
            }
            List<MerchantItem>  resultListData = new ArrayList<MerchantItem>();

            try {

                for (MerchantItem curObject:listValue) {
                    if(searchValue.equals("%"))
                    {
                        if(curObject.getDiscount()>0)
                            resultListData.add(curObject);
                    }
                    else {

                        if (curObject.getFullName().contains(searchValue) ||
                                curObject.getAddress().contains(searchValue) ||
                                curObject.getFullName().contains(searchValue.toUpperCase()) ||
                                curObject.getFullName().toUpperCase().contains(searchValue.toUpperCase()) ||
                                curObject.getFullName().toLowerCase().contains(searchValue.toLowerCase()) ||
                                curObject.getFullName().contains(searchValue.toLowerCase()
                                )
                                )
                            resultListData.add(curObject);
                    }
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
            vn.monpay.monchat.Models.MerchantAdapter.ViewMerchantItem holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.layout_merchant_item, null);
                holder = new vn.monpay.monchat.Models.MerchantAdapter.ViewMerchantItem();
                holder.imageView_merchant_avatar = (ImageView)convertView.findViewById(R.id.imageView_merchant_avatar);
                holder.textView_merchant_fullname = (TextView)convertView.findViewById(R.id.textView_merchant_fullname);
                holder.textView_merchant_distance = (TextView)convertView.findViewById(R.id.textView_merchant_distance);
                holder.textView_merchant_address = (TextView)convertView.findViewById(R.id.textView_merchant_address);
                holder.textView_merchant_mobilePhone = (TextView)convertView.findViewById(R.id.textView_merchant_mobilePhone);
                holder.textView_merchant_discount = (TextView)convertView.findViewById(R.id.textView_merchant_discount);

                convertView.setTag(holder);
            }
            else
            {
                holder = (vn.monpay.monchat.Models.MerchantAdapter.ViewMerchantItem) convertView.getTag();
            }

            MerchantItem merchantItem = this.listData.get(position);


            holder.textView_merchant_fullname.setText(merchantItem.getFullName());
            holder.textView_merchant_distance.setText(merchantItem.getDistanceString());
            holder.textView_merchant_address.setText(merchantItem.getAddress());
            holder.textView_merchant_mobilePhone.setText(merchantItem.getClassification());

            if(merchantItem.getDiscount()>0)
            {
                holder.imageView_merchant_avatar.setImageResource(R.drawable.ic_tag_discount);
                holder.textView_merchant_discount.setText(merchantItem.getDiscountString());
            }
            else
            {
                holder.imageView_merchant_avatar.setImageResource(R.drawable.ic_merchant_default);
                holder.textView_merchant_discount.setText("");
            }
            return convertView;
        }


        static class ViewMerchantItem
        {
            private ImageView imageView_merchant_avatar;
            private TextView textView_merchant_fullname;
            private TextView textView_merchant_distance;
            private TextView textView_merchant_address;
            private TextView textView_merchant_mobilePhone;
            private TextView textView_merchant_discount;


        }

    }