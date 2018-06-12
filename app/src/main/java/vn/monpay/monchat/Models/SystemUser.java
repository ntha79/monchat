package vn.monpay.monchat.Models;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Utilities.F;

public class SystemUser
{
    private int id = 0;
    private String login = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String imageUrl = "";
    private boolean activated = false;
    private String createdBy = "";
    private String createdDate = "";
    private String lastModifiedBy = "";
    private String lastModifiedDate = "";
    private String authorities = "";

    public SystemUser( int idValue, String loginValue, String firstNameValue, String lastNameValue, String emailValue, String imageUrlValue, boolean activatedValue, String createdByValue, String createdDateValue, String lastModifiedByValue, String lastModifiedDateValue, String authoritiesValue)
    {

        id = idValue;
        login = loginValue;
        firstName = firstNameValue;
        lastName = lastNameValue;
        email = emailValue;
        imageUrl = imageUrlValue;
        activated = activatedValue;
        createdBy = createdByValue;
        createdDate = createdDateValue;
        lastModifiedBy = lastModifiedByValue;
        lastModifiedDate = lastModifiedDateValue;
        authorities = authoritiesValue;
    }

    public SystemUser()
    {

        id = 0;
        login = "";
        firstName = "";
        lastName = "";
        email = "";
        imageUrl = "";
        activated = false;
        createdBy = "";
        createdDate = "";
        lastModifiedBy = "";
        lastModifiedDate = "";
        authorities = "";
    }

    public SystemUser(String JSONString)
    {

        id = 0;
        login = "";
        firstName = "";
        lastName = "";
        email = "";
        imageUrl = "";
        activated = false;
        createdBy = "";
        createdDate = "";
        lastModifiedBy = "";
        lastModifiedDate = "";
        authorities = "";
        try {
            JSONObject obj = new JSONObject(JSONString);

            id = F.IntIsNull(obj.getInt("id"));
            login = F.StringIsNull(obj.getString("login"));
            firstName = F.StringIsNull(obj.getString("firstName"));
            lastName = F.StringIsNull(obj.getString("lastName"));
            email = F.StringIsNull(obj.getString("email"));
            imageUrl = F.StringIsNull(obj.getString("imageUrl"));
            activated = F.BoolIsNull(obj.getBoolean("activated"));
            createdBy = F.StringIsNull(obj.getString("createdBy"));
            createdDate = F.StringIsNull(obj.getString("createdDate"));
            lastModifiedBy = F.StringIsNull(obj.getString("lastModifiedBy"));
            lastModifiedDate = F.StringIsNull(obj.getString("lastModifiedDate"));
            authorities = F.StringIsNull(obj.getString("authorities"));
        } catch (JSONException e) {}

    }
    public static List<SystemUser> GetListFromJSONString(String JSONlistData)
    {

        List<SystemUser> listData = new ArrayList<SystemUser>();
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
                    SystemUser it = new SystemUser(curJsonObject.toString());
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return listData;
    }
    public static List<SystemUser> GetListFromJSONArray(JSONArray array)
    {

        List<SystemUser> listData = new ArrayList<SystemUser>();
        if(array!=null)
        {
            try {

                int jsonArraySize = array.length();
                for (int i = 0; i < jsonArraySize; i++)
                {
                    JSONObject curJsonObject = (JSONObject) array.get(i);
                    if (curJsonObject == null)
                        continue;
                    SystemUser it = new SystemUser(curJsonObject.toString());
                    listData.add(it);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return listData;
    }
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
    public boolean getActivated(){ return activated;}
    public void setActivated(boolean  activated){ this.activated = activated;}
    public String getCreatedBy(){ return createdBy;}
    public void setCreatedBy(String  createdBy){ this.createdBy = createdBy;}
    public String getCreatedDate(){ return createdDate;}
    public void setCreatedDate(String  createdDate){ this.createdDate = createdDate;}
    public String getLastModifiedBy(){ return lastModifiedBy;}
    public void setLastModifiedBy(String  lastModifiedBy){ this.lastModifiedBy = lastModifiedBy;}
    public String getLastModifiedDate(){ return lastModifiedDate;}
    public void setLastModifiedDate(String  lastModifiedDate){ this.lastModifiedDate = lastModifiedDate;}
    public String getAuthorities(){ return authorities;}
    public void setAuthorities(String  authorities){ this.authorities = authorities;}


    public String getJSON()
    {
        JSONObject object = new JSONObject();
        try {

            object.put("id",id);
            object.put("login",login);
            object.put("firstName",firstName);
            object.put("lastName",lastName);
            object.put("email",email);
            object.put("imageUrl",imageUrl);
            object.put("activated",activated);
            object.put("createdBy",createdBy);
            object.put("createdDate",createdDate);
            object.put("lastModifiedBy",lastModifiedBy);
            object.put("lastModifiedDate",lastModifiedDate);
            object.put("authorities",authorities);}
        catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

}