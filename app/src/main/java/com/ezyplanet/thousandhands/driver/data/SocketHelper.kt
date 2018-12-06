package com.ezyplanet.thousandhands.driver.data

import android.content.Context
import android.util.Log
import com.ezyplanet.thousandhands.core.util.extension.fromJson
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.response.User
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent

import com.ezyplanet.thousandhands.driver.data.network.wraper.FayeMessageWrapper
import com.ezyplanet.thousandhands.driver.viewmodel.SocketEventVM
import com.ezyplanet.thousandhands.util.livedata.HotEventRx
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketHelper @Inject constructor(val appDataManager: AppDataManager) {
    private  var user :User?=null
    private var  socket: Socket?=null
    var context:Context?=null
    private var socketEventVM: SocketEventVM?=null




    fun startWebSocketService(socketEventVM: SocketEventVM?) {

        user= appDataManager.getUserInfo()
        if (user == null) {
            return
        }
        if (socket != null && socket!!.connected()) {
            return
        }
        // Set default ssl context
        if (appDataManager.setting == null) {
            return
        }
        this.socketEventVM = socketEventVM
        val opts = IO.Options()
        opts.forceNew = true
        opts.secure = true
        opts.query = "authToken=" + appDataManager.appPreferenceHelper.socketClient + "&userid=" + user?.id
        try {
            socket = IO.socket(appDataManager?.setting?.socket_io_endpoint, opts)
            socket?.on(Socket.EVENT_CONNECTING, Emitter.Listener { Timber.d("EVENT_CONNECTING ") })
            socket?.on(Socket.EVENT_RECONNECT_ATTEMPT, Emitter.Listener { Timber.d("EVENT_RECONNECT_ATTEMPT") })
            socket?.on(Socket.EVENT_CONNECT, onConnect)
            socket?.on(Socket.EVENT_DISCONNECT, onDisconnect)
            socket?.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
            socket?.on(Socket.EVENT_ERROR, onConnectError)
            socket?.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
            socket?.on("messages", onNewMessage)
            socket?.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun disconnect(){
        socket?.disconnect()

    }


    private val onConnect = Emitter.Listener { args -> Log.d("SocketHelper","onConnected " + args.toString()) }

    private val onDisconnect = Emitter.Listener { args -> Log.d("SocketHelper","onDisconnect " + args.toString()) }

    private val onConnectError = Emitter.Listener { args -> Log.d("SocketHelper","onConnectError " + args.toString()) }

    private val onNewMessage = Emitter.Listener { args ->
        try {
            if (user == null) {
                if (socket != null) {
                    socket?.disconnect()
                }
                return@Listener
            }

            val json = args[0] as JSONObject
            Log.d("SocketHelper", "Received message " + json.toString())
            ConvertToEventData(json.toString())
         //   Toast.makeText(context,wrapper.eventName.toString(),Toast.LENGTH_SHORT).show()



        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

   private fun ConvertToEventData( json:String) {
       val wrapper = Gson().fromJson(json, FayeMessageWrapper::class.java)
       when (wrapper.eventName) {
           "trip_booked" -> {

               val result = Gson().fromJson<BookTripEvent>(json,BookTripEvent::class.java)

               socketEventVM?.onNewMessage?.postValue(HotEventRx(result))
           }


       }
   }




}