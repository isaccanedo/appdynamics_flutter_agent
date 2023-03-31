/*
 * Copyright (c) 2022. AppDynamics LLC and its affiliates.
 * All rights reserved.
 *
 */

package com.appdynamics.appdynamics_agent.features.request_tracking.tracker_user_data

import androidx.annotation.NonNull
import com.appdynamics.appdynamics_agent.AppDynamicsAgentPlugin
import io.flutter.plugin.common.MethodChannel
import java.util.*

fun AppDynamicsAgentPlugin.setRequestTrackerUserDataDouble(
    @NonNull result: MethodChannel.Result,
    arguments: Any?
) {
    val properties = arguments as HashMap<*, *>
    val id = properties["id"] as String

    val tracker = requestTrackers[id] ?: run {
        result.error(
            "500",
            "Agent setRequestTrackerUserDataDouble() failed.",
            "Request tracker was not initialized or already reported."
        )
        return
    }

    val key = properties["key"] as? String ?: run {
        result.error(
            "500",
            "Agent setRequestTrackerUserDataDouble() failed.",
            "Please provide a valid string for `key`."
        )
        return
    }

    val value = properties["value"] as? Double ?: run {
        result.error(
            "500",
            "Agent setRequestTrackerUserDataDouble() failed.",
            "Please provide a valid double for `value`."
        )
        return
    }

    tracker.withUserDataDouble(key, value)
    result.success(null)
}