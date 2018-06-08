package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Utilities.F;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class ContactItem
{
    private int baseId = 0;			//Y nghia: 
    private String fullName = "";			//Y nghia: 
    private String firstName = "";			//Y nghia: 
    private String lastName = "";			//Y nghia: 
    private String lastMessage = "";			//Y nghia: 
    private String mobilePhone = "";			//Y nghia: 
    private int status = 0;			//Y nghia: 
    private String note = "";			//Y nghia: 

    public ContactItem( int baseIdValue,  String firstNameValue, String lastNameValue, String lastMessageValue, String mobilePhoneValue, int statusValue, String noteValue)
    {

        baseId = baseIdValue;
        firstName = firstNameValue;
        lastName = lastNameValue;
        lastMessage = lastMessageValue;
        mobilePhone = mobilePhoneValue;
        status = statusValue;
        note = noteValue;
        fullName = firstName;
        if(F.isEmpty(fullName))
            fullName ="";
        if(!F.isEmpty(lastName))
            fullName = fullName+ " "+lastName;
    }

    public ContactItem()
    {

        baseId = 0;
        fullName = "";
        firstName = "";
        lastName = "";
        lastMessage = "";
        mobilePhone = "";
        status = 0;
        note = "";
    }

    public ContactItem(String JSONString)
    {

        baseId = 0;
        fullName = "";
        firstName = "";
        lastName = "";
        lastMessage = "";
        mobilePhone = "";
        status = 0;
        note = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            baseId = F.IntIsNull(obj.getInt("baseId"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            firstName = F.StringIsNull(obj.getString("firstName"));
            lastName = F.StringIsNull(obj.getString("lastName"));
            lastMessage = F.StringIsNull(obj.getString("lastMessage"));
            mobilePhone = F.StringIsNull(obj.getString("mobilePhone"));
            status = F.IntIsNull(obj.getInt("status"));
            note = F.StringIsNull(obj.getString("note"));
        } catch (JSONException e) {}

    }
    public static List<ContactItem> GetListFromJSONString(String JSONlistData)
    {

        List<ContactItem> listData = new ArrayList<ContactItem>();
        if(!F.isEmpty(JSONlistData))
        {
            try {
                JSONArray array;
                array = new JSONArray(JSONlistData);
                int jsonArraySize = array.length();
                for (int i = 0; i < jsonArraySize; i++)
                {
                    JSONObject curJsonObject = (JSONObject) array.get(i);
                    if (curJsonObject == null)
                        continue;
                    ContactItem it = new ContactItem(curJsonObject.toString());
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return listData;
    }
    public int getBaseId(){ return baseId;}
    public String getBaseIdString(){ return "" + baseId;}
    public void setBaseId(int  baseId){ this.baseId = baseId;}
    public String getFullName(){ return fullName;}
    public void setFullName(String  fullName){ this.fullName = fullName;}
    public String getFirstName(){ return firstName;}
    public void setFirstName(String  firstName){ this.firstName = firstName;}
    public String getLastName(){ return lastName;}
    public void setLastName(String  lastName){ this.lastName = lastName;}
    public String getLastMessage(){ return lastMessage;}
    public void setLastMessage(String  lastMessage){ this.lastMessage = lastMessage;}
    public String getMobilePhone(){ return mobilePhone;}
    public void setMobilePhone(String  mobilePhone){ this.mobilePhone = mobilePhone;}
    public int getStatus(){ return status;}
    public String getStatusString(){ return "" + status;}
    public void setStatus(int  status){ this.status = status;}
    public String getNote(){ return note;}
    public void setNote(String  note){ this.note = note;}


    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("baseId",baseId);
            object.put("fullName",fullName);
            object.put("firstName",firstName);
            object.put("lastName",lastName);
            object.put("lastMessage",lastMessage);
            object.put("mobilePhone",mobilePhone);
            object.put("status",status);
            object.put("note",note);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public String getShortName(){ return fullName.substring(0,2).toUpperCase();}

    
    public static List<ContactItem> GetListDemo()
    {

        List<ContactItem> listData = new ArrayList<ContactItem>();
        ContactItem it2018001= new ContactItem(2018001,"Vũ Thị","Lan Anh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018001);
        ContactItem it2018002= new ContactItem(2018002,"Trần Thị","Vân Anh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018002);
        ContactItem it2018003= new ContactItem(2018003,"Hoàng Ngọc","Ánh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018003);
        ContactItem it2018004= new ContactItem(2018004,"Đặng Thị","Đạt","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018004);
        ContactItem it2018005= new ContactItem(2018005,"Phạm Thị","Kim Diệu","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018005);
        ContactItem it2018006= new ContactItem(2018006,"Lưu Thùy","Dung","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018006);
        ContactItem it2018007= new ContactItem(2018007,"Ngô Thị","Thùy Dương","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018007);
        ContactItem it2018008= new ContactItem(2018008,"Vũ Thị","Thu Hà","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018008);
        ContactItem it2018009= new ContactItem(2018009,"Vũ Thị","Hạnh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018009);
        ContactItem it2018010= new ContactItem(2018010,"Nông Thị","Hương","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018010);
        ContactItem it2018011= new ContactItem(2018011,"Đỗ Thị","Thu Huyền","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018011);
        ContactItem it2018012= new ContactItem(2018012,"Ngô Thị","Thanh  Huyền","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018012);
        ContactItem it2018013= new ContactItem(2018013,"Nguyễn Thị","Quỳnh Khanh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018013);
        ContactItem it2018014= new ContactItem(2018014,"Lê Thị","Kim","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018014);
        ContactItem it2018015= new ContactItem(2018015,"Đào Thị","Liên","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018015);
        ContactItem it2018016= new ContactItem(2018016,"Triệu Thị","Linh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018016);
        ContactItem it2018017= new ContactItem(2018017,"Nguyễn Thị","Linh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018017);
        ContactItem it2018018= new ContactItem(2018018,"Ngô Văn","Lộc","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018018);
        ContactItem it2018046= new ContactItem(2018046,"Nguyễn Hữu","Đức","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018046);
        ContactItem it2018054= new ContactItem(2018054,"Phạm Ngọc","Hanh","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018054);
        ContactItem it2018081= new ContactItem(2018081,"Phạm Văn","Sang","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018081);
        ContactItem it2018103= new ContactItem(2018103,"Nguyễn Thị","Yến","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018103);
        ContactItem it2018104= new ContactItem(2018104,"Phan Thanh","Diệp","last seen June 7, 2018 12:23","0912345678",0,""); listData.add(it2018104);

        return listData;

    }

}