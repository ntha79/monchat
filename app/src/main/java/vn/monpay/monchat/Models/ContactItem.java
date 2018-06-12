package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.monpay.monchat.SessionInfo;
import vn.monpay.monchat.Utilities.F;
public class ContactItem
{
    private int ownerId = 0;			//Y nghia: 
    private int id = 0;			//Y nghia: 
    private String login = "";			//Y nghia: 
    private String firstName = "";			//Y nghia: 
    private String lastName = "";			//Y nghia: 
    private String email = "";			//Y nghia: 
    private String imageUrl = "";			//Y nghia: 
    private String fullName = "";			//Y nghia: 
    private String mobilePhone = "";			//Y nghia: 
    private int status = 0;			//Y nghia: 
    private String note = "";			//Y nghia: 
    private String lastMessage = "";			//Y nghia: 

    public ContactItem( int ownerIdValue, int idValue, String loginValue,
                        String firstNameValue, String lastNameValue, String emailValue,
                        String imageUrlValue, String fullNameValue, String mobilePhoneValue,
                        int statusValue, String noteValue, String lastMessageValue)
    {

        ownerId = ownerIdValue;
        id = idValue;
        login = loginValue;
        firstName = firstNameValue;
        lastName = lastNameValue;
        email = emailValue;
        imageUrl = imageUrlValue;
        fullName = fullNameValue;
        mobilePhone = mobilePhoneValue;
        status = statusValue;
        note = noteValue;
        lastMessage = lastMessageValue;


        if(F.isEmpty(fullName)) {
            fullName = firstName;
            if (!F.isEmpty(lastName))
                fullName = fullName + " " + lastName;
        }
    }

    public ContactItem()
    {

        ownerId = 0;
        id = 0;
        login = "";
        firstName = "";
        lastName = "";
        email = "";
        imageUrl = "";
        fullName = "";
        mobilePhone = "";
        status = 0;
        note = "";
        lastMessage = "";
    }

    public ContactItem(String JSONString)
    {

        ownerId = 0;
        id = 0;
        login = "";
        firstName = "";
        lastName = "";
        email = "";
        imageUrl = "";
        fullName = "";
        mobilePhone = "";
        status = 0;
        note = "";
        lastMessage = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            ownerId = F.IntIsNull(obj.getInt("ownerId"));
            id = F.IntIsNull(obj.getInt("id"));
            login = F.StringIsNull(obj.getString("login"));
            firstName = F.StringIsNull(obj.getString("firstName"));
            lastName = F.StringIsNull(obj.getString("lastName"));
            email = F.StringIsNull(obj.getString("email"));
            imageUrl = F.StringIsNull(obj.getString("imageUrl"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            mobilePhone = F.StringIsNull(obj.getString("mobilePhone"));
            status = F.IntIsNull(obj.getInt("status"));
            note = F.StringIsNull(obj.getString("note"));
            lastMessage = F.StringIsNull(obj.getString("lastMessage"));
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
    public int getOwnerId(){ return ownerId;}
    public String getOwnerIdString(){ return "" + ownerId;}
    public void setOwnerId(int  ownerId){ this.ownerId = ownerId;}
    public int getId(){ return id;}
    public String getIdString(){ return "" + id;}
    public void setId(int  id){ this.id = id;}
    public String getLogin(){ return login;}
    public void setLogin(String  login){ this.login = login;}
    public String getFirstName(){ return firstName;}
    public void setFirstName(String  firstName){ this.firstName = firstName;}
    public String getLastName(){ return lastName;}
    public void setLastName(String  lastName){ this.lastName = lastName;}
    public String getEmail(){ return email;}
    public void setEmail(String  email){ this.email = email;}
    public String getImageUrl(){ return imageUrl;}
    public void setImageUrl(String  imageUrl){ this.imageUrl = imageUrl;}
    public String getFullName(){ return fullName;}
    public void setFullName(String  fullName){ this.fullName = fullName;}
    public String getMobilePhone(){ return mobilePhone;}
    public void setMobilePhone(String  mobilePhone){ this.mobilePhone = mobilePhone;}
    public int getStatus(){ return status;}
    public String getStatusString(){ return "" + status;}
    public void setStatus(int  status){ this.status = status;}
    public String getNote(){ return note;}
    public void setNote(String  note){ this.note = note;}
    public String getLastMessage(){ return lastMessage;}
    public void setLastMessage(String  lastMessage){ this.lastMessage = lastMessage;}

    public String getShowName(){
        String fullName1 = firstName;
        if (!F.isEmpty(lastName))
            fullName1 = fullName1 + " " + lastName;
        return fullName1;
    }

    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("ownerId",ownerId);
            object.put("id",id);
            object.put("login",login);
            object.put("firstName",firstName);
            object.put("lastName",lastName);
            object.put("email",email);
            object.put("imageUrl",imageUrl);
            object.put("fullName",fullName);
            object.put("mobilePhone",mobilePhone);
            object.put("status",status);
            object.put("note",note);
            object.put("lastMessage",lastMessage);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
    public String getShortName(){ return fullName.substring(0,2).toUpperCase();}

    public static List<ContactItem> listDataContactItem = new ArrayList<>();

    public static List<ContactItem> GetListDemo()
    {

        List<ContactItem> listData = new ArrayList<ContactItem>();
        ContactItem it2018001= new ContactItem(SessionInfo.getOwnerId(),1,"system","Vũ Thị","Lan Anh","system@localhost","","",
                "0912345678",0,"","last seen June 7, 2018 12:23"); listData.add(it2018001);


        ContactItem it2018002= new ContactItem(SessionInfo.getOwnerId(),3,"admin","Vũ","Kim Diệu","admin@localhost","","",
                "0912345679",0,"","last seen June 7, 2018 12:24"); listData.add(it2018002);

        ContactItem it2018003= new ContactItem(SessionInfo.getOwnerId(),4,"user","Ngô Hồng","User","user@localhost","","",
                "0912345680",0,"","last seen June 7, 2018 12:25"); listData.add(it2018003);


        ContactItem it2018004= new ContactItem(SessionInfo.getOwnerId(),7,"devpro1","Lê Nguyễn","Devpro1","devpro1@localhost","","",
                "0912345681",0,"","last seen June 7, 2018 12:29"); listData.add(it2018004);

        ContactItem it2018008= new ContactItem(SessionInfo.getOwnerId(),8,"devpro2","Trần Văn","Devpro2","devpro2@localhost","","",
                "0912345681",0,"","last seen June 7, 2018 12:29"); listData.add(it2018008);

        ContactItem it2018009= new ContactItem(SessionInfo.getOwnerId(),9,"devpro3","Vũ Trần","Devpro3","devpro3@localhost","","",
                "0912345681",0,"","last seen June 7, 2018 12:29"); listData.add(it2018009);



        return listData;

    }

    public static void Edit(int ownerIdValue, int idValue,  String firstNameValue, String lastNameValue, String mobilePhoneValue, String emailValue)
    {
        boolean isExits = false;
        for (ContactItem value:listDataContactItem
                ) {
            if(idValue>0) {
                if (value.getId() == idValue) {
                    isExits = true;
                    value.setFirstName(firstNameValue);
                    value.setLastName(lastNameValue);
                    value.setMobilePhone(mobilePhoneValue);
                    value.setLastMessage("last seen " + F.DateToStringMMM_dd_yyyy_HH_mm_ss(new Date()));


                }
            }
            else
            {
                if (value.getMobilePhone().equals(mobilePhoneValue)||value.getEmail().equals(emailValue)) {
                    isExits = true;
                    value.setFirstName(firstNameValue);
                    value.setLastName(lastNameValue);
                    value.setMobilePhone(mobilePhoneValue);
                    value.setLastMessage("last seen " + F.DateToStringMMM_dd_yyyy_HH_mm_ss(new Date()));
                }
            }

        }
        if(!isExits)
        {
            int id= idValue;
            if(id<1)
                id= F.IntIsNull(mobilePhoneValue);
            ContactItem it2018001= new ContactItem(SessionInfo.getOwnerId(),id,""+id,firstNameValue,lastNameValue,emailValue,"","",
                    mobilePhoneValue,0,"","last seen " + F.DateToStringMMM_dd_yyyy_HH_mm_ss(new Date()));
            listDataContactItem.add(it2018001);

        }
    }
}