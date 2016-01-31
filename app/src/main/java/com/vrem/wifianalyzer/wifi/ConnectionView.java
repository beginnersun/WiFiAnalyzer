/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.vrem.wifianalyzer.wifi;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.wifi.model.WiFiData;
import com.vrem.wifianalyzer.wifi.model.WiFiDetails;

public class ConnectionView implements UpdateNotifier {
    private final MainContext mainContext = MainContext.INSTANCE;
    private final Activity activity;
    private AccessPointsDetails accessPointsDetails;

    public ConnectionView(@NonNull Activity activity) {
        this.activity = activity;
        setAccessPointsDetails(new AccessPointsDetails());
        mainContext.getScanner().addUpdateNotifier(this);
    }

    @Override
    public void update(@NonNull WiFiData wifiData) {
        View view = activity.findViewById(R.id.connection);

        WiFiDetails connection = wifiData.getConnection();
        if (connection != null && connection.isConnected()) {
            view.setVisibility(View.VISIBLE);
            accessPointsDetails.setView(activity.getResources(), view, connection, false);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    void setAccessPointsDetails(@NonNull AccessPointsDetails accessPointsDetails) {
        this.accessPointsDetails = accessPointsDetails;
    }
}