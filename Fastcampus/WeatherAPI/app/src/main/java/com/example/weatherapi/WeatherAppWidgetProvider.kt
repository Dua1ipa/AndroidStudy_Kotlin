package com.example.weatherapi

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class WeatherAppWidgetProvider: AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetIds ->
            val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
                .let { intent ->
                    PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE)
                }

            val views: RemoteViews = RemoteViews(context.packageName, R.layout.widget_weather).apply {
                setOnClickPendingIntent(R.id.temperatureTextView, pendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetIds, views)
        }
    }
}