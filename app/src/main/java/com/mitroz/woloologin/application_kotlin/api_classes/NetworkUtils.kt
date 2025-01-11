package com.mitroz.woloologin.application_kotlin.api_classes

import android.content.Context
import android.net.ConnectivityManager
import android.net.Proxy
import android.os.Build

import java.net.NetworkInterface
import java.util.Collections

class NetworkUtils {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    /* public boolean isConnected(Context context) {
        boolean isConnectedStatus = false;
        try {

            //here we first check the PROXY SETTING ENABLE OR NOT
            String connectedTo = checkConnectedToStatus(context);

            //VPN DISABLE CODE
           */
    /* if ("MOBILE_DATA".equals(connectedTo)) {
                isConnectedStatus = true;
            } else if ("WIFI".equals(connectedTo)) {
                //CHECKING WIFI SETTINGS
                isConnectedStatus = true;
            }*/
    /*

            //VPN ENABLE CODE
            boolean isVpnEnabled = isVpnEnabled();
            if ("MOBILE_DATA".equals(connectedTo)) {
                isConnectedStatus = true;
            } else if ("WIFI".equals(connectedTo)) {
                //CHECKING WIFI SETTINGS
                isConnectedStatus = getProxySettingDetails(context);
            } else {
                isConnectedStatus = false;
            }
            if (isVpnEnabled) {
                isConnectedStatus = false;
            }

        } catch (Exception e) {
              CommonUtils.printStackTrace(e)
            return false;
        }
        return isConnectedStatus;
    }*/
    fun isConnected(context: Context): Boolean {


        var isConnectedStatus = false
        try {
            //here we first check the PROXY SETTING ENABLE OR NOT
            val mStringAmazonTv = Build.MANUFACTURER


            // Mobile type vpn check
            //VPN DISABLE CODE
            /* if ("MOBILE_DATA".equals(connectedTo)) {
                isConnectedStatus = true;
            } else if ("WIFI".equals(connectedTo)) {
                //CHECKING WIFI SETTINGS
                isConnectedStatus = true;
            }*/
            //VPN ENABLE CHECK CODE
            val isVpnEnabled = isVpnEnabled


            if (isVpnEnabled) {
                isConnectedStatus = true
            }
        } catch (e: Exception) {

            return false
        }
        return isConnectedStatus
    }

    fun getProxySettingDetails(context: Context?): Boolean {
        var proxyAddress: String? = ""
        var portValue: String? = ""
        val port: Int
        var proxySettingEnable = false
        try {
            if (preICS()) {
                try {
                    proxyAddress = Proxy.getHost(context)
                    port = Proxy.getPort(context)
                    if (proxyAddress == null || port == 0) {
                        proxySettingEnable = true
                    }
                } catch (e: Exception) {

                }
            } else {
                try {
                    proxyAddress = System.getProperty("http.proxyHost")
                    portValue = System.getProperty("http.proxyPort")
                    if (proxyAddress == null || portValue == null || portValue == "0") {
                        proxySettingEnable = true
                    }
                } catch (e: Exception) {

                }
            }
        } catch (ex: Exception) {
            //ex.pr

        }
        return proxySettingEnable
    }

    private fun preICS(): Boolean {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH
    }



    val isVpnEnabled: Boolean
        get() {
            val networkList: MutableList<String> = ArrayList()
            try {
                for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (networkInterface.isUp) networkList.add(networkInterface.name)
                }
            } catch (ex: Exception) {

            }
            return if (networkList.contains("tun0") || networkList.contains("tun1")) {
                true
            } else networkList.contains("ppp")
        }




  }
