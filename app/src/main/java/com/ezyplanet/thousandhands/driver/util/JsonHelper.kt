package com.ezyplanet.thousandhands.driver.util


import com.ezyplanet.thousandhands.driver.data.network.response.User
import com.google.gson.Gson

class JsonHelper {


     companion object {


//    private val TAG = "JsonHelper"
//
//    fun getPlaceHistoryFromJson(json: String): List<PlaceAddress>? {
//        try {
//            val gson = Gson()
//            val listType = object : TypeToken<List<PlaceAddress>>() {
//
//            }.type
//            try {
//                return gson.fromJson<List<PlaceAddress>>(json, listType)
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun placeAddressToJson(places: List<PlaceAddress>): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(places)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }

         fun getUserfromJson(json: String?): User? {
             try {
                 val gson = Gson()
                 var user: User?=null
                 try {
                     user = gson.fromJson<User>(json, User::class.java)

                 } catch (error: NoClassDefFoundError) {
                     return user
                 }

                 return user
             } catch (e: Exception) {
                 //  Log.e(TAG, "Exception : " + e.message)
                 return null
             }

         }

//    fun getSavePostfromJson(json: String): SavePost? {
//        try {
//            val gson = Gson()
//            val savePost: SavePost
//            try {
//                savePost = gson.fromJson<Any>(json, SavePost::class.java)
//
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//            return savePost
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun SavePostToJson(savePost: SavePost): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(savePost)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getAdsIdFromJson(json: String): List<AdvertisPref>? {
//        try {
//            val gson = Gson()
//            val listType = object : TypeToken<List<AdvertisPref>>() {
//
//            }.type
//            try {
//                return gson.fromJson<List<AdvertisPref>>(json, listType)
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun AdsIdToJson(ads: List<AdvertisPref>): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(ads)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun <T> convertJsonTo(responseBody: ResponseBody, tClass: Class<T>): T? {
//        var tClass = tClass
//        val gson = Gson()
//
//        try {
//            val json = responseBody.string()
//            tClass = gson.fromJson<out Class>(json, tClass.javaClass)
//            return tClass.newInstance()
//        } catch (t: Throwable) {
//            if (t.message != null) {
//                Log.e(TAG, "Exception : " + t.message)
//            }
//
//        }
//
//        return null
//    }
//
//
    fun userToJson(user: User?): String? {
        try {
            val gson = Gson()
            return gson.toJson(user)
        } catch (e: Exception) {
           // Log.e(TAG, "Exception : " + e.message)
            return null
        }

    }
//
//    fun payoutToJson(payout: PayoutResponse): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(payout)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    //	private String loadJSONFromAsset() {
//    //		String json = null;
//    //		try {
//    //
//    //			InputStream is = getAssets().open("countrycode.json");
//    //
//    //			int size = is.available();
//    //
//    //			byte[] buffer = new byte[size];
//    //
//    //			is.read(buffer);
//    //
//    //			is.close();
//    //
//    //			json = new String(buffer, "UTF-8");
//    //
//    //
//    //		} catch (IOException ex) {
//    //			ex.printStackTrace();
//    //			return null;
//    //		}
//    //		return json;
//    //
//    //	}
//    fun getCountryCode(context: Context): List<Constants.CountryCode>? {
//
//        val countryCodes = ArrayList<E>()
//        var json: String? = null
//        try {
//
//            val `is` = context.assets.open("countrycode.json")
//
//            val size = `is`.available()
//
//            val buffer = ByteArray(size)
//
//            `is`.read(buffer)
//
//            `is`.close()
//
//            json = String(buffer, "UTF-8")
//
//
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            return null
//        }
//
//        try {
//            val jsonArray = JSONArray(json)
//
//
//            for (i in 0 until jsonArray.length()) {
//                val jo_inside = jsonArray.getJSONObject(i)
//                val countryCode = Constants.CountryCode()
//                countryCode.setDial_code(jo_inside.getString("dial_code"))
//                countryCode.setCode(jo_inside.getString("code"))
//                countryCode.setName(jo_inside.getString("name"))
//                countryCodes.add(countryCode)
//
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//
//        return countryCodes
//
//    }
//
//    // for caching
//
//    fun getCategoryFromJson(json: String): List<CategoriesResponse>? {
//        try {
//            val gson = Gson()
//            val listType = object : TypeToken<List<CategoriesResponse>>() {
//
//            }.type
//            try {
//                return gson.fromJson<List<CategoriesResponse>>(json, listType)
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun CatToJson(cat: ArrayList<CategoriesResponse>): String? {
//        try {
//            val gson = Gson()
//
//            return gson.toJson(cat)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getBannerfromJson(json: String): List<BannerResp>? {
//        try {
//            val gson = Gson()
//            val listType = object : TypeToken<List<BannerResp>>() {
//
//            }.type
//            try {
//                return gson.fromJson<List<BannerResp>>(json, listType)
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun BannerToJson(banner: ArrayList<BannerResp>): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(banner)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getSettingfromJson(json: String): Setting? {
//        try {
//            val gson = Gson()
//            var setting: Setting? = null
//            try {
//                setting = gson.fromJson<Any>(json, Setting::class.java)
//
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//            return setting
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun SettingToJson(setting: Setting): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(setting)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getMobileVersionFromJson(json: String): MobileVersionResp? {
//        try {
//            val gson = Gson()
//            var mobileVersion: MobileVersionResp? = null
//            try {
//                mobileVersion = gson.fromJson<Any>(json, MobileVersionResp::class.java)
//
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//            return mobileVersion
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun MobileVersionToJson(versionResp: MobileVersionResp): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(versionResp)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getCartInfoFromJson(json: String): CartResp? {
//        try {
//            val gson = Gson()
//            var cartResp: CartResp? = null
//            try {
//                cartResp = gson.fromJson<Any>(json, CartResp::class.java)
//
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//            return cartResp
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun CartInfoToJson(cartResp: CartResp): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(cartResp)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun getThGreenCatFromJson(json: String): CategoriesResponse? {
//        try {
//            val gson = Gson()
//            var cartResp: CategoriesResponse? = null
//            try {
//                cartResp = gson.fromJson<Any>(json, CategoriesResponse::class.java)
//
//            } catch (error: NoClassDefFoundError) {
//                return null
//            }
//
//            return cartResp
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
//
//    fun ThGreenCatToJson(cat: CategoriesResponse): String? {
//        try {
//            val gson = Gson()
//            return gson.toJson(cat)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception : " + e.message)
//            return null
//        }
//
//    }
     }

}