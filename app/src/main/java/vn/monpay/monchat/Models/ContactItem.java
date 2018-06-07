package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Utilities.F;

public class ContactItem
{
    private int baseId;
    private String fullName;
    private String lastMessage;
    private int status;
    private String note;

    public ContactItem( int baseIdValue, String fullNameValue, String lastMessageValue, int statusValue, String noteValue)
    {

        baseId = baseIdValue;
        fullName = fullNameValue;
        lastMessage = lastMessageValue;
        status = statusValue;
        note = noteValue;
    }

    public ContactItem()
    {

        baseId = 0;
        fullName = "";
        lastMessage = "";
        status = 0;
        note = "";
    }

    public ContactItem(String JSONString)
    {

        baseId = 0;
        fullName = "";
        lastMessage = "";
        status = 0;
        note = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            baseId = F.IntIsNull(obj.getInt("baseId"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            lastMessage = F.StringIsNull(obj.getString("lastMessage"));
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
    public String getLastMessage(){ return lastMessage;}
    public void setLastMessage(String  lastMessage){ this.lastMessage = lastMessage;}
    public int getStatus(){ return status;}
    public String getStatusString(){ return "" + status;}
    public void setStatus(int  status){ this.status = status;}
    public String getNote(){ return note;}
    public void setNote(String  note){ this.note = note;}



    public String getShortName(){ return fullName.substring(0,2).toUpperCase();}

    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("baseId",baseId);
            object.put("fullName",fullName);
            object.put("lastMessage",lastMessage);
            object.put("status",status);
            object.put("note",note);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
    public static List<ContactItem> GetListDemo()
    {

        List<ContactItem> listData = new ArrayList<ContactItem>();
        ContactItem it2018001= new ContactItem(2018001,"Vũ Thị Lan Anh","last seen June 7, 2018 12:23",0,""); listData.add(it2018001);
        ContactItem it2018002= new ContactItem(2018002,"Trần Thị Vân Anh","last seen June 7, 2018 12:23",0,""); listData.add(it2018002);
        ContactItem it2018003= new ContactItem(2018003,"Hoàng Ngọc Ánh","last seen June 7, 2018 12:23",0,""); listData.add(it2018003);
        ContactItem it2018004= new ContactItem(2018004,"Đặng Thị Đạt","last seen June 7, 2018 12:23",0,""); listData.add(it2018004);
        ContactItem it2018005= new ContactItem(2018005,"Phạm Thị Kim Diện","last seen June 7, 2018 12:23",0,""); listData.add(it2018005);
        ContactItem it2018006= new ContactItem(2018006,"Lưu Thùy Dung","last seen June 7, 2018 12:23",0,""); listData.add(it2018006);
        ContactItem it2018007= new ContactItem(2018007,"Ngô Thị Thùy Dương","last seen June 7, 2018 12:23",0,""); listData.add(it2018007);
        ContactItem it2018008= new ContactItem(2018008,"Vũ Thị Thu Hà","last seen June 7, 2018 12:23",0,""); listData.add(it2018008);
        ContactItem it2018009= new ContactItem(2018009,"Vũ Thị Hạnh","last seen June 7, 2018 12:23",0,""); listData.add(it2018009);
        ContactItem it2018010= new ContactItem(2018010,"Nông Thị Hương","last seen June 7, 2018 12:23",0,""); listData.add(it2018010);
        ContactItem it2018011= new ContactItem(2018011,"Đỗ Thị Thu Huyền","last seen June 7, 2018 12:23",0,""); listData.add(it2018011);
        ContactItem it2018012= new ContactItem(2018012,"Ngô Thị Thanh  Huyền","last seen June 7, 2018 12:23",0,""); listData.add(it2018012);
        ContactItem it2018013= new ContactItem(2018013,"Nguyễn Thị Quỳnh Khanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018013);
        ContactItem it2018014= new ContactItem(2018014,"Lê Thị Kim","last seen June 7, 2018 12:23",0,""); listData.add(it2018014);
        ContactItem it2018015= new ContactItem(2018015,"Đào Thị Liên","last seen June 7, 2018 12:23",0,""); listData.add(it2018015);
        ContactItem it2018016= new ContactItem(2018016,"Triệu Thị Linh","last seen June 7, 2018 12:23",0,""); listData.add(it2018016);
        ContactItem it2018017= new ContactItem(2018017,"Nguyễn Thị Linh","last seen June 7, 2018 12:23",0,""); listData.add(it2018017);
        ContactItem it2018018= new ContactItem(2018018,"Ngô Văn Lộc","last seen June 7, 2018 12:23",0,""); listData.add(it2018018);
        ContactItem it2018019= new ContactItem(2018019,"Bùi Thị Lưu","last seen June 7, 2018 12:23",0,""); listData.add(it2018019);
        ContactItem it2018020= new ContactItem(2018020,"Nguyễn Thị Tố Nga","last seen June 7, 2018 12:23",0,""); listData.add(it2018020);
        ContactItem it2018021= new ContactItem(2018021,"Trương Thị Nhã","last seen June 7, 2018 12:23",0,""); listData.add(it2018021);
        ContactItem it2018022= new ContactItem(2018022,"Lê Thị Nhiên","last seen June 7, 2018 12:23",0,""); listData.add(it2018022);
        ContactItem it2018023= new ContactItem(2018023,"Bùi Thị Hồng Nhung","last seen June 7, 2018 12:23",0,""); listData.add(it2018023);
        ContactItem it2018024= new ContactItem(2018024,"Nguyễn Thị Nhung","last seen June 7, 2018 12:23",0,""); listData.add(it2018024);
        ContactItem it2018025= new ContactItem(2018025,"Nguyễn Đặng Xuân Oanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018025);
        ContactItem it2018026= new ContactItem(2018026,"Tống Thị Phượng","last seen June 7, 2018 12:23",0,""); listData.add(it2018026);
        ContactItem it2018027= new ContactItem(2018027,"Trần Thị Thắm","last seen June 7, 2018 12:23",0,""); listData.add(it2018027);
        ContactItem it2018028= new ContactItem(2018028,"Dương Thị Thảo","last seen June 7, 2018 12:23",0,""); listData.add(it2018028);
        ContactItem it2018029= new ContactItem(2018029,"Đinh Thị Thoa","last seen June 7, 2018 12:23",0,""); listData.add(it2018029);
        ContactItem it2018030= new ContactItem(2018030,"Lò Thị Thúy","last seen June 7, 2018 12:23",0,""); listData.add(it2018030);
        ContactItem it2018031= new ContactItem(2018031,"Ngô Thị Thùy","last seen June 7, 2018 12:23",0,""); listData.add(it2018031);
        ContactItem it2018032= new ContactItem(2018032,"Nguyễn Thị Thủy","last seen June 7, 2018 12:23",0,""); listData.add(it2018032);
        ContactItem it2018033= new ContactItem(2018033,"Đặng Thị Thanh Thủy","last seen June 7, 2018 12:23",0,""); listData.add(it2018033);
        ContactItem it2018034= new ContactItem(2018034,"Trịnh Thị Trang","last seen June 7, 2018 12:23",0,""); listData.add(it2018034);
        ContactItem it2018035= new ContactItem(2018035,"Đặng Thị Hà Trang","last seen June 7, 2018 12:23",0,""); listData.add(it2018035);
        ContactItem it2018036= new ContactItem(2018036,"Trương Thị Hà Trang","last seen June 7, 2018 12:23",0,""); listData.add(it2018036);
        ContactItem it2018037= new ContactItem(2018037,"Nguyễn Thanh Tuấn","last seen June 7, 2018 12:23",0,""); listData.add(it2018037);
        ContactItem it2018038= new ContactItem(2018038,"Đào Thị Ánh Tuyết","last seen June 7, 2018 12:23",0,""); listData.add(it2018038);
        ContactItem it2018039= new ContactItem(2018039,"Vũ Thị Bạch Xuyến","last seen June 7, 2018 12:23",0,""); listData.add(it2018039);
        ContactItem it2018040= new ContactItem(2018040,"Hoàng Lan Anh","last seen June 7, 2018 12:23",0,""); listData.add(it2018040);
        ContactItem it2018041= new ContactItem(2018041,"Ngọ Thị Lan Anh","last seen June 7, 2018 12:23",0,""); listData.add(it2018041);
        ContactItem it2018042= new ContactItem(2018042,"Phạm Thị Chinh","last seen June 7, 2018 12:23",0,""); listData.add(it2018042);
        ContactItem it2018043= new ContactItem(2018043,"Bùi Văn Chính","last seen June 7, 2018 12:23",0,""); listData.add(it2018043);
        ContactItem it2018044= new ContactItem(2018044,"Hoàng Duy Đại","last seen June 7, 2018 12:23",0,""); listData.add(it2018044);
        ContactItem it2018045= new ContactItem(2018045,"Nguyễn Thị Đào","last seen June 7, 2018 12:23",0,""); listData.add(it2018045);
        ContactItem it2018046= new ContactItem(2018046,"Nguyễn Hữu  Đức","last seen June 7, 2018 12:23",0,""); listData.add(it2018046);
        ContactItem it2018047= new ContactItem(2018047,"Nguyễn Thị Dung","last seen June 7, 2018 12:23",0,""); listData.add(it2018047);
        ContactItem it2018048= new ContactItem(2018048,"Đinh Thị Duyên","last seen June 7, 2018 12:23",0,""); listData.add(it2018048);
        ContactItem it2018049= new ContactItem(2018049,"Hồ Thị Gấm","last seen June 7, 2018 12:23",0,""); listData.add(it2018049);
        ContactItem it2018050= new ContactItem(2018050,"Đào Thị Giang","last seen June 7, 2018 12:23",0,""); listData.add(it2018050);
        ContactItem it2018051= new ContactItem(2018051,"Dương Thị Giảng","last seen June 7, 2018 12:23",0,""); listData.add(it2018051);
        ContactItem it2018052= new ContactItem(2018052,"Nguyễn Thị Hải","last seen June 7, 2018 12:23",0,""); listData.add(it2018052);
        ContactItem it2018053= new ContactItem(2018053,"Thân Thị Hằng","last seen June 7, 2018 12:23",0,""); listData.add(it2018053);
        ContactItem it2018054= new ContactItem(2018054,"Phạm Ngọc Hanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018054);
        ContactItem it2018055= new ContactItem(2018055,"Nguyễn Thị Huế","last seen June 7, 2018 12:23",0,""); listData.add(it2018055);
        ContactItem it2018056= new ContactItem(2018056,"Phạm Thị Huế","last seen June 7, 2018 12:23",0,""); listData.add(it2018056);
        ContactItem it2018057= new ContactItem(2018057,"Trần Thị Thu Hương","last seen June 7, 2018 12:23",0,""); listData.add(it2018057);
        ContactItem it2018058= new ContactItem(2018058,"Phùng Thị Hường","last seen June 7, 2018 12:23",0,""); listData.add(it2018058);
        ContactItem it2018059= new ContactItem(2018059,"Nguyễn Thị Huyên","last seen June 7, 2018 12:23",0,""); listData.add(it2018059);
        ContactItem it2018060= new ContactItem(2018060,"Hoàng Thị Thanh Huyền","last seen June 7, 2018 12:23",0,""); listData.add(it2018060);
        ContactItem it2018061= new ContactItem(2018061,"Nguyễn Thị Huyền","last seen June 7, 2018 12:23",0,""); listData.add(it2018061);
        ContactItem it2018062= new ContactItem(2018062,"Hà Thị Lành","last seen June 7, 2018 12:23",0,""); listData.add(it2018062);
        ContactItem it2018063= new ContactItem(2018063,"Lê Thị Liên","last seen June 7, 2018 12:23",0,""); listData.add(it2018063);
        ContactItem it2018064= new ContactItem(2018064,"Nguyễn Thị Thùy Linh","last seen June 7, 2018 12:23",0,""); listData.add(it2018064);
        ContactItem it2018065= new ContactItem(2018065,"Nguyễn Thị Châu Loan","last seen June 7, 2018 12:23",0,""); listData.add(it2018065);
        ContactItem it2018066= new ContactItem(2018066,"Trịnh Văn Lộc","last seen June 7, 2018 12:23",0,""); listData.add(it2018066);
        ContactItem it2018067= new ContactItem(2018067,"Nguyễn Thanh Mai","last seen June 7, 2018 12:23",0,""); listData.add(it2018067);
        ContactItem it2018068= new ContactItem(2018068,"Đoàn Thị Quỳnh Mai","last seen June 7, 2018 12:23",0,""); listData.add(it2018068);
        ContactItem it2018069= new ContactItem(2018069,"Hoàng Thị Na","last seen June 7, 2018 12:23",0,""); listData.add(it2018069);
        ContactItem it2018070= new ContactItem(2018070,"Vũ Thị Thanh Nga","last seen June 7, 2018 12:23",0,""); listData.add(it2018070);
        ContactItem it2018071= new ContactItem(2018071,"Vũ Thị Kim Ngân","last seen June 7, 2018 12:23",0,""); listData.add(it2018071);
        ContactItem it2018072= new ContactItem(2018072,"Nguyễn Thị Thúy Ngân","last seen June 7, 2018 12:23",0,""); listData.add(it2018072);
        ContactItem it2018073= new ContactItem(2018073,"Bùi Thị Ngọc","last seen June 7, 2018 12:23",0,""); listData.add(it2018073);
        ContactItem it2018074= new ContactItem(2018074,"Đinh Thị Ngọt","last seen June 7, 2018 12:23",0,""); listData.add(it2018074);
        ContactItem it2018075= new ContactItem(2018075,"Trương Thị Hồng Nhung","last seen June 7, 2018 12:23",0,""); listData.add(it2018075);
        ContactItem it2018076= new ContactItem(2018076,"Nguyễn Thị Nữ","last seen June 7, 2018 12:23",0,""); listData.add(it2018076);
        ContactItem it2018077= new ContactItem(2018077,"Nguyễn Thị Oanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018077);
        ContactItem it2018078= new ContactItem(2018078,"Nguyễn Thị Phương","last seen June 7, 2018 12:23",0,""); listData.add(it2018078);
        ContactItem it2018079= new ContactItem(2018079,"Cấn Đỗ Như Quyên","last seen June 7, 2018 12:23",0,""); listData.add(it2018079);
        ContactItem it2018080= new ContactItem(2018080,"Nguyễn Thị Quỳnh","last seen June 7, 2018 12:23",0,""); listData.add(it2018080);
        ContactItem it2018081= new ContactItem(2018081,"Phạm Văn Sang","last seen June 7, 2018 12:23",0,""); listData.add(it2018081);
        ContactItem it2018082= new ContactItem(2018082,"Trần Thị Thắm","last seen June 7, 2018 12:23",0,""); listData.add(it2018082);
        ContactItem it2018083= new ContactItem(2018083,"Nguyễn Thị Thu Thanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018083);
        ContactItem it2018084= new ContactItem(2018084,"Phạm Thị Thanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018084);
        ContactItem it2018085= new ContactItem(2018085,"Vũ Thị Thảo","last seen June 7, 2018 12:23",0,""); listData.add(it2018085);
        ContactItem it2018086= new ContactItem(2018086,"Nguyễn Thị Thảo","last seen June 7, 2018 12:23",0,""); listData.add(it2018086);
        ContactItem it2018087= new ContactItem(2018087,"Hoàng Thị Tới","last seen June 7, 2018 12:23",0,""); listData.add(it2018087);
        ContactItem it2018088= new ContactItem(2018088,"Nguyễn Thị Tuyến","last seen June 7, 2018 12:23",0,""); listData.add(it2018088);
        ContactItem it2018089= new ContactItem(2018089,"Tô Thị Ngọc Ánh","last seen June 7, 2018 12:23",0,""); listData.add(it2018089);
        ContactItem it2018090= new ContactItem(2018090,"Bùi Thị Thu Hà","last seen June 7, 2018 12:23",0,""); listData.add(it2018090);
        ContactItem it2018091= new ContactItem(2018091,"Nguyễn Thị Hải","last seen June 7, 2018 12:23",0,""); listData.add(it2018091);
        ContactItem it2018092= new ContactItem(2018092,"Lê Thị Thúy Hạnh","last seen June 7, 2018 12:23",0,""); listData.add(it2018092);
        ContactItem it2018093= new ContactItem(2018093,"Bùi Thị Mai Hồng","last seen June 7, 2018 12:23",0,""); listData.add(it2018093);
        ContactItem it2018094= new ContactItem(2018094,"Nguyễn Thị Huyền","last seen June 7, 2018 12:23",0,""); listData.add(it2018094);
        ContactItem it2018095= new ContactItem(2018095,"Nguyễn Thị Thành Lý","last seen June 7, 2018 12:23",0,""); listData.add(it2018095);
        ContactItem it2018096= new ContactItem(2018096,"Trần Thị Trà Mi","last seen June 7, 2018 12:23",0,""); listData.add(it2018096);
        ContactItem it2018097= new ContactItem(2018097,"Trần Thị Ngọc","last seen June 7, 2018 12:23",0,""); listData.add(it2018097);
        ContactItem it2018098= new ContactItem(2018098,"Khuất Minh Nguyệt","last seen June 7, 2018 12:23",0,""); listData.add(it2018098);
        ContactItem it2018099= new ContactItem(2018099,"Trần Thị Minh Phương","last seen June 7, 2018 12:23",0,""); listData.add(it2018099);
        ContactItem it2018100= new ContactItem(2018100,"Nguyễn Thị Thơm","last seen June 7, 2018 12:23",0,""); listData.add(it2018100);
        ContactItem it2018101= new ContactItem(2018101,"Đặng Thị Thanh Thúy","last seen June 7, 2018 12:23",0,""); listData.add(it2018101);
        ContactItem it2018102= new ContactItem(2018102,"Tạ Thị Vụ","last seen June 7, 2018 12:23",0,""); listData.add(it2018102);
        ContactItem it2018103= new ContactItem(2018103,"Nguyễn Thị Yến","last seen June 7, 2018 12:23",0,""); listData.add(it2018103);
        ContactItem it2018104= new ContactItem(2018104,"Phan Thanh Diệp","last seen June 7, 2018 12:23",0,""); listData.add(it2018104);
        ContactItem it2018105= new ContactItem(2018105,"Hoàng Thị Mai","last seen June 7, 2018 12:23",0,""); listData.add(it2018105);
        ContactItem it2018106= new ContactItem(2018106,"Trần Thị Oanh","last seen June 7, 2018 12:23",0,""); listData.add(it2018106);

        return listData;

    }

}