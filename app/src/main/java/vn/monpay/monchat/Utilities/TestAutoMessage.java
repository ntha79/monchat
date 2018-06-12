package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import vn.monpay.monchat.Models.ContactItem;
import vn.monpay.monchat.R;

public class TestAutoMessage {
    public static  String AutoReply(String value)
    {
        String result = "";
        if(value.equals("H")||value.equals("h")||value.equals("hi")||value.equals("HI")||value.equals("Hi")||value.contains("Hi!")||value.contains("hi!"))
            result = "Hello!";
        else if(value.equals("Hai")||value.equals("hai")||value.equals("hai!")||value.equals("HI")||value.contains("Hi!")||value.contains("hi!"))
            result = "Hello!";
        else if(value.contains("Dao nay xinh")||value.contains("dao nay xinh")||value.contains("dao nay nhin xinh")||value.contains("nhin xinh the"))
            result = "Ahihi! Mới phẫu thuật mà";
        else if(value.equals("ahihi")||value.equals("Ahihi")||value.equals("ahihi!")||value.equals("Ahihi!"))
            result = "Ahihi! :)";
        else if(value.contains("Ban dang o dau")||value.contains("dang o dau"))
            result = "Tôi đang trên công ty, mình làm ở HDMon đấy";
        else if(value.contains("co gi moi khong")||value.contains("Co gi moi khong"))
            result = "Cũng thay đổi nhiều! Sắp có ứng dụng chát mới đấy...";
        else if(value.contains("so dien thoai")||value.contains("so di dong"))
            result = "Mình đổi số rồi, số mình là 01234567888 nhé";
        else if(value.contains("lay chong chua")||value.contains("lấy chồng chưa"))
            result = "Ahihi! mình chưa, vẫn FA buồn lắm!";
        else if(value.contains("chao nhe")||value.contains("tam biet"))
            result = "Okie, bye bạn nhé!";

        else if(value.equals("Hello")||value.contains("Hello!")||value.equals("hello")||value.contains("hello!")||value.equals("HELLO!")||value.equals("HELLO"))
            result = "Hi!";
        else if(value.equals("Chào bạn")||value.contains("Chào bạn!")||value.contains("chào bạn!")||value.equals("Chao ban")||value.equals("chao ban")||value.contains("Chao ban!")||value.contains("chao ban!")||value.contains("chaoban!")||value.equals("chaoban"))
            result = "Xin chào!";
        else if(value.equals("ban khoe khong?")||value.equals("ban khoe khong")||value.contains("khoe khong")||value.contains("khỏe không"))
            result = "Cảm ơn, tôi khỏe! Bạn thì sao";
        else if(value.contains("dai")||value.contains("text dai"))
            result = "Thủ tướng Canada Justin Trudeau hành động quá yếu đuối trong cuộc gặp G7 để rồi trong cuộc họp báo sau khi tôi rời đi, ông ta nói rằng 'thuế quan của Mỹ mang tính xúc phạm' và 'sẽ không để bị bắt nạt'. Thật không trung thực và yếu ớt. Thuế quan của chúng tôi là để đáp trả lại mức thuế nhập khẩu 270% của ông ấy đối với sản phẩm sữa!\", Tổng thống Trump viết trên Twitter, đồng thời thêm rằng ông sẽ xem xét áp thuế đối với ôtô nhập khẩu vào Mỹ";
        else if(value.contains("QC")||value.contains("QC:")||value.contains("[QC]")||value.contains("[QC:]"))
            result = "Cần tuyển lập trình viên iOS\n- Lương up to $1500\n" +
                    "- Được định hướng phát triển chuyên sâu về công nghệ\n" +
                    "- MÔI TRƯỜNG LÀM VIỆC: thân thiện, năng động, có cơ hội thăng tiến";
        return result;
    }
    public static Bitmap getBitmapAvatar(Context context, int frientId)
    {
        Bitmap result = null;
        if(frientId==1)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_demo_g_01);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId==2)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_demo_g_02);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId==3)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_demo_g_03);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId==4)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_demo_g_04);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId==5)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_demo_g_05);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId==6)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_b_01);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }
        else if(frientId>=7)
        {
            Bitmap tempBMP = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_demo_b_02);
            result = F.GetBitmapCricleFromBitmap(tempBMP,70,70,R.color.colorPrimary);
        }

        return result;
    }
}
