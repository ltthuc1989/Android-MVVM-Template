package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by PV on 1/13/2017.
 */

public class CoverResp {

    /**
     * cover_type : video
     * attach_processing : true
     * thumb_url : https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_thumb.jpg?1483690669
     * mobile_thumb_url : https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_mobile_thumb.jpg?1483690669
     * original_url : https://s3-us-west-2.amazonaws.com/ezyhelper-development/video/2017-01/06/26d4554219afc19e303d46f0fae8abee/video_original.mp4?1483690669
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
