package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by PV on 9/5/2016.
 */
public class UserStatus {

    /**
     * customer_task_completed_count : 0
     * customer_task_live_count : 0
     * customer_task_count : 0
     * new_message : 1
     * new_notification : 1
     * helper_task_completed_count : 0
     * helper_task_active_count : 0
     * helper_task_count : 0
     */

    private int customer_task_completed_count;
    private int customer_task_live_count;
    private int customer_task_count;
    private int new_message;
    private int new_notification;
    private int helper_task_completed_count;
    private int helper_task_active_count;
    private int helper_task_count;

    public boolean is_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    private boolean is_online;

    public int getCustomer_task_completed_count() {
        return customer_task_completed_count;
    }

    public void setCustomer_task_completed_count(int customer_task_completed_count) {
        this.customer_task_completed_count = customer_task_completed_count;
    }

    public int getCustomer_task_live_count() {
        return customer_task_live_count;
    }

    public void setCustomer_task_live_count(int customer_task_live_count) {
        this.customer_task_live_count = customer_task_live_count;
    }

    public int getCustomer_task_count() {
        return customer_task_count;
    }

    public void setCustomer_task_count(int customer_task_count) {
        this.customer_task_count = customer_task_count;
    }

    public int getNew_message() {
        return new_message;
    }

    public void setNew_message(int new_message) {
        this.new_message = new_message;
    }

    public int getNew_notification() {
        return new_notification;
    }

    public void setNew_notification(int new_notification) {
        this.new_notification = new_notification;
    }

    public int getHelper_task_completed_count() {
        return helper_task_completed_count;
    }

    public void setHelper_task_completed_count(int helper_task_completed_count) {
        this.helper_task_completed_count = helper_task_completed_count;
    }

    public int getHelper_task_active_count() {
        return helper_task_active_count;
    }

    public void setHelper_task_active_count(int helper_task_active_count) {
        this.helper_task_active_count = helper_task_active_count;
    }

    public int getHelper_task_count() {
        return helper_task_count;
    }

    public void setHelper_task_count(int helper_task_count) {
        this.helper_task_count = helper_task_count;
    }
}
