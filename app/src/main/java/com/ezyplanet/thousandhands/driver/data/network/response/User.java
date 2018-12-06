package com.ezyplanet.thousandhands.driver.data.network.response;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


import com.ezyplanet.thousandhands.driver.data.network.request.Address;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by Thuc on 5/9/2016.
 */
public class User implements Serializable, Parcelable {

    /**
     * id : 60
     * email : lemanh2006@gmail.com
     * full_name : trong manh
     * avatar_url : http://localhost:3000/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg
     * status : active
     * register_helper : true
     * formatted_account_balance : ₫0
     * phone : 84979502104
     * phone_verified_at : null
     * phone_verified : false
     * device_count : 0
     * send_notifications : true
     * country_code : VN
     * language : vi
     * skills : ["ruby on rails","nodejs","php"]
     * latitude : null
     * longitude : null
     * summary : summary about helper
     * task_count : 5
     * bid_count : 0
     * customer_rate_count : 0
     * customer_star : 0.0
     * helper_rate_count : 0
     * helper_star : 0.0
     * created_at : 2016-05-05T03:05:18.607Z
     * updated_at : 2016-05-05T03:09:53.904Z
     * task_completed_count : 0
     * task_live_count : 0
     * helper_completed_count : 3
     * location : {"id":30,"country_code":"VN","country":"Vietnam","city":"Đồng Nai","route":"Phan Văn Đáng","address":"Phan Văn Đáng, Phú Hữu, Nhơn Trạch, Đồng Nai, Vietnam","buidling_info":"Anderson Group","latitude":"10.7104493","longitude":"106.7621459"}
     * categories : [{"id":75,"name":"CATERING (PART TIME/ FULL TIME)","thumb_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg","medium_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"},{"id":20,"name":"Electrical Services","thumb_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg","medium_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"},{"id":31,"name":"shopping assistant","thumb_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg","medium_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"},{"id":56,"name":"Bartending","thumb_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg","medium_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"}]
     * lasted_rate : {"id":254,"star":4,"comment":"ban lam rat tot","updated_at":"2016-04-22T04:52:29.549Z","created_at":"2016-04-22T04:52:29.549Z","customer":{"id":61,"full_name":"trongmanh2006","avatar_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"}}
     */


    private String id;
    private String email;
    private String full_name;
    private String avatar_url;
    private String cover_url;
    private String status;

    private String formatted_account_balance;
    private String phone;
    private String phone_verified_at;

    public boolean isHas_send_become_helper_request() {
        return has_send_become_helper_request;
    }

    public void setHas_send_become_helper_request(boolean has_send_become_helper_request) {
        this.has_send_become_helper_request = has_send_become_helper_request;
    }

    private boolean has_send_become_helper_request;
    private boolean phone_verified;
    private int device_count;
    private boolean send_notifications;
    private String country_code;
    private String language;
    private String latitude;
    private String longitude;
    private String summary;
    private int task_count;
    private int bid_count;
    private int customer_rate_count;
    private float customer_star;
    private int helper_rate_count;
    private float helper_star;
    private String created_at;
    private String updated_at;
    private int task_completed_count;
    private int task_live_count;
    private int helper_task_count;
    private int helper_completed_count;
    //exclude properties;
    private String password;
    private String access_token;




    private String refresh_token;
    private String referral_by;

    private int helper_task_active_count;
    private int check_referral;
    private String referral_code;

    private boolean is_online;

    private String formatted_phone;
    public PhoneInfo getPhone_info() {
        return phone_info;
    }

    public void setPhone_info(PhoneInfo phone_info) {
        this.phone_info = phone_info;
    }
    public String getFormatted_phone() {
        return formatted_phone;
    }

    public void setFormatted_phone(String formatted_phone) {
        this.formatted_phone = formatted_phone;
    }
    private PhoneInfo phone_info;
    boolean isSuspend;
    boolean first_sign_in;
    Date last_activity;
    UserStatus userStatus;
    LocationResponse countryInfo;
    public LocationResponse getCountryInfo(){
        return countryInfo;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public Date getLast_activity() {
        if(last_activity == null){
            return new Date();
        }
        return last_activity;
    }

    public void setLast_activity(Date last_activity) {
        this.last_activity = last_activity;
    }

    public boolean isFist_sign_in() {
        return first_sign_in;
    }

    public boolean is_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public void setFist_sign_in(boolean fist_sign_in) {
        this.first_sign_in = fist_sign_in;
    }

    public boolean isSuspend() {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }
    CompanyBean company;

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }




    public static User objectFromData(String str) {

        return new Gson().fromJson(str, User.class);
    }

    public static User objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), User.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> arrayUserFromData(String str) {

        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<User> arrayUserFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }


    public int getCheck_referral() {
        return check_referral;
    }

    public void setCheck_referral(int check_referral) {
        this.check_referral = check_referral;
    }

    @SerializedName("token")
    @Expose
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    /**
     * user_type : customer
     */
    private Setting setting;
    private boolean is_random_password;
    /**
     * approval_request : {"id":1,"status":"requested"}
     */

    private ApprovalRequestBean approval_request;
    /**
     * register_helper : true
     */

    private boolean register_helper;
    /**
     * user_cover : {"cover_type":"video","attach_processing":false,"thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_thumb.jpg?1484536239","mobile_thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_mobile_thumb.jpg?1484536239","original_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_original.mp4?1484536239"}
     */

    private CoverResp user_cover;
    /**
     * video : {"cover_type":"video","attach_processing":false,"thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_thumb.jpg?1483690669","mobile_thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_mobile_thumb.jpg?1483690669","original_url":"https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_original.mp4?1483690669"}
     */


    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    /**
     * user_cover : {"cover_type":"video","attach_processing":false,"thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_thumb.jpg?1484536239","mobile_thumb_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_mobile_thumb.jpg?1484536239","original_url":"https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_original.mp4?1484536239"}
     */



    public Setting getSetting() {

        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    private String user_type;
    private boolean isLoginFacebook;

    public boolean isLoginFacebook() {
        return isLoginFacebook;
    }

    public void setLoginFacebook(boolean loginFacebook) {
        isLoginFacebook = loginFacebook;
    }

    public int getHelper_task_active_count() {
        return helper_task_active_count;
    }

    public void setHelper_task_active_count(int helper_task_active_count) {
        this.helper_task_active_count = helper_task_active_count;
    }

    /**
     * id : 254
     * star : 4
     * comment : ban lam rat tot
     * updated_at : 2016-04-22T04:52:29.549Z
     * created_at : 2016-04-22T04:52:29.549Z
     * customer : {"id":61,"full_name":"trongmanh2006","avatar_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"}
     */

    private CustomerLastedRateBean customer_lasted_rate;
    /**
     * id : 255
     * star : 3
     * comment : thanks
     * updated_at : 2016-04-22T04:52:29.549Z
     * created_at : 2016-04-22T04:52:29.549Z
     * customer : {"id":14,"full_name":"hoang minh","avatar_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"}
     */

    private HelperLastedRateBean helper_lasted_rate;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * id : 30
     * country_code : VN
     * country : Vietnam
     * city : Đồng Nai
     * route : Phan Văn Đáng
     * address : Phan Văn Đáng, Phú Hữu, Nhơn Trạch, Đồng Nai, Vietnam
     * buidling_info : Anderson Group
     * latitude : 10.7104493
     * longitude : 106.7621459
     */
    private LocationEntity location;
    /**
     * id : 254
     * star : 4
     * comment : ban lam rat tot
     * updated_at : 2016-04-22T04:52:29.549Z
     * created_at : 2016-04-22T04:52:29.549Z
     * customer : {"id":61,"full_name":"trongmanh2006","avatar_url":"/assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg"}
     */


    private String[] skills;
    /**
     * id : 75
     * name : CATERING (PART TIME/ FULL TIME)
     * thumb_url : /assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg
     * medium_url : /assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg
     */

    private List<CategoriesEntity> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getFormatted_account_balance() {
        return formatted_account_balance;
    }

    public void setFormatted_account_balance(String formatted_account_balance) {
        this.formatted_account_balance = formatted_account_balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;

    }

    public String getPhone_verified_at() {
        return phone_verified_at;
    }

    public void setPhone_verified_at(String phone_verified_at) {
        this.phone_verified_at = phone_verified_at;
    }

    public boolean isPhone_verified() {
        return phone_verified;
    }

    public void setPhone_verified(boolean phone_verified) {
        this.phone_verified = phone_verified;
    }

    public int getDevice_count() {
        return device_count;
    }

    public void setDevice_count(int device_count) {
        this.device_count = device_count;
    }

    public boolean isSend_notifications() {
        return send_notifications;
    }

    public void setSend_notifications(boolean send_notifications) {
        this.send_notifications = send_notifications;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getLanguage() {
        if (TextUtils.isEmpty(language)) {
            Timber.d("language is null");
            String deviceL =  Locale.getDefault().toString();
            String lang = "en";
            if(deviceL != null && deviceL.equals("en")){
                lang = "en";
            }
            else if(deviceL != null && deviceL.startsWith("vi")){
                lang = "vi";
            }
            else if(deviceL != null && deviceL.equals("zh_CN")){
                lang = "zh-hans";
            }else if(deviceL != null && deviceL.equals("zh_TW")){
                lang = "zh-hant";
            }
            language = lang;
        }

        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTask_count() {
        return task_count;
    }

    public void setTask_count(int task_count) {
        this.task_count = task_count;
    }

    public int getBid_count() {
        return bid_count;
    }

    public void setBid_count(int bid_count) {
        this.bid_count = bid_count;
    }

    public int getCustomer_rate_count() {
        return customer_rate_count;
    }

    public void setCustomer_rate_count(int customer_rate_count) {
        this.customer_rate_count = customer_rate_count;
    }

    public float getCustomer_star() {
        return customer_star;
    }

    public void setCustomer_star(float customer_star) {
        this.customer_star = customer_star;
    }

    public int getHelper_rate_count() {
        return helper_rate_count;
    }

    public void setHelper_rate_count(int helper_rate_count) {
        this.helper_rate_count = helper_rate_count;
    }

    public float getHelper_star() {
        return helper_star;
    }

    public void setHelper_star(float helper_star) {
        this.helper_star = helper_star;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getTask_completed_count() {
        return task_completed_count;
    }

    public void setTask_completed_count(int task_completed_count) {
        this.task_completed_count = task_completed_count;
    }

    public int getTask_live_count() {
        return task_live_count;
    }

    public void setTask_live_count(int task_live_count) {
        this.task_live_count = task_live_count;
    }

    public int getHelper_completed_count() {
        return helper_completed_count;
    }

    public void setHelper_completed_count(int helper_completed_count) {
        this.helper_completed_count = helper_completed_count;
    }

    public LocationEntity getLocation() {

        return location;
    }
    public String getShortLocation(){
        String address="";
        if(location==null){
            return "";
        }
        if(location.getSuburb()!=null){
            address=location.getSuburb();
            if(location.getCity()!=null){
                address=address+", "+location.getCity();
                if(location.getState()!=null&&!location.getCity().equals(location.getState())){
                    address=address+", "+ location.getState();
                }
            }else {
                if(location.getState()!=null){
                    address=address+", "+location.getState();
                }
            }

        }else {
            if(location.getCity()!=null){
                address=address+location.getCity();
                if(location.getState()!=null&&!location.getCity().equals(location.getState())){
                    address=address+", "+ location.getState();
                }
            }else{
                address=location.getState();
            }

        }
        return address;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }


    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public List<CategoriesEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesEntity> categories) {
        this.categories = categories;
    }

    public CustomerLastedRateBean getCustomer_lasted_rate() {
        return customer_lasted_rate;
    }

    public void setCustomer_lasted_rate(CustomerLastedRateBean customer_lasted_rate) {
        this.customer_lasted_rate = customer_lasted_rate;
    }

    public HelperLastedRateBean getHelper_lasted_rate() {
        return helper_lasted_rate;
    }

    public void setHelper_lasted_rate(HelperLastedRateBean helper_lasted_rate) {
        this.helper_lasted_rate = helper_lasted_rate;
    }

    public String getUser_type() {
        if(user_type==null){
            return "";
        }
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getReferral_by() {
        return referral_by;
    }

    public void setReferral_by(String referral_by) {
        this.referral_by = referral_by;
    }

    public boolean is_random_password() {
        return is_random_password;
    }

    public void setIs_random_password(boolean is_random_password) {
        this.is_random_password = is_random_password;
    }

    public ApprovalRequestBean getApproval_request() {

        return approval_request;
    }

    public void setApproval_request(ApprovalRequestBean approval_request) {
        this.approval_request = approval_request;
    }

    public boolean isRegister_helper() {
        return register_helper;
    }

    public void setRegister_helper(boolean register_helper) {
        this.register_helper = register_helper;
    }

    public int getHelper_task_count() {
        return helper_task_count;
    }

    public void setHelper_task_count(int helper_task_count) {
        this.helper_task_count = helper_task_count;
    }


    public CoverResp getUser_cover() {
        return user_cover;
    }

    public void setUser_cover(CoverResp user_cover) {
        this.user_cover = user_cover;
    }



    public User updateFormatPhone(String formatted_phone) {
        this.formatted_phone = formatted_phone;
        return this;
    }


    public static class LocationEntity implements Parcelable {
        private int id;
        private String country_code;
        private String country;
        private String city;
        private String route;
        private String address;
        private String buidling_info;
        private String latitude;
        private String longitude;
        private String suburb;
        String postal_code;
        String state;
        String streetNumber;
        String state_3;
        String district;



        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getState_3() {
            return state_3;
        }

        public void setState_3(String state_3) {
            this.state_3 = state_3;
        }

        public String getShortAddress(){
            String s="";

                if (streetNumber != null) {
                    s=streetNumber;
                    if (route != null) {
                        s=streetNumber+", "+route;
                    }
                    return s;

                }

            return route;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSuburb() {
            return suburb;
        }

        public void setSuburb(String suburb) {
            this.suburb = suburb;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getAddress() {
            return address;
        }


        public void setAddress(String address) {
            this.address = address;
        }

        public String getBuidling_info() {
            return buidling_info;
        }

        public void setBuidling_info(String buidling_info) {
            this.buidling_info = buidling_info;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public LocationEntity() {
        }
        public Address convertToAddress(){
            Address address = new Address();
            address.setCountry(getCountry());
            address.setStreetName(getAddress());
            address.setState(getState());
            address.setNote(getBuidling_info());
            address.setLatitude(Double.parseDouble(getLatitude()));
            address.setLongitude(Double.parseDouble(getLongitude()));
            address.setRoute(getRoute());
            address.setSuburb(getSuburb());
            address.setStreetNumber(getStreetNumber());
            return address;

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.country_code);
            dest.writeString(this.country);
            dest.writeString(this.city);
            dest.writeString(this.route);
            dest.writeString(this.address);
            dest.writeString(this.buidling_info);
            dest.writeString(this.latitude);
            dest.writeString(this.longitude);
            dest.writeString(this.suburb);
            dest.writeString(this.postal_code);
            dest.writeString(this.state);
            dest.writeString(this.streetNumber);
            dest.writeString(this.state_3);
            dest.writeString(this.district);
        }

        protected LocationEntity(Parcel in) {
            this.id = in.readInt();
            this.country_code = in.readString();
            this.country = in.readString();
            this.city = in.readString();
            this.route = in.readString();
            this.address = in.readString();
            this.buidling_info = in.readString();
            this.latitude = in.readString();
            this.longitude = in.readString();
            this.suburb = in.readString();
            this.postal_code = in.readString();
            this.state = in.readString();
            this.streetNumber = in.readString();
            this.state_3 = in.readString();
            this.district = in.readString();
        }

        public static final Creator<LocationEntity> CREATOR = new Creator<LocationEntity>() {
            @Override
            public LocationEntity createFromParcel(Parcel source) {
                return new LocationEntity(source);
            }

            @Override
            public LocationEntity[] newArray(int size) {
                return new LocationEntity[size];
            }
        };
    }


    public static class CategoriesEntity implements Parcelable{
        private int id;
        private String name;
        private String thumb_url;
        private String medium_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getMedium_url() {
            return medium_url;
        }

        public void setMedium_url(String medium_url) {
            this.medium_url = medium_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);

        }

        public CategoriesEntity() {
        }

        protected CategoriesEntity(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
           ;
        }

        public static final Creator<CategoriesEntity> CREATOR = new Creator<CategoriesEntity>() {
            @Override
            public CategoriesEntity createFromParcel(Parcel source) {
                return new CategoriesEntity(source);
            }

            @Override
            public CategoriesEntity[] newArray(int size) {
                return new CategoriesEntity[size];
            }
        };
    }

    public static class CustomerLastedRateBean implements Parcelable {
        private int id;
        private int star;
        private String comment;
        private String updated_at;
        private String created_at;
        /**
         * id : 61
         * full_name : trongmanh2006
         * avatar_url : /assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg
         */

        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Parcelable {
            private int id;
            private String full_name;
            private String avatar_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.full_name);
                dest.writeString(this.avatar_url);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.id = in.readInt();
                this.full_name = in.readString();
                this.avatar_url = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel source) {
                    return new UserBean(source);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.star);
            dest.writeString(this.comment);
            dest.writeString(this.updated_at);
            dest.writeString(this.created_at);
            dest.writeParcelable(this.user, flags);
        }

        public CustomerLastedRateBean() {
        }

        protected CustomerLastedRateBean(Parcel in) {
            this.id = in.readInt();
            this.star = in.readInt();
            this.comment = in.readString();
            this.updated_at = in.readString();
            this.created_at = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
        }

        public static final Creator<CustomerLastedRateBean> CREATOR = new Creator<CustomerLastedRateBean>() {
            @Override
            public CustomerLastedRateBean createFromParcel(Parcel source) {
                return new CustomerLastedRateBean(source);
            }

            @Override
            public CustomerLastedRateBean[] newArray(int size) {
                return new CustomerLastedRateBean[size];
            }
        };
    }

    public static class HelperLastedRateBean implements Parcelable {
        private int id;
        private int star;
        private String comment;
        private String updated_at;
        private String created_at;
        /**
         * id : 14
         * full_name : hoang minh
         * avatar_url : /assets/image_default-8178c68c997cd3a72d24feaf579ec3af4a47aa3b73587b1246da3bb67448ab7d.jpg
         */

        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Parcelable {
            private int id;
            private String full_name;
            private String avatar_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.full_name);
                dest.writeString(this.avatar_url);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.id = in.readInt();
                this.full_name = in.readString();
                this.avatar_url = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel source) {
                    return new UserBean(source);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.star);
            dest.writeString(this.comment);
            dest.writeString(this.updated_at);
            dest.writeString(this.created_at);
            dest.writeParcelable(this.user, flags);
        }

        protected HelperLastedRateBean(Parcel in) {
            this.id = in.readInt();
            this.star = in.readInt();
            this.comment = in.readString();
            this.updated_at = in.readString();
            this.created_at = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
        }

        public static final Creator<HelperLastedRateBean> CREATOR = new Creator<HelperLastedRateBean>() {
            @Override
            public HelperLastedRateBean createFromParcel(Parcel source) {
                return new HelperLastedRateBean(source);
            }

            @Override
            public HelperLastedRateBean[] newArray(int size) {
                return new HelperLastedRateBean[size];
            }
        };
    }


    public void updateUserInfo(Context context, User userResponse) {
        full_name = userResponse.full_name;
        email = userResponse.email;
        avatar_url = userResponse.avatar_url;
        cover_url = userResponse.cover_url;
        status = userResponse.status;

        formatted_account_balance = userResponse.formatted_account_balance;
        phone = userResponse.phone;
        phone_verified_at = userResponse.phone_verified_at;
        device_count = userResponse.device_count;
        phone_verified = userResponse.phone_verified;
        language = userResponse.language;
        summary = userResponse.summary;
        location = userResponse.location;
        phone_info = userResponse.phone_info;

        skills = userResponse.skills;

        task_count = userResponse.task_count;
        bid_count = userResponse.bid_count;
        customer_rate_count = userResponse.customer_rate_count;
        customer_star = userResponse.customer_star;
        helper_rate_count = userResponse.helper_rate_count;
        helper_star = userResponse.helper_star;
        created_at = userResponse.created_at;
        updated_at = userResponse.updated_at;
        task_completed_count = userResponse.task_completed_count;
        task_live_count = userResponse.task_live_count;
        helper_completed_count = userResponse.helper_completed_count;
        helper_task_active_count = userResponse.helper_task_active_count;
        categories = userResponse.categories;
        register_helper = userResponse.isRegister_helper();
        approval_request = userResponse.getApproval_request();
        user_type = userResponse.getUser_type();
        user_cover = userResponse.getUser_cover();
        referral_by = userResponse.getReferral_by();
        check_referral = userResponse.getCheck_referral();
        referral_code = userResponse.getReferral_code();
        isSuspend = userResponse.isSuspend;
        company = userResponse.company;
        formatted_phone = userResponse.formatted_phone;
        is_online = userResponse.is_online();
        last_activity = userResponse.getLast_activity();
        setSend_notifications(userResponse.isSend_notifications());
       // setting = CacheManager.getInstance().getSetting(context);
        saveUserInfo(context);
    }

    public void saveUserInfo(Context context) {
//        String userJson = JsonHelper.userToJson(this);
//        SharedPreferencesHelper.putSharedPreferencesString(context,
//                Constants.SharedPreferencesKeys.USER_KEY, userJson);
    }

    public static class ApprovalRequestBean implements Parcelable {
        /**
         * id : 1
         * status : requested
         */

        @SerializedName("id")
        private int idX;
        @SerializedName("status")
        private String statusX;

        public int getIdX() {
            return idX;
        }

        public void setIdX(int idX) {
            this.idX = idX;
        }

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.idX);
            dest.writeString(this.statusX);
        }

        public ApprovalRequestBean() {
        }

        protected ApprovalRequestBean(Parcel in) {
            this.idX = in.readInt();
            this.statusX = in.readString();
        }

        public static final Creator<ApprovalRequestBean> CREATOR = new Creator<ApprovalRequestBean>() {
            @Override
            public ApprovalRequestBean createFromParcel(Parcel source) {
                return new ApprovalRequestBean(source);
            }

            @Override
            public ApprovalRequestBean[] newArray(int size) {
                return new ApprovalRequestBean[size];
            }
        };
    }


    public static class UserCoverBean {
        /**
         * cover_type : video
         * attach_processing : false
         * thumb_url : https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_thumb.jpg?1484536239
         * mobile_thumb_url : https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_mobile_thumb.jpg?1484536239
         * original_url : https://s3-us-west-2.amazonaws.com/ezyhelpers-st/user_cover/2017-01/13/cd1d199eb6ad3d81130bc6298e41346a/user_cover_original.mp4?1484536239
         */

        private String cover_type;
        private boolean attach_processing;
        private String thumb_url;
        private String mobile_thumb_url;
        private String original_url;

        public String getCover_type() {
            return cover_type;
        }

        public void setCover_type(String cover_type) {
            this.cover_type = cover_type;
        }

        public boolean isAttach_processing() {
            return attach_processing;
        }

        public void setAttach_processing(boolean attach_processing) {
            this.attach_processing = attach_processing;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getMobile_thumb_url() {
            return mobile_thumb_url;
        }

        public void setMobile_thumb_url(String mobile_thumb_url) {
            this.mobile_thumb_url = mobile_thumb_url;
        }

        public String getOriginal_url() {
            return original_url;
        }

        public void setOriginal_url(String original_url) {
            this.original_url = original_url;
        }
    }
    public static class Token implements Parcelable {

        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("token_type")
        @Expose
        private String tokenType;
        @SerializedName("expires_in")
        @Expose
        private Integer expiresIn;
        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;
        @SerializedName("created_at")
        @Expose
        private Integer createdAt;
        @SerializedName("user_id")
        @Expose
        private Integer userId;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public Integer getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Integer createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.accessToken);
            dest.writeString(this.tokenType);
            dest.writeValue(this.expiresIn);
            dest.writeString(this.refreshToken);
            dest.writeValue(this.createdAt);
            dest.writeValue(this.userId);

        }

        public Token() {
        }

        protected Token(Parcel in) {
            this.accessToken = in.readString();
            this.tokenType = in.readString();
            this.expiresIn = (Integer) in.readValue(Integer.class.getClassLoader());
            this.refreshToken = in.readString();
            this.createdAt = (Integer) in.readValue(Integer.class.getClassLoader());
            this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public static final Creator<Token> CREATOR = new Creator<Token>() {
            @Override
            public Token createFromParcel(Parcel source) {
                return new Token(source);
            }

            @Override
            public Token[] newArray(int size) {
                return new Token[size];
            }
        };
    }


    public User udatePhoneNumber(String phone){
        this.phone = phone;
        return this;
    }
    public User updatePhoneVerified(boolean isVerified){
        this.setPhone_verified(isVerified);
        return this;
    }
    public User updateSetting(Context context,Setting setting){
        if(setting!=null){
            //CacheManager.getInstance().updateSetting(context,setting);
        }
        this.setting = setting;

        return this;
    }
    public User updateFullName(String fullName){
        this.full_name = fullName;
        return this;
    }
    public User updateEmail(String email){
        this.email = email;
        return this;
    }
    public User updatePhone(String phone){
        this.phone = phone;
        return this;
    }
    public User updatePhoneInfo(PhoneInfo phone_info){
        this.phone_info = phone_info;
        return this;
    }
    public User updateApproveStatus(String status){
        if(this.getApproval_request()!=null) {
            this.getApproval_request().setStatusX(status);
        }
        return this;
    }
    public User updateSuspendStatus(boolean isSuspend){
        this.isSuspend = isSuspend;
        return this;
    }
    public User updateCoverPhoto(CoverResp coverResp){
        this.user_cover = coverResp;
        return this;
    }
    public User updateAvatarPhoto(String avatarUrl){
        this.avatar_url = avatarUrl;
        return this;
    }
    public User updateLocation(LocationEntity location){
        this.location = location;
        return this;
    }
    public User updateLanguage(String lang){
        this.language = lang;
        return this;
    }
    public User updateUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
        return this;
    }
    public User updateCountryInfo(LocationResponse countryInfo){
        this.countryInfo=countryInfo;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", cover_url='" + cover_url + '\'' +
                ", status='" + status + '\'' +
                ", formatted_account_balance='" + formatted_account_balance + '\'' +
                ", phone='" + phone + '\'' +
                ", phone_verified_at='" + phone_verified_at + '\'' +
                ", phone_verified=" + phone_verified +
                ", device_count=" + device_count +
                ", send_notifications=" + send_notifications +
                ", country_code='" + country_code + '\'' +
                ", language='" + language + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", summary='" + summary + '\'' +
                ", task_count=" + task_count +
                ", bid_count=" + bid_count +
                ", customer_rate_count=" + customer_rate_count +
                ", customer_star=" + customer_star +
                ", helper_rate_count=" + helper_rate_count +
                ", helper_star=" + helper_star +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", task_completed_count=" + task_completed_count +
                ", task_live_count=" + task_live_count +
                ", helper_task_count=" + helper_task_count +
                ", helper_completed_count=" + helper_completed_count +
                ", password='" + password + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", referral_by='" + referral_by + '\'' +
                ", helper_task_active_count=" + helper_task_active_count +
                ", check_referral=" + check_referral +
                ", referral_code='" + referral_code + '\'' +
                ", token=" + token +
                ", setting=" + setting +
                ", is_random_password=" + is_random_password +
                ", approval_request=" + approval_request +
                ", register_helper=" + register_helper +
                ", user_cover=" + user_cover +
                ", user_type='" + user_type + '\'' +
                ", isLoginFacebook=" + isLoginFacebook +
                ", customer_lasted_rate=" + customer_lasted_rate +
                ", helper_lasted_rate=" + helper_lasted_rate +
                ", location=" + location +
                ", skills=" + Arrays.toString(skills) +
                ", categories=" + categories +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.email);
        dest.writeString(this.full_name);
        dest.writeString(this.avatar_url);
        dest.writeString(this.cover_url);
        dest.writeString(this.status);
        dest.writeString(this.formatted_account_balance);
        dest.writeString(this.phone);
        dest.writeString(this.phone_verified_at);
        dest.writeByte(this.phone_verified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.device_count);
        dest.writeByte(this.send_notifications ? (byte) 1 : (byte) 0);
        dest.writeString(this.country_code);
        dest.writeString(this.language);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.summary);
        dest.writeInt(this.task_count);
        dest.writeInt(this.bid_count);
        dest.writeInt(this.customer_rate_count);
        dest.writeFloat(this.customer_star);
        dest.writeInt(this.helper_rate_count);
        dest.writeFloat(this.helper_star);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeInt(this.task_completed_count);
        dest.writeInt(this.task_live_count);
        dest.writeInt(this.helper_task_count);
        dest.writeInt(this.helper_completed_count);
        dest.writeString(this.password);
        dest.writeString(this.access_token);
        dest.writeString(this.refresh_token);
        dest.writeString(this.referral_by);
        dest.writeInt(this.helper_task_active_count);
        dest.writeInt(this.check_referral);
        dest.writeString(this.referral_code);
        dest.writeByte(this.isSuspend ? (byte) 1 : (byte) 0);
        dest.writeByte(this.first_sign_in ? (byte) 1 : (byte) 0);



        dest.writeParcelable(this.token, flags);

        dest.writeByte(this.is_random_password ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.approval_request, flags);
        dest.writeByte(this.register_helper ? (byte) 1 : (byte) 0);

        dest.writeString(this.user_type);
        dest.writeByte(this.isLoginFacebook ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.customer_lasted_rate, flags);
        dest.writeParcelable(this.helper_lasted_rate, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeStringArray(this.skills);
        dest.writeList(this.categories);
        dest.writeSerializable(this.last_activity);
        dest.writeByte(this.is_online ? (byte) 1 : (byte) 0);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.email = in.readString();
        this.full_name = in.readString();
        this.avatar_url = in.readString();
        this.cover_url = in.readString();
        this.status = in.readString();
        this.formatted_account_balance = in.readString();
        this.phone = in.readString();
        this.phone_verified_at = in.readString();
        this.phone_verified = in.readByte() != 0;
        this.device_count = in.readInt();
        this.send_notifications = in.readByte() != 0;
        this.country_code = in.readString();
        this.language = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.summary = in.readString();
        this.task_count = in.readInt();
        this.bid_count = in.readInt();
        this.customer_rate_count = in.readInt();
        this.customer_star = in.readFloat();
        this.helper_rate_count = in.readInt();
        this.helper_star = in.readFloat();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.task_completed_count = in.readInt();
        this.task_live_count = in.readInt();
        this.helper_task_count = in.readInt();
        this.helper_completed_count = in.readInt();
        this.password = in.readString();
        this.access_token = in.readString();
        this.refresh_token = in.readString();
        this.referral_by = in.readString();
        this.helper_task_active_count = in.readInt();
        this.check_referral = in.readInt();
        this.referral_code = in.readString();
        this.isSuspend = in.readByte() != 0;
        this.first_sign_in = in.readByte() != 0;

        this.company = in.readParcelable(CompanyBean.class.getClassLoader());
        this.token = in.readParcelable(Token.class.getClassLoader());
        this.setting = in.readParcelable(Setting.class.getClassLoader());
        this.is_random_password = in.readByte() != 0;
        this.approval_request = in.readParcelable(ApprovalRequestBean.class.getClassLoader());
        this.register_helper = in.readByte() != 0;
        this.user_cover = in.readParcelable(CoverResp.class.getClassLoader());
        this.user_type = in.readString();
        this.isLoginFacebook = in.readByte() != 0;
        this.customer_lasted_rate = in.readParcelable(CustomerLastedRateBean.class.getClassLoader());
        this.helper_lasted_rate = in.readParcelable(HelperLastedRateBean.class.getClassLoader());
        this.location = in.readParcelable(LocationEntity.class.getClassLoader());
        this.skills = in.createStringArray();
        this.categories = new ArrayList<CategoriesEntity>();
        this.last_activity = (Date) in.readSerializable();
        in.readList(this.categories, CategoriesEntity.class.getClassLoader());
        this.is_online = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


//    public boolean isAU(){
//        return country_code.equals(Constants.CountryCode.AUSTRALIA);
//    }
//    public boolean isHelperApproved() {
//        if (this == null)
//            return false;
//        if (getUser_type()!=null&&getUser_type().equals(Constants.UserType.HELPER)) {
//            return true;
//
//
//        }
//        return false;
//    }
}
