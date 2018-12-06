package com.ezyplanet.thousandhands.driver.data.network.response

class OauthResponse {

    /**
     * access_token : 98f5e79b8636febdd2c26d36f30004300d6c3a478e76f51fd374d634b401f562
     * token_type : bearer
     * expires_in : 7200
     * refresh_token : 3018435c22a5b6821bce962533ac75d0a8190ed6a7d1475c136f6fec72ee512e
     * created_at : 1433612435
     */

    var auth_code: String? = null

    var sms_code: String? = null
    var access_token: String? = null
    var token_type: String? = null
    var expires_in: Int = 0
    var refresh_token: String? = null
    var created_at: Int = 0
    var socket_token: String?=null

    var message: String? = null
}