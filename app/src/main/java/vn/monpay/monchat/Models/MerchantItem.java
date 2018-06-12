package vn.monpay.monchat.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Utilities.F;

public class MerchantItem
{
    private String merchantId;
    private String fullName;
    private String address;
    private String mobilePhone;
    private String info;
    private String weblink;
    private String classification;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private Double discount = 0d;

    public MerchantItem( String merchantIdValue, String fullNameValue, String addressValue, String mobilePhoneValue, String infoValue, String weblinkValue, String classificationValue, Double latitudeValue, Double longitudeValue, Double distanceValue)
    {

        merchantId = merchantIdValue;
        fullName = fullNameValue;
        address = addressValue;
        mobilePhone = mobilePhoneValue;
        info = infoValue;
        weblink = weblinkValue;
        classification = classificationValue;
        latitude = latitudeValue;
        longitude = longitudeValue;
        distance = distanceValue;
    }

    public MerchantItem()
    {

        merchantId = "";
        fullName = "";
        address = "";
        mobilePhone = "";
        info = "";
        weblink = "";
        classification = "";
        latitude = 0.0;
        longitude = 0.0;
        distance = 0.0;
    }

    public MerchantItem(String JSONString)
    {

        merchantId = "";
        fullName = "";
        address = "";
        mobilePhone = "";
        info = "";
        weblink = "";
        classification = "";
        latitude = 0.0;
        longitude = 0.0;
        distance = 0.0;
        try {
            JSONObject obj = new JSONObject(JSONString);

            merchantId = F.StringIsNull(obj.getString("merchantId"));
            fullName = F.StringIsNull(obj.getString("fullName"));
            address = F.StringIsNull(obj.getString("address"));
            mobilePhone = F.StringIsNull(obj.getString("mobilePhone"));
            info = F.StringIsNull(obj.getString("info"));
            weblink = F.StringIsNull(obj.getString("weblink"));
            classification = F.StringIsNull(obj.getString("classification"));
            latitude = F.DoubleIsNull(obj.getDouble("latitude"));
            longitude = F.DoubleIsNull(obj.getDouble("longitude"));
            distance = F.DoubleIsNull(obj.getDouble("distance"));
        } catch (JSONException e) {}

    }
    public static List<MerchantItem> GetListFromJSONString(String JSONlistData)
    {

        List<MerchantItem> listData = new ArrayList<MerchantItem>();
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
                    MerchantItem it = new MerchantItem(curJsonObject.toString());
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return listData;
    }
    public String getMerchantId(){ return merchantId;}
    public void setMerchantId(String  merchantId){ this.merchantId = merchantId;}
    public String getFullName(){ return fullName;}
    public void setFullName(String  fullName){ this.fullName = fullName;}
    public String getAddress(){ return address;}
    public void setAddress(String  address){ this.address = address;}
    public String getMobilePhone(){ return mobilePhone;}
    public void setMobilePhone(String  mobilePhone){ this.mobilePhone = mobilePhone;}
    public String getInfo(){ return info;}
    public void setInfo(String  info){ this.info = info;}
    public String getWeblink(){ return weblink;}
    public void setWeblink(String  weblink){ this.weblink = weblink;}
    public String getClassification(){ return classification;}
    public void setClassification(String  classification){ this.classification = classification;}
    public Double getLatitude(){ return latitude;}
    public String getLatitudeString(){ if(latitude == null) return ""; return latitude.toString();}
    public void setLatitude(Double  latitude){ this.latitude = latitude;}
    public Double getLongitude(){ return longitude;}
    public String getLongitudeString(){ if(longitude == null) return ""; return longitude.toString();}
    public void setLongitude(Double  longitude){ this.longitude = longitude;}
    public Double getDistance(){ return distance;}
    public String getDistanceString(){ if(distance == null) return ""; return F.FormatDistance(distance);}
    public void setDistance(Double  distance){ this.distance = distance;}
    public Double getDiscount(){ return discount;}
    public String getDiscountString(){ if(discount == null) return ""; return ""+discount.intValue()+"%";}
    public void setDiscount(Double  discount){ this.discount = discount;}

    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("merchantId",merchantId);
            object.put("fullName",fullName);
            object.put("address",address);
            object.put("mobilePhone",mobilePhone);
            object.put("info",info);
            object.put("weblink",weblink);
            object.put("classification",classification);
            object.put("latitude",latitude);
            object.put("longitude",longitude);
            object.put("distance",distance);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
    public static List<MerchantItem> GetListDemo()
    {

        List<MerchantItem> listData = new ArrayList<MerchantItem>();
        MerchantItem itCN0000001 = new MerchantItem("MERCHANTCN0000001","Ngân hàng Vietcombank", "34 Bát Đàn, Hoàn Kiếm, Hà Nội", "(024) 3923 2932(024) 3923 2933","mobilechatsystem@gmail.com","https://www.vietcombank.com.vn","Tài chính, ngân hàng",21.0337390000d,105.8470970000d,100d); listData.add(itCN0000001);
        MerchantItem itCN0000002 = new MerchantItem("MERCHANTCN0000002","Ngân hàng BIDV", "191 Bà Triệu, Hai Bà Trưng, Hà Nội", "(024) 3944 6368(024) 3944 6387","mobilechatsystem@gmail.com","http://www.bidv.com.vn","Tài chính, ngân hàng",21.0093210000d,105.8490810000d,150d); listData.add(itCN0000002);
        MerchantItem itCN0000003 = new MerchantItem("MERCHANTCN0000003","Ngân hàng Tiên Phong", "Số 32 An Dương, Tây Hồ, Hà Nội", "(024) 3775 3366","mobilechatsystem@gmail.com","https://tpb.vn","Tài chính, ngân hàng",21.0553410000d,105.8383830000d,200d); listData.add(itCN0000003);
        MerchantItem itCN0000004 = new MerchantItem("MERCHANTCN0000004","Chi nhánh Ba Đình", "168 Ngọc Khánh, Giảng Võ,, Ba Đình, Hà Nội", "(024) 3724.5959(024) 3724.5858","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0253340000d,105.8186720000d,250d);
        itCN0000004.setDiscount(15d);
        listData.add(itCN0000004);
        MerchantItem itCN0000005 = new MerchantItem("MERCHANTCN0000005","Chi nhánh Bách Khoa", "136 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội", "(024) 3868 4918(024) 3868 4916","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0019460000d,105.8453940000d,300d);
        itCN0000005.setDiscount(25d);
        listData.add(itCN0000005);
        MerchantItem itCN0000006 = new MerchantItem("MERCHANTCN0000006","Chi nhánh Big C", "16 lô 11A Trung Hoà, Cầu Giấy, Hà Nội", "(024) 3783 0668(024) 3783 0671","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0151860000d,105.8013300000d,350d);
        itCN0000006.setDiscount(30d);
        listData.add(itCN0000006);
        MerchantItem itCN0000007 = new MerchantItem("MERCHANTCN0000007","Chi nhánh Bờ Hồ   ", "32 Hàng Da, phường Cửa Đông, Hoàn Kiếm, Hà Nội", "(024) 3938 2828(024) 3938 2829","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0306720000d,105.8466170000d,400d); listData.add(itCN0000007);
        MerchantItem itCN0000008 = new MerchantItem("MERCHANTCN0000008","Chi nhánh Cát Linh   ", "Số 98 Tôn Đức Thắng, Đống Đa, Hà Nội", "(024) 6275 4183(024) 6275 4184","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0271360000d,105.8339190000d,450d);
        itCN0000008.setDiscount(30d);
        listData.add(itCN0000008);
        MerchantItem itCN0000009 = new MerchantItem("MERCHANTCN0000009","Chi nhánh Chợ Mơ", "297 Bạch Mai, Hai Bà Trưng, Hà Nội", "(024) 3624 7088(024) 3624 7099","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0022700000d,105.8507280000d,500d); listData.add(itCN0000009);
        MerchantItem itCN0000010 = new MerchantItem("MERCHANTCN0000010","Chi nhánh Chương Dương", "414 Nguyễn Văn Cừ, Long Biên, Hà Nội", "(024) 3872 2222(024) 3872 2242","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0465080000d,105.8780810000d,550d); listData.add(itCN0000010);
        MerchantItem itCN0000011 = new MerchantItem("MERCHANTCN0000011","Chi nhánh Ciputra", "Tòa Nhà E4 - Khu Đô thị Nam Thăng Long, Tây Hồ, Hà Nội", "(024) 3743 0176(024) 3743 0175","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0715730000d,105.8092190000d,600d);
        itCN0000011.setDiscount(45d);
        listData.add(itCN0000011);
        MerchantItem itCN0000012 = new MerchantItem("MERCHANTCN0000012","Chi nhánh Cửa Bắc", "Só nhà 70, Cửa Bắc, Ba Đình, Hà Nội", "(024) 6273 3023(024) 6273 3094","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0428730000d,105.8426930000d,650d); listData.add(itCN0000012);
        MerchantItem itCN0000013 = new MerchantItem("MERCHANTCN0000013","Chi nhánh Cửa Nam", "67 phố Cửa Nam, phường Cửa Nam, Hoàn Kiếm, Hà Nội", "(024) 3936 4455(024) 3936 4454","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0262390000d,105.8436250000d,700d); listData.add(itCN0000013);
        MerchantItem itCN0000014 = new MerchantItem("MERCHANTCN0000014","Chi nhánh Đan Phượng", "135 Tây Sơn, thị trấn Phùng, Đan Phượng, Hà Nội", "(024) 3388 7889(024) 3388 7456","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0826660000d,105.6743170000d,750d); listData.add(itCN0000014);
        MerchantItem itCN0000015 = new MerchantItem("MERCHANTCN0000015","Chi nhánh Đặng Văn Ngữ", "Số 28 phố Nam Đồng, phường Nam Đồng, Đống Đa, Hà Nội", "(024) 6275 5337 ","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0158390000d,105.8306550000d,800d); listData.add(itCN0000015);
        MerchantItem itATM0000029 = new MerchantItem("MERCHANTATM0000029","ATM Hàng Hành", "24 Hàng Hành, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0314060000d,105.8502130000d,850d); listData.add(itATM0000029);
        MerchantItem itCN0000016 = new MerchantItem("MERCHANTCN0000016","Chi nhánh Đào Tấn", "37 Đào Tấn, Ngọc Khánh, Ba Đình, Hà Nội", "(024) 3760 6018(024) 3766 7510","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0327120000d,105.8076560000d,900d); listData.add(itCN0000016);
        MerchantItem itCN0000017 = new MerchantItem("MERCHANTCN0000017","Chi nhánh Định Công", "52 Vương Thừa Vũ, Khương Trung, Thanh Xuân, Hà Nội", "(024) 3665 8518(024) 3568 3657","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9998660000d,105.8219190000d,950d); listData.add(itCN0000017);
        MerchantItem itCN0000018 = new MerchantItem("MERCHANTCN0000018","Chi nhánh Đội Cấn", "285 Đội Cấn, Phường Liếu giai, Ba Đình, Hà Nội", "(024) 3722 5318(024) 3722 5320","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0358220000d,105.8190120000d,1000d); listData.add(itCN0000018);
        MerchantItem itCN0000019 = new MerchantItem("MERCHANTCN0000019","Chi nhánh Đông Anh", "Số 2 Cao Lỗ, Uy Nỗ, Đông Anh, Hà Nội", "(024) 3965 5346(024) 3965 5347","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1381610000d,105.8585360000d,1050d); listData.add(itCN0000019);
        MerchantItem itCN0000020 = new MerchantItem("MERCHANTCN0000020","Chi nhánh Đống Đa", "208 Thái Hà, Đống Đa, Hà Nội", "(024) 3537 3586(024) 3537 3565","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0135660000d,105.8191600000d,1100d); listData.add(itCN0000020);
        MerchantItem itCN0000021 = new MerchantItem("MERCHANTCN0000021","Chi nhánh Đông Đô", "Tầng 1, Tòa nhà 29 T2, Đường Hoàng Đạo Thúy, Cầu Giấy, Hà Nội", "(024) 6251 1032(024) 6251 1035","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0088010000d,105.8013010000d,1150d); listData.add(itCN0000021);
        MerchantItem itCN0000022 = new MerchantItem("MERCHANTCN0000022","Chi nhánh Đồng Xuân", "62 Hàng Giấy, Hoàn Kiếm, Hà Nội", "(024) 3927 6868","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0384640000d,105.8484520000d,1200d); listData.add(itCN0000022);
        MerchantItem itCN0000023 = new MerchantItem("MERCHANTCN0000023","Chi nhánh Hà Nội", "15 Đào Duy Từ, Hoàn Kiếm, Hà Nội", "(024) 3824 3941(024) 3825 0545","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0361360000d,105.8527530000d,1250d); listData.add(itCN0000023);
        MerchantItem itCN0000024 = new MerchantItem("MERCHANTCN0000024","Chi nhánh Hà Tây", "Tầng 1, Tòa nhà CT2, đường Ngô Thì Nhậm, Hà Đông, Hà Nội", "(024) 3311 9950(024) 3311 9962","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9687000000d,105.7686910000d,1300d); listData.add(itCN0000024);
        MerchantItem itCN0000025 = new MerchantItem("MERCHANTCN0000025","Chi nhánh Hà Thành", "74 Bà Triệu, Hoàn Kiếm, Hà Nội", "(024) 3863 9999(024) 3944 0101","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0200640000d,105.8496940000d,1350d); listData.add(itCN0000025);
        MerchantItem itCN0000026 = new MerchantItem("MERCHANTCN0000026","Chi nhánh Hai Bà Trưng", "9 Tô Hiến Thành, Hai Bà Trưng, Hà Nội", "(024) 3972 5550(024) 3972 5551","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0135430000d,105.8511280000d,1400d); listData.add(itCN0000026);
        MerchantItem itCN0000027 = new MerchantItem("MERCHANTCN0000027","Chi nhánh Hàng Đậu", "25 B Phan Đình Phùng, Ba Đình, Hà Nội", "(024) 3733 6868(024) 3734 4566","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0400810000d,105.8446340000d,1450d); listData.add(itCN0000027);
        MerchantItem itCN0000028 = new MerchantItem("MERCHANTCN0000028","Chi nhánh Hoàn Kiếm", "97 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội", "(024) 3942 6868(024) 3942 8845","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0236300000d,105.8431280000d,1500d); listData.add(itCN0000028);
        MerchantItem itCN0000029 = new MerchantItem("MERCHANTCN0000029","Chi nhánh Hoàng Cầu", "63 phố Hoàng Cầu, Đống Đa, Hà Nội", "(024) 6275 0087(024) 6275 0092","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0166030000d,105.8238580000d,1550d); listData.add(itCN0000029);
        MerchantItem itCN0000030 = new MerchantItem("MERCHANTCN0000030","Chi nhánh Hoàng Gia", "Tầng B1 khu TTTM TP Hoàng Gia (Royal City) 72 Nguyễn Trãi, Thanh Xuân, Hà Nội", "(024) 6664 1818","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0036120000d,105.8145520000d,1600d); listData.add(itCN0000030);
        MerchantItem itCN0000031 = new MerchantItem("MERCHANTCN0000031","Chi nhánh Hoàng Hoa Thám", "141 Hoàng Hoa Thám, Ngọc Hà, Ba Đình, Hà Nội", "(024) 2223 2468(024) 2223 2467","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0404470000d,105.8246900000d,1650d); listData.add(itCN0000031);
        MerchantItem itCN0000032 = new MerchantItem("MERCHANTCN0000032","Chi nhánh Hoàng Mai", "Tòa nhà Resco, B15 Khu đô thị mới Đại Kim, Đại Kim, Hoàng Mai, Hà Nội", "(024) 6284 2637(024) 6284 2635","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9758280000d,105.8366610000d,1700d); listData.add(itCN0000032);
        MerchantItem itCN0000033 = new MerchantItem("MERCHANTCN0000033","Chi nhánh Hoàng Quốc Việt", "98 Hoàng Quốc Việt, Cầu Giấy, Hà Nội", "(024) 3748 0412(024) 3755 6565","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0462750000d,105.7958460000d,1750d); listData.add(itCN0000033);
        MerchantItem itCN0000034 = new MerchantItem("MERCHANTCN0000034","Chi nhánh Huỳnh Thúc Kháng", "Số 21 Huỳnh Thúc Kháng, Láng Hạ, Đống Đa, Hà Nội", "(024) 7300 0999(024) 7305 9119","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0177470000d,105.8122360000d,1800d); listData.add(itCN0000034);
        MerchantItem itCN0000035 = new MerchantItem("MERCHANTCN0000035","Chi nhánh KeangNam", "B101 Tòa nhà Keangnam, E6 Phạm Hùng, Cầu Giấy, Hà Nội", "(024) 3837 8999(024) 3837 8124","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0177080000d,105.7834430000d,1850d); listData.add(itCN0000035);
        MerchantItem itCN0000036 = new MerchantItem("MERCHANTCN0000036","Chi nhánh Khâm Thiên", "228 Khâm Thiên, Đống Đa, Hà Nội", "(024) 3511 3096","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0192620000d,105.8343240000d,1900d); listData.add(itCN0000036);
        MerchantItem itCN0000037 = new MerchantItem("MERCHANTCN0000037","Chi nhánh Khương Mai", "70 Hoàng Văn Thái, Khương Mai, Thanh Xuân, Hà Nội", "(024) 3566 5366(024) 3566 5367","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9994180000d,105.8279730000d,1950d); listData.add(itCN0000037);
        MerchantItem itCN0000038 = new MerchantItem("MERCHANTCN0000038","Chi nhánh Kim Liên", "Tầng 2 B14, Phạm Ngọc Thạch, P. Kim Liên, Đống Đa, Hà Nội", "(024) 2222 9966 (024) 2220 4296","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0085720000d,105.8343400000d,2000d); listData.add(itCN0000038);
        MerchantItem itCN0000039 = new MerchantItem("MERCHANTCN0000039","Chi nhánh Kim Mã", "07 Kim Mã, phường Kim Mã, Ba Đình, Hà Nội", "(024) 3734 5588(024) 3734 9091","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0318750000d,105.8256240000d,2050d); listData.add(itCN0000039);
        MerchantItem itCN0000040 = new MerchantItem("MERCHANTCN0000040","Chi nhánh Lạc Long Quân", "667 Lạc Long Quân, phường Xuân La, Tây Hồ, Hà Nội", "(024) 6258 1440(024) 6258 1439","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0711390000d,105.8120270000d,2100d); listData.add(itCN0000040);
        MerchantItem itCN0000041 = new MerchantItem("MERCHANTCN0000041","Chi nhánh Lạc Trung", "23 Lạc Trung, Hai Bà Trưng, Hà Nội", "(024) 6327 8951","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0024610000d,105.8639840000d,2150d); listData.add(itCN0000041);
        MerchantItem itCN0000042 = new MerchantItem("MERCHANTCN0000042","Chi nhánh Láng Hạ", "Tầng 1 – 2, tòa nhà 101 Láng Hạ, Đống Đa, Hà Nội", "(024) 3730 1968/3730 1988(024) 3519 1137/3519 1138","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0130000000d,105.8127250000d,2200d); listData.add(itCN0000042);
        MerchantItem itCN0000043 = new MerchantItem("MERCHANTCN0000043","Chi nhánh Linh Đàm", "Tầng 1 CC2A, Khu đô thị mới Bắc Linh Đàm, Đại Kim, Hoàng Mai, Hà Nội", "(024) 3641 6088(024) 3641 6090","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9697590000d,105.8267880000d,2250d); listData.add(itCN0000043);
        MerchantItem itCN0000044 = new MerchantItem("MERCHANTCN0000044","Chi nhánh Lĩnh Nam", "18 Tam Trinh, Minh Khai, Hai Bà Trưng, Hà Nội", "(024) 3632 1265(024) 3632 1260","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9939610000d,105.8619660000d,2300d); listData.add(itCN0000044);
        MerchantItem itCN0000045 = new MerchantItem("MERCHANTCN0000045","Chi nhánh Lò Đúc", "108 Lò Đúc, Hai Bà Trưng, Hà Nội", "(024) 3972 8116(024) 3972 3456","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0132940000d,105.8578650000d,2350d); listData.add(itCN0000045);
        MerchantItem itCN0000046 = new MerchantItem("MERCHANTCN0000046","Chi nhánh Long Biên", "K1-2 và K1-3, TTTM Vincom Long Biên, phường Phúc Lợi, Long Biên, Hà Nội", "(024) 3212 7762","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0504620000d,105.9160650000d,2400d); listData.add(itCN0000046);
        MerchantItem itCN0000047 = new MerchantItem("MERCHANTCN0000047","Chi nhánh Lý Nam Đế", "95F Lý Nam Đế, Hoàn Kiếm, Hà Nội", "(024) 3747 5568(024) 3747 5569","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0305110000d,105.8441050000d,2450d); listData.add(itCN0000047);
        MerchantItem itCN0000048 = new MerchantItem("MERCHANTCN0000048","Chi nhánh Lý Thái Tổ ", "30 Lý Thái Tổ, Hoàn Kiếm, Hà Nội", "(024) 3926 3405(024) 3926 3405","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0298100000d,105.8551020000d,2500d); listData.add(itCN0000048);
        MerchantItem itCN0000049 = new MerchantItem("MERCHANTCN0000049","Chi nhánh Lý Thường Kiệt", "57A Phan Chu Trinh, Hoàn Kiếm, Hà Nội", "(024) 3943 0714(024) 3824 9877","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0190790000d,105.8553730000d,2550d); listData.add(itCN0000049);
        MerchantItem itCN0000050 = new MerchantItem("MERCHANTCN0000050","Chi nhánh Mê Linh", "Phố Yên, Tiền Phong, Mê Linh, Hà Nội", "(024) 3818 5778(024) 3818 5780","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1530600000d,105.7573180000d,2600d); listData.add(itCN0000050);
        MerchantItem itCN0000051 = new MerchantItem("MERCHANTCN0000051","Chi nhánh Ngã Tư Sở", "78 Nguyễn Trãi, Thượng Đình, Thanh Xuân, Hà Nội", "(024) 3562 6156(024) 3562 6157","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0008660000d,105.8169360000d,2650d); listData.add(itCN0000051);
        MerchantItem itCN0000052 = new MerchantItem("MERCHANTCN0000052","Chi nhánh Ngô Gia Tự", "46 Ngô Gia Tự, Long Biên, Hà Nội", "(024) 3652 5176(024) 3652 5175","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0588370000d,105.8940020000d,2700d); listData.add(itCN0000052);
        MerchantItem itCN0000053 = new MerchantItem("MERCHANTCN0000053","Chi nhánh Ngọc Khánh", "52 Nguyễn Chí Thanh, Đống Đa, Hà Nội", "(024) 3775 5389(024) 3775 5385","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0242510000d,105.8103270000d,2750d); listData.add(itCN0000053);
        MerchantItem itCN0000054 = new MerchantItem("MERCHANTCN0000054","Chi nhánh Ngọc Lâm", "60 phố Ngọc Lâm, Phường Ngọc Lâm, Long Biên, Hà Nội", "(024) 3873 7865(024) 3873 7867","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0468990000d,105.8722240000d,2800d); listData.add(itCN0000054);
        MerchantItem itCN0000055 = new MerchantItem("MERCHANTCN0000055","Chi nhánh Nguyễn An Ninh", "Số 65 Nguyễn An Ninh, Hoàng Mai, Hà Nội", "(024) 3662 8812","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9905380000d,105.8450260000d,2850d); listData.add(itCN0000055);
        MerchantItem itCN0000056 = new MerchantItem("MERCHANTCN0000056","Chi nhánh Nguyễn Cơ Thạch", "Tòa nhà C3, Khu đô thị Mỹ Đình 1, đường Nguyễn Cơ Thạch, Từ Liêm, Hà Nội", "(024) 6287 1746(024) 6287 1976","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0312480000d,105.7694510000d,2900d); listData.add(itCN0000056);
        MerchantItem itCN0000057 = new MerchantItem("MERCHANTCN0000057","Chi nhánh Nguyễn Thị Định", "Tầng 1, Tòa nhà 18T1 Khu Đô Thị Trung Hòa Nhân Chính, Cầu Giấy, Hà Nội", "(024) 6251 3015(024) 6251 3017","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0061650000d,105.8061500000d,2950d); listData.add(itCN0000057);
        MerchantItem itCN0000058 = new MerchantItem("MERCHANTCN0000058","Chi nhánh Nhuệ Giang", "56- 58 Lê Lợi, phường Nguyễn Trãi, Hà Đông, Hà Nội", "(024) 3352 9876(024) 3352 9877","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9706860000d,105.7780580000d,3000d); listData.add(itCN0000058);
        MerchantItem itCN0000059 = new MerchantItem("MERCHANTCN0000059","Chi nhánh Nội Bài", "Khu đất số 8, Cảng hàng không Quốc tế Nội Bài, Sóc Sơn, Hà Nội", "(024) 584 0538(024) 584 0539","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.2187150000d,105.8041710000d,3050d); listData.add(itCN0000059);
        MerchantItem itCN0000060 = new MerchantItem("MERCHANTCN0000060","Chi nhánh Phan Bội Châu", "92 Yết Kiêu, Hai Bà Trưng, Hà Nội", "(024) 3941 3931(024) 3941 3930","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0197150000d,105.8424550000d,3100d); listData.add(itCN0000060);
        MerchantItem itCN0000061 = new MerchantItem("MERCHANTCN0000061","Chi nhánh Phủ Lỗ  ", "Số 148, đường 2, xã Phủ Lỗ, Sóc Sơn, Hà Nội", "(024) 3884 9305(024) 3884 9306","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1980510000d,105.8401350000d,3150d); listData.add(itCN0000061);
        MerchantItem itCN0000062 = new MerchantItem("MERCHANTCN0000062","Chi nhánh Phùng Xá", "Số 09, đường 419 xã Phùng Xá, Thạch Thất, Hà Nội", "(024) 3310 3688(024) 3310 3988","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0116700000d,105.6206750000d,3200d); listData.add(itCN0000062);
        MerchantItem itCN0000063 = new MerchantItem("MERCHANTCN0000063","Chi nhánh Phương Mai", "20 Trường Chinh, Phương Mai, Đống Đa, Hà Nội", "(024) 3868 9999(024) 3868 9899","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9988610000d,105.8378520000d,3250d); listData.add(itCN0000063);
        MerchantItem itCN0000064 = new MerchantItem("MERCHANTCN0000064","Chi nhánh Sóc Sơn", "Khu C Thị Trấn Sóc Sơn, Đông Anh, Hà Nội", "(024) 3595 5399(024) 3595 5398","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.2582200000d,105.8482840000d,3300d); listData.add(itCN0000064);
        MerchantItem itCN0000065 = new MerchantItem("MERCHANTCN0000065","Chi nhánh Sơn Tây", "269 - 271 Chùa Thông, Sơn Tây, Hà Nội", "(024) 3361 8968(024) 3361 8768","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1247300000d,105.5014890000d,3350d); listData.add(itCN0000065);
        MerchantItem itCN0000066 = new MerchantItem("MERCHANTCN0000066","Chi nhánh Tây Hồ", "110 Lạc Long Quân, Tây Hồ, Hà Nội", "(024) 3753 3535(024) 3759 2565","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0510660000d,105.8080070000d,3400d); listData.add(itCN0000066);
        MerchantItem itCN0000067 = new MerchantItem("MERCHANTCN0000067","Chi nhánh Thái Thịnh", "Lô số 109 tầng 1, 102 Thái Thịnh, Đống Đa, Hà Nội", "(024) 3537 8288(024) 3537 8656","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0085440000d,105.8205180000d,3450d); listData.add(itCN0000067);
        MerchantItem itCN0000068 = new MerchantItem("MERCHANTCN0000068","Chi nhánh Thăng Long", "181 Nguyễn Lương Bằng, Đống Đa, Hà Nội", "(024) 2220 8989(024) 2220 3646","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0136900000d,105.8271170000d,3500d); listData.add(itCN0000068);
        MerchantItem itCN0000069 = new MerchantItem("MERCHANTCN0000069","Chi nhánh Thanh Xuân", "467 Nguyễn Trãi, Thanh Xuân, Hà Nội", "(024) 3554 2698(024) 3554 2706","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9902660000d,105.8019170000d,3550d); listData.add(itCN0000069);
        MerchantItem itCN0000070 = new MerchantItem("MERCHANTCN0000070","Chi nhánh The Manor", "Một phần diện tích tầng 1 tòa nhà HH3, Sudico - Khu đô thị Mỹ Đình Mễ Trì, Phường Mỹ Đình 1, quận Nam Từ Liêm, Từ Liêm, Hà Nội", "(024) 3787 8999(024) 3787 8889","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0165180000d,105.7795290000d,3600d); listData.add(itCN0000070);
        MerchantItem itCN0000071 = new MerchantItem("MERCHANTCN0000071","Chi nhánh Thụy Khuê", "152 Thụy Khuê, Tây Hồ, Hà Nội", "(024) 3728 2631(024) 3728 2633","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0430730000d,105.8228090000d,3650d); listData.add(itCN0000071);
        MerchantItem itCN0000072 = new MerchantItem("MERCHANTCN0000072","Chi nhánh Trần Bình", "Căn hộ N3C1 nhà số 3 khu nhà ở để bán, Phường Mỹ Đình, Từ Liêm, Hà Nội", "(024) 6287 0061(024) 6287 0038","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0312480000d,105.7694510000d,3700d); listData.add(itCN0000072);
        MerchantItem itCN0000073 = new MerchantItem("MERCHANTCN0000073","Chi nhánh Trần Điền", "Ki ốt 3-4 nhà CT5- Đơn Nguyên 2 Khu ĐTM Định Công, Hoàng Mai, Hà Nội", "(024) 3640 0596(024) 3640 0597","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9889440000d,105.8301040000d,3750d); listData.add(itCN0000073);
        MerchantItem itCN0000074 = new MerchantItem("MERCHANTCN0000074","Chi nhánh Trần Duy Hưng", "Tầng 1 - CT1, Tòa nhà Vimeco, Nguyễn Chánh, Cầu Giấy, Hà Nội", "(024) 2225 0286(024) 2225 0288","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0087860000d,105.7944790000d,3800d); listData.add(itCN0000074);
        MerchantItem itCN0000075 = new MerchantItem("MERCHANTCN0000075","Chi nhánh Trần Thái Tông", "Tầng 1, Tòa nhà Sunrise, D11 phố Trần Thái Tông, Cầu Giấy, Hà Nội", "(024) 3795 0505(024) 3795 8244","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0307020000d,105.7877790000d,3850d); listData.add(itCN0000075);
        MerchantItem itCN0000076 = new MerchantItem("MERCHANTCN0000076","Chi nhánh Tràng An", "Tầng 1, T4- Times City, 458 Minh Khai, Hai Bà Trưng, Hà Nội", "(024) 3932 9888(024) 3829 3666","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9953270000d,105.8697710000d,3900d); listData.add(itCN0000076);
        MerchantItem itCN0000077 = new MerchantItem("MERCHANTCN0000077","Chi nhánh Trung Hòa", "Cánh 3 Tầng 1 Tòa nhà Trung Yên Plaza, Khu đô thị Trung Yên, phường Trung Hòa, Cầu Giấy, Hà Nội", "(024) 7308 0999","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0138000000d,105.8018480000d,3950d); listData.add(itCN0000077);
        MerchantItem itCN0000078 = new MerchantItem("MERCHANTCN0000078","Chi nhánh Trương Định", "593 Trương Định, Giáp Bát, Hoàng Mai, Hà Nội", "(024) 3642 5033(024) 3642 5032","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9813730000d,105.8453360000d,4000d); listData.add(itCN0000078);
        MerchantItem itCN0000079 = new MerchantItem("MERCHANTCN0000079","Chi nhánh Từ Liêm", "40 Phố Nhổn, P. Tây Tựu, Từ Liêm, Hà Nội", "(024) 378 05197(024) 3780 5200","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0549380000d,105.7319850000d,4050d); listData.add(itCN0000079);
        MerchantItem itCN0000080 = new MerchantItem("MERCHANTCN0000080","Chi nhánh Vạn Phúc", "Số 8 ngõ 6 phố Vĩnh Phúc,Phường Vĩnh Phúc, Ba Đình, Hà Nội", "(024) 3568 3556(024) 3568 3554","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0443620000d,105.8088860000d,4100d); listData.add(itCN0000080);
        MerchantItem itCN0000081 = new MerchantItem("MERCHANTCN0000081","Chi nhánh Văn Quán   ", "Số 10 - 12 đường 19/5 phường Văn Quán, Hà Đông, Hà Nội", "(024) 3354 3327(024) 3354 7656","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9749410000d,105.7878110000d,4150d); listData.add(itCN0000081);
        MerchantItem itCN0000082 = new MerchantItem("MERCHANTCN0000082","Chi nhánh Việt Hưng", "108 K10 khu ĐTM Việt Hưng, Giang Biên, Long Biên, Hà Nội", "(024) 3657 4404(024) 3657 4403","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0646310000d,105.9080730000d,4200d); listData.add(itCN0000082);
        MerchantItem itCN0000083 = new MerchantItem("MERCHANTCN0000083","Chi nhánh Xa La", "Số 2 biệt thự 8 khu đô thị Xa La, Hà Đông, Hà Nội", "(024) 3311 5673(024) 3311 5675","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9628320000d,105.7920500000d,4250d); listData.add(itCN0000083);
        MerchantItem itCN0000084 = new MerchantItem("MERCHANTCN0000084","Chi nhánh Xuân Diệu", "Số 46 Xuân Diệu, phường Quảng An, Tây Hồ, Hà Nội", "(024) 3718 5594(024) 3718 5584","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0617820000d,105.8308380000d,4300d); listData.add(itCN0000084);
        MerchantItem itATM0000035 = new MerchantItem("MERCHANTATM0000035","ATM Láng Hạ", "23 Láng Hạ, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0210780000d,105.8181340000d,4350d); listData.add(itATM0000035);
        MerchantItem itATM0000036 = new MerchantItem("MERCHANTATM0000036","ATM Láng Hạ 4", "105 Láng Hạ, Đống Đa, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0133860000d,105.8128520000d,4400d); listData.add(itATM0000036);
        MerchantItem itATM0000001 = new MerchantItem("MERCHANTATM0000001","ATM - B - 00330 Liễu Giai", "Tòa nhà Lotte, số 54 Liễu Giai, quận Ba Đình, Ba Đình, Hà Nội", "(0243) 942 7444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0321680000d,105.8124270000d,4450d); listData.add(itATM0000001);
        MerchantItem itATM0000002 = new MerchantItem("MERCHANTATM0000002","ATM - B - 00333 Trường Chinh", "Số 59 Trường Chinh, Thanh Xuân, Hà Nội", "(0243) 942 7444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9977730000d,105.8410370000d,4500d); listData.add(itATM0000002);
        MerchantItem itATM0000003 = new MerchantItem("MERCHANTATM0000003","ATM - B - 00334 Khách sạn Mùa Xuân Vàng", "Số 22 Nguyễn Hữu Huân, Hoàn Kiếm, Hà Nội", "(0243) 942 7444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0347070000d,105.8544240000d,4550d); listData.add(itATM0000003);
        MerchantItem itATM0000004 = new MerchantItem("MERCHANTATM0000004","ATM B - 00329 Trần Phú", "Số 1B, Trần Phú, quận Hà Đông, Hà Đông, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9758410000d,105.7827000000d,4600d); listData.add(itATM0000004);
        MerchantItem itATM0000005 = new MerchantItem("MERCHANTATM0000005","ATM Cầu Bươu", "Thanh Liệt, Thanh Trì, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9674200000d,105.8143530000d,4650d); listData.add(itATM0000005);
        MerchantItem itATM0000006 = new MerchantItem("MERCHANTATM0000006","ATM Nhà ga T2", "Khu vực cách li Quốc tế đến, Nhà ga Hành khách T2, Sân bay Quốc Tế Nội Bài, Sóc Sơn, Hà Nội", "(024) 35840538","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.2187150000d,105.8041710000d,4700d); listData.add(itATM0000006);
        MerchantItem itATM0000007 = new MerchantItem("MERCHANTATM0000007","Techcombank Bát Đàn", "34 Bát Đàn, Hoàn Kiếm, Hà Nội", "(024) 3923 2932(024) 3923 2933","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0337390000d,105.8470970000d,4750d); listData.add(itATM0000007);
        MerchantItem itATM0000008 = new MerchantItem("MERCHANTATM0000008","208 Lê Lợi - Sơn Tây", "208 Lê Lợi, Sơn Tây, Hà Nội", "04 33618768","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1495060000d,105.5053940000d,4800d); listData.add(itATM0000008);
        MerchantItem itATM0000009 = new MerchantItem("MERCHANTATM0000009","ATM 17T10", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,4850d); listData.add(itATM0000009);
        MerchantItem itATM0000010 = new MerchantItem("MERCHANTATM0000010","ATM Bát Sứ", "41 Bát Sứ, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0345890000d,105.8475060000d,4900d); listData.add(itATM0000010);
        MerchantItem itATM0000011 = new MerchantItem("MERCHANTATM0000011","ATM Bệnh viện phụ sản Trung ương", "43 Tràng Thi, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0269920000d,105.8466630000d,4950d); listData.add(itATM0000011);
        MerchantItem itATM0000012 = new MerchantItem("MERCHANTATM0000012","ATM Big C Hồ Gươm Plaza-Hà Đông", "Siêu thị Big C Hồ Gươm-Tòa nhà Hồ Gươm Plaza, P.Trần Phú, Hà Đông, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9789360000d,105.7856770000d,5000d); listData.add(itATM0000012);
        MerchantItem itATM0000013 = new MerchantItem("MERCHANTATM0000013","ATM Bộ Kế hoạch và Đầu tư", "6 Hoàng Diệu, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0392100000d,105.8394800000d,5050d); listData.add(itATM0000013);
        MerchantItem itATM0000014 = new MerchantItem("MERCHANTATM0000014","ATM Bộ Lao động thương binh và xã hội", "02 Đinh Lễ, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0258310000d,105.8545070000d,5100d); listData.add(itATM0000014);
        MerchantItem itATM0000015 = new MerchantItem("MERCHANTATM0000015","ATM Bộ ngoại giao", "7 Chu Văn An, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0334140000d,105.8366560000d,5150d); listData.add(itATM0000015);
        MerchantItem itATM0000025 = new MerchantItem("MERCHANTATM0000025","ATM Hà Đông", "Hà Cầu, Hà Đông, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9636430000d,105.7776650000d,5200d); listData.add(itATM0000025);
        MerchantItem itATM0000027 = new MerchantItem("MERCHANTATM0000027","ATM Hàng Bè", "50 Hàng Bè, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0326130000d,105.8538040000d,5250d); listData.add(itATM0000027);
        MerchantItem itATM0000028 = new MerchantItem("MERCHANTATM0000028","ATM Hàng Giầy", "44 Hàng Giầy, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0388570000d,105.8482550000d,5300d); listData.add(itATM0000028);
        MerchantItem itATM0000016 = new MerchantItem("MERCHANTATM0000016","ATM Bộ thông tin và truyền thông", "18 Nguyễn Du, Hai Bà Trưng, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0186220000d,105.8499890000d,5350d); listData.add(itATM0000016);
        MerchantItem itATM0000017 = new MerchantItem("MERCHANTATM0000017","ATM Cầu Diễn", "Công ty CP Chế tạo Điện cơ Hà Nội-Km12, đường Cầu Diễn, P. Phúc Diễn, Từ Liêm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0464490000d,105.7462770000d,5400d); listData.add(itATM0000017);
        MerchantItem itATM0000018 = new MerchantItem("MERCHANTATM0000018","ATM Công ty Anh Minh", "36 Hoàng Cầu, Đống Đa, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0170900000d,105.8238590000d,5450d); listData.add(itATM0000018);
        MerchantItem itATM0000019 = new MerchantItem("MERCHANTATM0000019","ATM Công ty DETECH", "15B Phạm Hùng, Cầu Giấy, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0291860000d,105.7801490000d,5500d); listData.add(itATM0000019);
        MerchantItem itATM0000020 = new MerchantItem("MERCHANTATM0000020","ATM Cty CP Traphaco", "CONG TY TRAPHACO, Hoàng Mai, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9612640000d,105.8448390000d,5550d); listData.add(itATM0000020);
        MerchantItem itATM0000021 = new MerchantItem("MERCHANTATM0000021","ATM Điện Biên Phủ", "28A Điện Biên Phủ, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0310220000d,105.8408030000d,5600d); listData.add(itATM0000021);
        MerchantItem itATM0000022 = new MerchantItem("MERCHANTATM0000022","ATM Đoàn bay 919", "121 Nguyễn Sơn, Long Biên, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0449760000d,105.8827680000d,5650d); listData.add(itATM0000022);
        MerchantItem itATM0000023 = new MerchantItem("MERCHANTATM0000023","ATM Dương Quảng Hàm", "68 Dương Quảng Hàm, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0346900000d,105.7998220000d,5700d); listData.add(itATM0000023);
        MerchantItem itATM0000024 = new MerchantItem("MERCHANTATM0000024","ATM Gốm sứ Bát Tràng", "Xóm 3, Bát Tràng, Gia Lâm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9817590000d,105.9173940000d,5750d); listData.add(itATM0000024);
        MerchantItem itATM0000026 = new MerchantItem("MERCHANTATM0000026","ATM Hai Bà Trưng", "54 Hai Bà Trưng, Hoàn Kiếm, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0256690000d,105.8480480000d,5800d); listData.add(itATM0000026);
        MerchantItem itATM0000030 = new MerchantItem("MERCHANTATM0000030","ATM Hoàng Quốc Việt", "387 Hoàng Quốc Việt, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0458830000d,105.7925360000d,5850d); listData.add(itATM0000030);
        MerchantItem itATM0000031 = new MerchantItem("MERCHANTATM0000031","ATM Hoàng Quốc Việt 2", "18 Hoàng Quốc Việt, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0481230000d,105.8012820000d,5900d); listData.add(itATM0000031);
        MerchantItem itATM0000032 = new MerchantItem("MERCHANTATM0000032","ATM KCN Minh Trí", "KCN Vĩnh Tuy, Hoàng Mai, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9827750000d,105.8728890000d,5950d); listData.add(itATM0000032);
        MerchantItem itATM0000033 = new MerchantItem("MERCHANTATM0000033","ATM KCN Nội Bài", "KCN Nội Bài, Đông Anh, Hà Nội", "(024).35840538","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.1738470000d,105.7795430000d,6000d); listData.add(itATM0000033);
        MerchantItem itATM0000034 = new MerchantItem("MERCHANTATM0000034","ATM Khách sạn Somerset", "254D Thụy Khuê, Tây Hồ, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0435940000d,105.8194310000d,6050d); listData.add(itATM0000034);
        MerchantItem itATM0000037 = new MerchantItem("MERCHANTATM0000037","ATM Lê Thanh Nghị", "39 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0017600000d,105.8462460000d,6100d); listData.add(itATM0000037);
        MerchantItem itATM0000038 = new MerchantItem("MERCHANTATM0000038","ATM Lê Văn Lương", "Lê Văn Lương, Trung Văn, Cầu Giấy, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0043390000d,105.8031460000d,6150d); listData.add(itATM0000038);
        MerchantItem itATM0000039 = new MerchantItem("MERCHANTATM0000039","ATM Lĩnh Nam", "72 Lĩnh Nam, Hoàng Mai, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9896710000d,105.8652090000d,6200d); listData.add(itATM0000039);
        MerchantItem itATM0000040 = new MerchantItem("MERCHANTATM0000040","ATM Lý Nam Đế", "17 Lý Nam Đế, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0375490000d,105.8456270000d,6250d); listData.add(itATM0000040);
        MerchantItem itATM0000041 = new MerchantItem("MERCHANTATM0000041","ATM Mã Mây", "66 Mã Mây, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0347390000d,105.8534040000d,6300d); listData.add(itATM0000041);
        MerchantItem itATM0000042 = new MerchantItem("MERCHANTATM0000042","ATM Mai Dịch", "160 Mai Dịch, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0332580000d,105.7981480000d,6350d); listData.add(itATM0000042);
        MerchantItem itATM0000043 = new MerchantItem("MERCHANTATM0000043","ATM Metro Thăng Long", "Phạm Văn Đồng, Từ Liêm, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0597050000d,105.7832090000d,6400d); listData.add(itATM0000043);
        MerchantItem itATM0000044 = new MerchantItem("MERCHANTATM0000044","ATM Ngõ Gạch", "7 Ngõ Gạch, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0362670000d,105.8504580000d,6450d); listData.add(itATM0000044);
        MerchantItem itATM0000045 = new MerchantItem("MERCHANTATM0000045","ATM Ngô Gia Tự", "256 Ngô Gia Tự, Long Biên, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0620140000d,105.8948250000d,6500d); listData.add(itATM0000045);
        MerchantItem itATM0000046 = new MerchantItem("MERCHANTATM0000046","ATM Ngọc Khánh ", "25 Ngọc Khánh, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0262810000d,105.8171920000d,6550d); listData.add(itATM0000046);
        MerchantItem itATM0000047 = new MerchantItem("MERCHANTATM0000047","ATM Ngụy Như Kon Tum", "KĐT mới HACINCO, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0061650000d,105.8061500000d,6600d); listData.add(itATM0000047);
        MerchantItem itATM0000048 = new MerchantItem("MERCHANTATM0000048","ATM Nguyễn Chí Thanh", "Tòa Nhà M3-M4 Nguyễn Chí Thanh, Đống Đa, Hà Nội", "1800588822 hoặc (024) 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0202270000d,105.8089050000d,6650d); listData.add(itATM0000048);
        MerchantItem itATM0000049 = new MerchantItem("MERCHANTATM0000049","ATM Nguyễn Chí Thanh 4", "91 Nguyễn Chí Thanh, Đống Đa, Hà Nội", "1800588822 hoặc (024) 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0207930000d,105.8094830000d,6700d); listData.add(itATM0000049);
        MerchantItem itATM0000050 = new MerchantItem("MERCHANTATM0000050","ATM Nguyễn Du", "52 Nguyễn Du, Hai Bà Trưng, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0190420000d,105.8479570000d,6750d); listData.add(itATM0000050);
        MerchantItem itATM0000051 = new MerchantItem("MERCHANTATM0000051","ATM Nguyễn Khánh Toàn 2", "Nguyễn Khánh Toàn, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0362530000d,105.8028960000d,6800d); listData.add(itATM0000051);
        MerchantItem itATM0000052 = new MerchantItem("MERCHANTATM0000052","ATM Nguyễn Khánh Toàn 2", "68 Trần Đăng Ninh, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0389030000d,105.7923630000d,6850d); listData.add(itATM0000052);
        MerchantItem itATM0000056 = new MerchantItem("MERCHANTATM0000056","ATM Phủ Lãm", "Phú Lãm, Hà Đông, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9426710000d,105.7541890000d,6900d); listData.add(itATM0000056);
        MerchantItem itATM0000071 = new MerchantItem("MERCHANTATM0000071","ATM Tôn Đản", "25 Tôn Đản, Hoàn Kiếm, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0251840000d,105.8583380000d,6950d); listData.add(itATM0000071);
        MerchantItem itATM0000053 = new MerchantItem("MERCHANTATM0000053","ATM Nguyễn Trãi", "384-386 Nguyễn Trãi, Thanh Xuân, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9935700000d,105.8056680000d,7000d); listData.add(itATM0000053);
        MerchantItem itATM0000054 = new MerchantItem("MERCHANTATM0000054","ATM Nguyễn Trãi 1", "126 Hồ Tùng Mậu, Q Cầu Giấy, Hà Nội, Cầu Giấy, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0371710000d,105.7761540000d,7050d); listData.add(itATM0000054);
        MerchantItem itATM0000055 = new MerchantItem("MERCHANTATM0000055","ATM Nguyễn Trường Tộ", "46 Nguyễn Trường Tộ, Ba Đình, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0429640000d,105.8438290000d,7100d); listData.add(itATM0000055);
        MerchantItem itATM0000057 = new MerchantItem("MERCHANTATM0000057","ATM Quốc lộ 32-1", "Quốc lộ 32-1, Từ Liêm, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0538360000d,105.7332310000d,7150d); listData.add(itATM0000057);
        MerchantItem itATM0000058 = new MerchantItem("MERCHANTATM0000058","ATM Sài Đồng 2", "TTTM Vincom Vincomcenter Long Biên, Sài Đồng, Long Biên, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0507360000d,105.9166640000d,7200d); listData.add(itATM0000058);
        MerchantItem itATM0000059 = new MerchantItem("MERCHANTATM0000059","ATM Sài Đồng 3", "Khu đô thị VincomVillage Sài Đồng, Long Biên, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0366160000d,105.9114270000d,7250d); listData.add(itATM0000059);
        MerchantItem itATM0000060 = new MerchantItem("MERCHANTATM0000060","ATM Siêu Thị Thành Đô", "352 Giải Phóng, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9880080000d,105.8404300000d,7300d); listData.add(itATM0000060);
        MerchantItem itATM0000061 = new MerchantItem("MERCHANTATM0000061","ATM Thái Hà", "119B Thái Hà, Đống Đa, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0159060000d,105.8159330000d,7350d); listData.add(itATM0000061);
        MerchantItem itATM0000062 = new MerchantItem("MERCHANTATM0000062","ATM Thái Thịnh 2", "102 Thái Thịnh, Đống Đa, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0090030000d,105.8203610000d,7400d); listData.add(itATM0000062);
        MerchantItem itATM0000063 = new MerchantItem("MERCHANTATM0000063","ATM Thành Công", "7 Thành Công, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0223480000d,105.8159250000d,7450d); listData.add(itATM0000063);
        MerchantItem itATM0000064 = new MerchantItem("MERCHANTATM0000064","ATM Toà Nhà 17 T 3", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,7500d); listData.add(itATM0000064);
        MerchantItem itATM0000065 = new MerchantItem("MERCHANTATM0000065","ATM Toà Nhà 17 T 6", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,7550d); listData.add(itATM0000065);
        MerchantItem itATM0000066 = new MerchantItem("MERCHANTATM0000066","ATM Toà Nhà 24 T 1", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,7600d); listData.add(itATM0000066);
        MerchantItem itATM0000067 = new MerchantItem("MERCHANTATM0000067","ATM Toà Nhà 24 T 2", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,7650d); listData.add(itATM0000067);
        MerchantItem itATM0000068 = new MerchantItem("MERCHANTATM0000068","ATM Toà Nhà 34 T", "KĐT Trung Hòa Nhân Chính, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0059220000d,105.8023420000d,7700d); listData.add(itATM0000068);
        MerchantItem itATM0000069 = new MerchantItem("MERCHANTATM0000069","ATM Toà Nhà N1", "62 Nguyễn Thị Định, Thanh Xuân, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0086380000d,105.8047620000d,7750d); listData.add(itATM0000069);
        MerchantItem itATM0000070 = new MerchantItem("MERCHANTATM0000070","ATM Tòa nhà Viện khoa học thống kê", "43 Nguyễn Chí Thanh, Đống Đa, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0167880000d,105.8065130000d,7800d); listData.add(itATM0000070);
        MerchantItem itATM0000072 = new MerchantItem("MERCHANTATM0000072","ATM Trần Phú 2", "16 Trần Phú, Hà Đông, Hà Nội", "1800588822 hoặc 04 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",20.9749680000d,105.7809590000d,7850d); listData.add(itATM0000072);
        MerchantItem itATM0000073 = new MerchantItem("MERCHANTATM0000073","ATM Trần Phú 3", "60 Trần Phú, Ba Đình, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0303430000d,105.8431240000d,7900d); listData.add(itATM0000073);
        MerchantItem itATM0000074 = new MerchantItem("MERCHANTATM0000074","ATM Trung Yên", "Tòa nhà chung cư Trung Yên, KĐT Trung Yên, Cầu Giấy, Hà Nội", "+84 247 108 5686","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0135260000d,105.7902340000d,7950d); listData.add(itATM0000074);
        MerchantItem itATM0000075 = new MerchantItem("MERCHANTATM0000075","ATM Trường Chinh 1", "361 Trường Chinh, Thanh Xuân, Hà Nội", "1800588822 hoặc 024 37427444","mobilechatsystem@gmail.com","https://www.giftpop.vn","Tài chính, ngân hàng",21.0025060000d,105.8221200000d,8000d); listData.add(itATM0000075);


        return listData;

    }

}