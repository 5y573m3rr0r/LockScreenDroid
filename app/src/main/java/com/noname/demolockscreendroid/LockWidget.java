package com.noname.demolockscreendroid;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class LockWidget extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       for(int appWidgetId : appWidgetIds){
         /*  Intent intent = new Intent(context,MainActivity.class);
          PendingIntent widgetPendingIntent = PendingIntent.getActivities( context, 0, intent, 0 );

          RemoteViews remoteViewsForWidget = new RemoteViews( context.getPackageName(),R.layout.lock_widget  );
         remoteViewsForWidget.setOnClickPendingIntent( R.id.lock_widget_image_button,widgetPendingIntent );
          appWidgetManager.updateAppWidget( appWidgetIds, remoteViewsForWidget ); */
           Intent intent = new Intent(context, MainActivity.class);
           PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

           // Get the layout for the App Widget and attach an on-click listener
           // to the button

          // views.setOnClickPendingIntent(R.id.button, pendingIntent);

           //
          RemoteViews views = new RemoteViews( context.getPackageName(),R.layout.lock_widget );
          views.setOnClickPendingIntent( R.id.lock_widget_image_button,pendingIntent );
          appWidgetManager.updateAppWidget( appWidgetId, views );

       }
    }

}