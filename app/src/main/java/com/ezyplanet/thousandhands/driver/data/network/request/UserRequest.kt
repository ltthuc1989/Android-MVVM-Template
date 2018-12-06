package com.ezyplanet.thousandhands.driver.data.network.request

class UserRequest {
    /**
     * email : lemanh2006@gmail.com
     * phone : 840979502104
     * full_name : trongmanh2006
     * language : vi
     * send_notifications : true
     * summary : summary about me
     * location_attributes : {"country":"Việt Nam","city":"Ho Chi Minh","route":"31 Lê Hồng Phong","address":"31 Lê Hồng Phong, phường 4, Quận 5, Hồ Chí Minh, Việt Nam","latitude":10.7618329,"longitude":106.6742248}
     * skills : ["ruby on rails","nodejs","php"]
     * category_ids : [1,2,3]
     *
     */


    private var user: UserBean? = null

    fun getUser(): UserBean? {
        return user
    }

    fun setUser(user: UserBean) {
        this.user = user
    }

    class UserBean {
        var email: String? = null
        var phone: String? = null
        var full_name: String? = null
        var language: String? = null
        private var send_notifications: Boolean? = null
        var summary: String? = null
        var referral_by: String? = null
        var check_referral: Int = 0

        /**
         * country : Việt Nam
         * city : Ho Chi Minh
         * route : 31 Lê Hồng Phong
         * address : 31 Lê Hồng Phong, phường 4, Quận 5, Hồ Chí Minh, Việt Nam
         * latitude : 10.7618329
         * longitude : 106.6742248
         */

        var location_attributes: LocationAttributesBean? = null
        var skills: List<String>? = null
        var category_ids: List<Int>? = null

        var isSend_notifications: Boolean
            get() = send_notifications!!
            set(send_notifications) {
                this.send_notifications = send_notifications
            }

        class LocationAttributesBean {
            var country: String? = null
            var city: String? = null
            var route: String? = null
            var address: String? = null
            var latitude: Double = 0.toDouble()
            var longitude: Double = 0.toDouble()
        }
    }
}