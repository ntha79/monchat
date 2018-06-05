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
    private String isSecret = "";			//Y nghia:
    private String status = "";			//Y nghia:
    private String lastMessage = "";			//Y nghia:
    private String lastTime = "";			//Y nghia:
    private String isReceived = "";			//Y nghia:
    private String isRead = "";			//Y nghia:
    private String timeReceived = "";			//Y nghia:
    private String timeRead = "";			//Y nghia:

    public ChatTitleItem( int baseIdValue, int fromIdValue, int toIdValue, String codeValue, String fullNameValue, String isSecretValue, String statusValue, String lastMessageValue, String lastTimeValue, String isReceivedValue, String isReadValue, String timeReceivedValue, String timeReadValue)
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
        isSecret = "";
        status = "";
        lastMessage = "";
        lastTime = "";
        isReceived = "";
        isRead = "";
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
        isSecret = "";
        status = "";
        lastMessage = "";
        lastTime = "";
        isReceived = "";
        isRead = "";
        timeReceived = "";
        timeRead = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            baseId = F.IntIsNull(obj.getInt("baseId"));
            fromId = F.IntIsNull(obj.getInt("fromId"));
            toId = F.IntIsNull(obj.getInt("toId"));
            code = F.StringIsNull(obj.getString("code"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            isSecret = F.StringIsNull(obj.getString("isSecret"));
            status = F.StringIsNull(obj.getString("status"));
            lastMessage = F.StringIsNull(obj.getString("lastMessage"));
            lastTime = F.StringIsNull(obj.getString("lastTime"));
            isReceived = F.StringIsNull(obj.getString("isReceived"));
            isRead = F.StringIsNull(obj.getString("isRead"));
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
    public String getIsSecret(){ return isSecret;}
    public void setIsSecret(String  isSecret){ this.isSecret = isSecret;}
    public String getStatus(){ return status;}
    public void setStatus(String  status){ this.status = status;}
    public String getLastMessage(){ return lastMessage;}
    public void setLastMessage(String  lastMessage){ this.lastMessage = lastMessage;}
    public String getLastTime(){ return lastTime;}
    public void setLastTime(String  lastTime){ this.lastTime = lastTime;}
    public String getIsReceived(){ return isReceived;}
    public void setIsReceived(String  isReceived){ this.isReceived = isReceived;}
    public String getIsRead(){ return isRead;}
    public void setIsRead(String  isRead){ this.isRead = isRead;}
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

}