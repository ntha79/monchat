package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Utilities.F;
/**
 * Created by mobilechatsystem@gmail.com on 06/05/2018.
 */
public class ChatTitleItem
{
    private int baseId = 0;			//Y nghia:
    private int fromId = 0;			//Y nghia:
    private int toId = 0;			//Y nghia:
    private String code = "";			//Y nghia:
    private String fullName = "";			//Y nghia:
    private boolean isSecret = false;			//Y nghia:
    private int status = 0;			//Y nghia:
    private String lastMessage = "";			//Y nghia:
    private String lastTime = "";			//Y nghia:
    private boolean isReceived = false;			//Y nghia:
    private boolean isRead = false;			//Y nghia:
    private String timeReceived = "";			//Y nghia:
    private String timeRead = "";			//Y nghia:

    public ChatTitleItem( int baseIdValue, int fromIdValue, int toIdValue, String codeValue, String fullNameValue, boolean isSecretValue, int statusValue, String lastMessageValue, String lastTimeValue, boolean isReceivedValue, boolean isReadValue, String timeReceivedValue, String timeReadValue)
    {

        baseId = baseIdValue;
        fromId = fromIdValue;
        toId = toIdValue;
        code = codeValue;
        fullName = fullNameValue;
        isSecret = isSecretValue;
        status = statusValue;
        lastMessage = lastMessageValue;
        lastTime = lastTimeValue;
        isReceived = isReceivedValue;
        isRead = isReadValue;
        timeReceived = timeReceivedValue;
        timeRead = timeReadValue;
    }

    public ChatTitleItem()
    {

        baseId = 0;
        fromId = 0;
        toId = 0;
        code = "";
        fullName = "";
        isSecret = false;
        status = 0;
        lastMessage = "";
        lastTime = "";
        isReceived = false;
        isRead = false;
        timeReceived = "";
        timeRead = "";
    }

    public ChatTitleItem(String JSONString)
    {

        baseId = 0;
        fromId = 0;
        toId = 0;
        code = "";
        fullName = "";
        isSecret = false;
        status = 0;
        lastMessage = "";
        lastTime = "";
        isReceived = false;
        isRead = false;
        timeReceived = "";
        timeRead = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            baseId = F.IntIsNull(obj.getInt("baseId"));
            fromId = F.IntIsNull(obj.getInt("fromId"));
            toId = F.IntIsNull(obj.getInt("toId"));
            code = F.StringIsNull(obj.getString("code"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            isSecret = F.BoolIsNull(obj.getBoolean("isSecret"));
            status = F.IntIsNull(obj.getInt("status"));
            lastMessage = F.StringIsNull(obj.getString("lastMessage"));
            lastTime = F.StringIsNull(obj.getString("lastTime"));
            isReceived = F.BoolIsNull(obj.getBoolean("isReceived"));
            isRead = F.BoolIsNull(obj.getBoolean("isRead"));
            timeReceived = F.StringIsNull(obj.getString("timeReceived"));
            timeRead = F.StringIsNull(obj.getString("timeRead"));
        } catch (JSONException e) {}

    }
    public static List<ChatTitleItem> GetListFromJSONString(String JSONlistData)
    {

        List<ChatTitleItem> listData = new ArrayList<ChatTitleItem>();
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
                    ChatTitleItem it = new ChatTitleItem(curJsonObject.toString());
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
    public int getFromId(){ return fromId;}
    public String getFromIdString(){ return "" + fromId;}
    public void setFromId(int  fromId){ this.fromId = fromId;}
    public int getToId(){ return toId;}
    public String getToIdString(){ return "" + toId;}
    public void setToId(int  toId){ this.toId = toId;}
    public String getCode(){ return code;}
    public void setCode(String  code){ this.code = code;}
    public String getFullName(){ return fullName;}
    public void setFullName(String  fullName){ this.fullName = fullName;}
    public boolean getIsSecret(){ return isSecret;}
    public void setIsSecret(boolean  isSecret){ this.isSecret = isSecret;}
    public int getStatus(){ return status;}
    public String getStatusString(){ return "" + status;}
    public void setStatus(int  status){ this.status = status;}
    public String getLastMessage(){ return lastMessage;}
    public void setLastMessage(String  lastMessage){ this.lastMessage = lastMessage;}
    public String getLastTime(){ return lastTime;}
    public void setLastTime(String  lastTime){ this.lastTime = lastTime;}
    public boolean getIsReceived(){ return isReceived;}
    public void setIsReceived(boolean  isReceived){ this.isReceived = isReceived;}
    public boolean getIsRead(){ return isRead;}
    public void setIsRead(boolean  isRead){ this.isRead = isRead;}
    public String getTimeReceived(){ return timeReceived;}
    public void setTimeReceived(String  timeReceived){ this.timeReceived = timeReceived;}
    public String getTimeRead(){ return timeRead;}
    public void setTimeRead(String  timeRead){ this.timeRead = timeRead;}


    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("baseId",baseId);
            object.put("fromId",fromId);
            object.put("toId",toId);
            object.put("code",code);
            object.put("fullName",fullName);
            object.put("isSecret",isSecret);
            object.put("status",status);
            object.put("lastMessage",lastMessage);
            object.put("lastTime",lastTime);
            object.put("isReceived",isReceived);
            object.put("isRead",isRead);
            object.put("timeReceived",timeReceived);
            object.put("timeRead",timeRead);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }


    public String getShortName(){ return fullName.substring(0,2).toUpperCase();}

    public static List<ChatTitleItem> GetListDemo()
    {

        List<ChatTitleItem> listData = new ArrayList<ChatTitleItem>();
        ChatTitleItem it = new ChatTitleItem(1528127149,1528127149,2018001,"2018001","Vũ Thị Lan Anh",true,0,"Abc def...","12:34",true,true,"12:34","12:34");
        listData.add(it);
        ChatTitleItem it2 = new ChatTitleItem(1528127149,1528127149,1528127141,"1528127149","Nguyen Van .NET",false,0,"Hi bạn...","12:35",true,false,"12:35","12:35");
        listData.add(it2);
        ChatTitleItem it3 = new ChatTitleItem(1528127149,1528127149,1528127140,"1528127149","Group 123",false,1,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it3);
        ChatTitleItem it4 = new ChatTitleItem(1528127149,1528127149,1528127142,"1528127149","ABC 345",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it4);
        ChatTitleItem it5 = new ChatTitleItem(1528127149,1528127149,1528127143,"1528127149","ABC 5678",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it5);
        ChatTitleItem it6 = new ChatTitleItem(1528127149,1528127149,1528127150,"1528127149","ABC 900",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it6);
        ChatTitleItem it7 = new ChatTitleItem(1528127149,1528127149,1528127151,"1528127149","ABC 680",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it7);
        ChatTitleItem it8 = new ChatTitleItem(1528127149,1528127149,1528127144,"1528127149","ABC 5678",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it8);
        ChatTitleItem it9 = new ChatTitleItem(1528127149,1528127149,1528127145,"1528127149","ABC 900 TRTY",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it9);
        ChatTitleItem it0 = new ChatTitleItem(1528127149,1528127149,1528127146,"1528127149","ABC 680RRE RRY",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it0);
        ChatTitleItem it10 = new ChatTitleItem(1528127149,1528127149,1528127147,"1528127149","ABC FFFF RRY",false,0,"Last message...","12:35",true,false,"12:35","12:35");
        listData.add(it10);
        return listData;

    }
}