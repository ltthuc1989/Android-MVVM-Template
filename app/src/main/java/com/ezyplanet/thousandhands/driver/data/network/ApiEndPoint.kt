package com.ezyplanet.thousandhands.driver.data.network

import com.ezyplanet.thousandhands.driver.BuildConfig


/**
 * Created by jyotidubey on 11/01/18.
 */
object ApiEndPoint {


    val ENDPOIN_auth = BuildConfig.API_URL+"/api/v1/oauth/token"
    val ENDPOINT_getMedetail = BuildConfig.API_URL + "/api/v1/users/me"
    val ENDPOINT_getSetting = BuildConfig.API_URL + "/api/v1/settings"
    val ENDPOINT_CHECK_USER=BuildConfig.API_URL +"/api/v1/users/check"
    val ENDPOINT_MY_NOTIFICATION=BuildConfig.API_URL+"/api/v1/users/me/notifications"
    val ENDPOINT_updateSkillForMe = BuildConfig.API_URL + "/api/v1/users/me"
    val ENDPOINT_getConversions = BuildConfig.API_URL +"/api/v1/conversations"
    val ENDPOINT_USER_STATUS= BuildConfig.API_URL +"/api/v1/users/me/status"
    val ENDPOINT_REVOKE=BuildConfig.API_URL+"/api/v1/oauth/revoke"
    val ENDPOIND_DISABLE_DEVICE=BuildConfig.API_URL+"/api/v1/users/me/devices/disable"
    val ENDPOINT_CHANGE_PASSWORD=BuildConfig.API_URL+"/api/v1/users/me/change_password"
    val ENDPOINT_updateSummaryForMe = BuildConfig.API_URL + "/api/v1/users/me"
    val ENDPOINT_BANNER=BuildConfig.API_URL+"/api/v1/banners"
    val ENDPOINT_CATEGORY = BuildConfig.API_URL+"/api/v1/categories"
    val ENDPOINT_CUSTOMER_TASK_LIVE=BuildConfig.API_URL+"/api/v1/users/me/tasks/live"
    val ENDPOINT_CUSTOMER_TASK_COMPLETE=BuildConfig.API_URL+"/api/v1/users/me/tasks/completed"
    val ENDPOINT_GET_TASK_AROUNT = BuildConfig.API_URL+"/api/v1/tasks"
    val ENDPOINT_updatePersonalInfoForMe = BuildConfig.API_URL + "/api/v1/users/me"

    val ENDPOIN_ACCEPT_TRIP=BuildConfig.API_URL+"/api/rides/v1/trips/{id}/accept"
    val ENDPOINT_START_TRIP =BuildConfig.API_URL+"/api/rides/v1/trips/{id}/start"
    val ENDPOINT_ARRIVE =BuildConfig.API_URL+"/api/rides/v1/trips/{id}/arrive"


    val ENDPOINT_getUser = BuildConfig.API_URL + "/api/v1/users/{id}"


    val ENDPOINT_changePassword = BuildConfig.API_URL + "/api/v1/users/me/change_password"


    val ENDPOINT_uploadImages = BuildConfig.API_URL + "api/v1/users/me/images"


    val ENDPOINT_uploadCoverImages = BuildConfig.API_URL + "api/v1/users/me/images"

    val ENDPOINT_uploadCover = BuildConfig.API_URL + "api/v1/users/me/cover"

    //Bids api

    val ENDPOINT_getMeBids = BuildConfig.API_URL + "api/v1/helpers/me/bids"


    val ENDPOINT_getBidsById = BuildConfig.API_URL + "api/v1/tasks/{task_id}/bids"


    val ENDPOINT_getBidById = BuildConfig.API_URL + "api/v1/bids/{id}"


    val ENDPOINT_createBid = BuildConfig.API_URL + "/api/v1tasks/{task_id}/bids"


    val ENDPOINT_assignBid = BuildConfig.API_URL + "/api/v2/tasks/{task_id}/bids/{id}/assign"


    //Categories api

    val ENDPOINT_getCategories = BuildConfig.API_URL + "/api/v1/categories"

    //Customer api

    val ENDPOINT_registerCustomer = BuildConfig.API_URL + "/api/v1/customers"


    val ENDPOINT_registerAsHellper = BuildConfig.API_URL + "/api/v1/customers/me/register_helper"


    val ENDPOINT_verifyPhone = BuildConfig.API_URL + "/api/v1/phone_verifications/{phone_code}"


    val ENDPOINT_resendPhoneCode = BuildConfig.API_URL + "/api/v1/phone_verifications"

    //task api

    val ENDPOINT_showTasksAround = BuildConfig.API_URL + "/api/v1/tasks"


    val ENDPOINT_showTasksWithinBound = BuildConfig.API_URL + "/api/v1/tasks"
    val ENDPOINT_showCustomerTasks = BuildConfig.API_URL + "/api/v1/users/me/tasks"
    val ENDPOINT_showCustomerTasksLive = BuildConfig.API_URL + "/api/v1/users/me/tasks/live"

    val ENDPOINT_showTaskById = BuildConfig.API_URL + "/api/v1/tasks/{id}"

    val ENDPOINT_showCustomerTasksCompleted = BuildConfig.API_URL + "/api/v1/users/me/tasks/completed"

    val ENDPOINT_showHelpersTasks = BuildConfig.API_URL + "/api/v1/helpers/me/tasks"
    val ENDPOINT_showHelpersTasksActive = BuildConfig.API_URL + "/api/v1/helpers/me/tasks/active"

    val ENDPOINT_showHelpersTasksCompleted = BuildConfig.API_URL + "/api/v1/helpers/me/tasks/completed"
    val ENDPOINT_registerDevice = BuildConfig.API_URL + "/api/v1/users/me/devices"
    val ENDPOINT_getCurrentLocation = BuildConfig.API_URL + "/api/v1/locations/current"
    val ENDPOINT_getMessages = BuildConfig.API_URL + "/api/v1/conversations/{conversation_id}/messages"
    val ENDPOINT_updateHelpersPosition = BuildConfig.API_URL + "api/rides/v1/drivers/set_position"
    val ENDPOINT_placeBid = BuildConfig.API_URL + "/api/v1/tasks/{task_id}/bids"
    val ENDPOINT_takeJob = BuildConfig.API_URL + "/api/v1/tasks/{task_id}/bids"
    val ENDPOINT_logout = BuildConfig.API_URL + "/api/v1/oauth/revoke"
    val ENDPOINT_forgotPassword = BuildConfig.API_URL + "/api/v1/password_resets"
    val ENDPOINT_postTaskNormal = BuildConfig.API_URL + "/api/v1/tasks/normal"

    val ENDPOINT_uploadTaskDocument = BuildConfig.API_URL + "/api/v1/tasks/{task_id}/documents"
    val ENDPOINT_getTotalTasksWorld = BuildConfig.API_URL + "/api/v1/tasks/statistics"



}