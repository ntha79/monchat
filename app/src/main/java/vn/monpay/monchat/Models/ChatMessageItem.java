package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.monpay.monchat.SessionInfo;
import vn.monpay.monchat.Utilities.F;
public class ChatMessageItem
{
    private int ownerId = 0;			//Y nghia:
    private int fromId = 0;			//Y nghia:
    private int toId = 0;			//Y nghia:
    private String code = "";			//Y nghia:
    private String fromFullName = "";			//Y nghia:
    private String toFullName = "";			//Y nghia:
    private int status = 0;			//Y nghia:
    private String message = "";			//Y nghia:
    private String lastTime = "";			//Y nghia:
    private boolean isSent = false;			//Y nghia:
    private boolean isReceived = false;			//Y nghia:
    private boolean isRead = false;			//Y nghia:
    private String timeSend = "";			//Y nghia:
    private String timeReceived = "";			//Y nghia:
    private String timeRead = "";			//Y nghia:
    private int msgType = 0;			//Y nghia:

    public ChatMessageItem( int ownerIdValue, int fromIdValue, int toIdValue, String codeValue, String fromFullNameValue, String toFullNameValue, int statusValue, String messageValue, String lastTimeValue, boolean isSentValue, boolean isReceivedValue, boolean isReadValue, String timeSendValue, String timeReceivedValue, String timeReadValue, int msgTypeValue)
    {

        ownerId = ownerIdValue;
        fromId = fromIdValue;
        toId = toIdValue;
        code = codeValue;
        fromFullName = fromFullNameValue;
        toFullName = toFullNameValue;
        status = statusValue;
        message = messageValue;
        lastTime = lastTimeValue;
        isSent = isSentValue;
        isReceived = isReceivedValue;
        isRead = isReadValue;
        timeSend = timeSendValue;
        timeReceived = timeReceivedValue;
        timeRead = timeReadValue;
        msgType = msgTypeValue;
    }

    public ChatMessageItem()
    {

        ownerId = 0;
        fromId = 0;
        toId = 0;
        code = "";
        fromFullName = "";
        toFullName = "";
        status = 0;
        message = "";
        lastTime = "";
        isSent = false;
        isReceived = false;
        isRead = false;
        timeSend = "";
        timeReceived = "";
        timeRead = "";
        msgType = 0;
    }

    public ChatMessageItem(String JSONString)
    {

        ownerId = 0;
        fromId = 0;
        toId = 0;
        code = "";
        fromFullName = "";
        toFullName = "";
        status = 0;
        message = "";
        lastTime = "";
        isSent = false;
        isReceived = false;
        isRead = false;
        timeSend = "";
        timeReceived = "";
        timeRead = "";
        msgType = 0;
        try {
            JSONObject obj = new JSONObject(JSONString);

            ownerId = F.IntIsNull(obj.getInt("ownerId"));
            fromId = F.IntIsNull(obj.getInt("fromId"));
            toId = F.IntIsNull(obj.getInt("toId"));
            code = F.StringIsNull(obj.getString("code"));
            fromFullName = F.StringIsNull(obj.getString("fromFullName"));
            toFullName = F.StringIsNull(obj.getString("toFullName"));
            status = F.IntIsNull(obj.getInt("status"));
            message = F.StringIsNull(obj.getString("message"));
            lastTime = F.StringIsNull(obj.getString("lastTime"));
            isSent = F.BoolIsNull(obj.getBoolean("isSent"));
            isReceived = F.BoolIsNull(obj.getBoolean("isReceived"));
            isRead = F.BoolIsNull(obj.getBoolean("isRead"));
            timeSend = F.StringIsNull(obj.getString("timeSend"));
            timeReceived = F.StringIsNull(obj.getString("timeReceived"));
            timeRead = F.StringIsNull(obj.getString("timeRead"));
            msgType = F.IntIsNull(obj.getInt("msgType"));
        } catch (JSONException e) {}

    }
    public static List<ChatMessageItem> GetListFromJSONString(String JSONlistData)
    {

        List<ChatMessageItem> listData = new ArrayList<ChatMessageItem>();
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
                    ChatMessageItem it = new ChatMessageItem(curJsonObject.toString());
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
    public int getFromId(){ return fromId;}
    public String getFromIdString(){ return "" + fromId;}
    public void setFromId(int  fromId){ this.fromId = fromId;}
    public int getToId(){ return toId;}
    public String getToIdString(){ return "" + toId;}
    public void setToId(int  toId){ this.toId = toId;}
    public String getCode(){ return code;}
    public void setCode(String  code){ this.code = code;}
    public String getFromFullName(){ return fromFullName;}
    public void setFromFullName(String  fromFullName){ this.fromFullName = fromFullName;}
    public String getToFullName(){ return toFullName;}
    public void setToFullName(String  toFullName){ this.toFullName = toFullName;}
    public int getStatus(){ return status;}
    public String getStatusString(){ return "" + status;}
    public void setStatus(int  status){ this.status = status;}
    public String getMessage(){ return message;}
    public void setMessage(String  message){ this.message = message;}
    public String getLastTime(){ return lastTime;}
    public void setLastTime(String  lastTime){ this.lastTime = lastTime;}
    public boolean getIsSent(){ return isSent;}
    public void setIsSent(boolean  isSent){ this.isSent = isSent;}
    public boolean getIsReceived(){ return isReceived;}
    public void setIsReceived(boolean  isReceived){ this.isReceived = isReceived;}
    public boolean getIsRead(){ return isRead;}
    public void setIsRead(boolean  isRead){ this.isRead = isRead;}
    public String getTimeSend(){ return timeSend;}
    public void setTimeSend(String  timeSend){ this.timeSend = timeSend;}
    public String getTimeReceived(){ return timeReceived;}
    public void setTimeReceived(String  timeReceived){ this.timeReceived = timeReceived;}
    public String getTimeRead(){ return timeRead;}
    public void setTimeRead(String  timeRead){ this.timeRead = timeRead;}
    public int getMsgType(){ return msgType;}
    public String getMsgTypeString(){ return "" + msgType;}
    public void setMsgType(int  msgType){ this.msgType = msgType;}


    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("ownerId",ownerId);
            object.put("fromId",fromId);
            object.put("toId",toId);
            object.put("code",code);
            object.put("fromFullName",fromFullName);
            object.put("toFullName",toFullName);
            object.put("status",status);
            object.put("message",message);
            object.put("lastTime",lastTime);
            object.put("isSent",isSent);
            object.put("isReceived",isReceived);
            object.put("isRead",isRead);
            object.put("timeSend",timeSend);
            object.put("timeReceived",timeReceived);
            object.put("timeRead",timeRead);
            object.put("msgType",msgType);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }


    public static List<ChatMessageItem> listDataChatMessageItem = new ArrayList<>();
    public static List<ChatMessageItem> Get(int toIdValue)
    {
        List<ChatMessageItem> listData = new ArrayList<ChatMessageItem>();
        for (ChatMessageItem obj:listDataChatMessageItem
             ) {
            if(obj.getOwnerId()== SessionInfo.getOwnerId() &&(obj.getFromId()==toIdValue||obj.getToId()==toIdValue))
                listData.add(obj);
        }
        return listData;
    }
    public static ChatMessageItem Add( int ownerIdValue, int fromIdValue, int toIdValue,String fromFullNameValue, String toFullNameValue,  String messageValue1)
    {
        String codeValue = F.FormatTopic(""+fromIdValue,""+toIdValue);
        int statusValue = 0;
        String lastTimeValue = F.DateToStringMMM_dd_yyyy_HH_mm_ss(new Date());
        boolean isSentValue =true;
        boolean isReceivedValue = true;
        boolean isReadValue = true;
        String timeSendValue =lastTimeValue;
        String timeReceivedValue=lastTimeValue;
        String timeReadValue=lastTimeValue;
        int msgTypeValue = 0;
        String messageValue = messageValue1;
        if(messageValue.startsWith("QC:"))
        {
            messageValue = messageValue.substring(3);
            msgTypeValue = 1;
        }

        ChatMessageItem item = new ChatMessageItem(ownerIdValue,  fromIdValue,  toIdValue,  codeValue, fromFullNameValue, toFullNameValue,  statusValue,  messageValue,  lastTimeValue,  isSentValue,  isReceivedValue,  isReadValue,
                timeSendValue,  timeReceivedValue,  timeReadValue,msgTypeValue);
        listDataChatMessageItem.add(item);
        return item;
    }
}