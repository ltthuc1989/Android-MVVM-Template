package com.ezyplanet.thousandhands.core.util


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log

import com.ezyplanet.thousandhands.core.R


/**
 * Created by misotomovski on 2/5/16.
 */
class AlertUtils {

   companion object {


       fun showOkAlert(context: Context, title: String, message: String) {
           try {

               val builder = AlertDialog.Builder(context)
               builder.setTitle(title)
               builder.setMessage(message)
               builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }
               val alertDialog = builder.create()


               alertDialog.show()
           } catch (e: Exception) {
               Log.e("ShowOkAlert", "Exception: " + e.message)
           }

       }

       fun showOkAlert(context: Context, title: Int, message: Int) {
           try {
               //            SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
               //            pDialog.getProgressHelper().setBarColor(Color.parseColor("#fb8462"));
               //
               //            pDialog.setTitleText(context.getString(title));
               //
               //            pDialog.setContentText(context.getString(message));
               //            pDialog.setCancelable(true);
               //            pDialog.show();
               val builder = AlertDialog.Builder(context)
               builder.setTitle(title)
               builder.setMessage(message)
               builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }
               val alertDialog = builder.create()
               alertDialog.setCanceledOnTouchOutside(true)


               alertDialog.show()
           } catch (e: Exception) {
               Log.e("ShowOkAlert", "Exception: " + e.message)
           }

       }

       fun showOkAlert(context: Context, title: String, message: String, listener: DialogInterface.OnDismissListener) {
           try {

               val builder = AlertDialog.Builder(context)
               builder.setTitle(title)
               builder.setMessage(message)
               builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }
               val alertDialog = builder.create()
               alertDialog.setCanceledOnTouchOutside(false)
               alertDialog.setOnDismissListener(listener)

               alertDialog.show()
           } catch (e: Exception) {
               Log.e("ShowOkAlert", "Exception: " + e.message)
           }

       }

       fun showOkAlert(context: Context, title: String, message: String, listener: DialogInterface.OnDismissListener, isCancel: Boolean) {
           try {

               val builder = AlertDialog.Builder(context)
               builder.setTitle(title)
               builder.setMessage(message)
               builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }
               val alertDialog = builder.create()
               alertDialog.setCanceledOnTouchOutside(isCancel)
               alertDialog.setOnDismissListener(listener)

               alertDialog.show()
           } catch (e: Exception) {
               Log.e("ShowOkAlert", "Exception: " + e.message)
           }

       }

       //    public static void showOkAlertCustom(Context context, String title, String message){
       //        try {
       //            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
       //            //builder.setTitle(title);
       //            View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_2,null);
       //            TextViewPlus tvContent =(TextViewPlus)view.findViewById(R.id.tvAlertContent);
       //            tvContent.setText(message);
       //            builder.setView(view);
       //
       //            builder.setMessage(message)
       //                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
       //                        public void onClick(DialogInterface dialog, int id) {
       //                            //do nothing
       //                        }
       //                    });
       //            android.support.v7.app.AlertDialog alert = builder.create();
       //            alert.show();
       //        }
       //        catch (Exception e){
       //            Log.e("ShowOkAlert", "Exception: " + e.getMessage());
       //        }
       //    }
       fun showOkAlertDialog(context: Context, message: String?) {

           val builder = AlertDialog.Builder(context)
           builder.setMessage(message)
           builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }


           val alertDialog = builder.create()
           alertDialog.setCanceledOnTouchOutside(false)


           alertDialog.show()
       }

       fun showOkAlertDialog(context: Context, resId: Int) {
           showOkAlertDialog(context, context.getString(resId))
       }

       fun showOkAlertDialog(context: Context, message: String, listener: DialogInterface.OnDismissListener) {
           val builder = AlertDialog.Builder(context)
           builder.setMessage(message)
           builder.setPositiveButton(context.getString(R.string.OK)) { dlog, id -> dlog.dismiss() }
           val alertDialog = builder.create()
           alertDialog.setCanceledOnTouchOutside(false)
           alertDialog.setOnDismissListener(listener)

           alertDialog.show()
       }

       fun showOkAlertDialog(context: Context, message: String, listener: DialogInterface.OnClickListener) {
           val builder = AlertDialog.Builder(context)
           builder.setMessage(message)
           builder.setPositiveButton(context.getString(R.string.OK), listener)

           val alertDialog = builder.create()
           alertDialog.setCanceledOnTouchOutside(false)
           // alertDialog.setOnDismissListener(listener);

           alertDialog.show()
       }


   }
}
