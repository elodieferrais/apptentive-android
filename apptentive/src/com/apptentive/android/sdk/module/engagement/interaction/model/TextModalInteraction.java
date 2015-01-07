/*
 * Copyright (c) 2014, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.sdk.module.engagement.interaction.model;

import com.apptentive.android.sdk.Log;
import com.apptentive.android.sdk.module.engagement.interaction.model.common.Actions;
import com.apptentive.android.sdk.util.Util;
import org.json.JSONException;

/**
 * @author Sky Kelsey
 */
public class TextModalInteraction extends Interaction {

	private static final String KEY_LAYOUT = "layout";
	private static final String KEY_TITLE = "title";
	private static final String KEY_BODY = "body";
	private static final String KEY_TEXT_COLOR = "text_color";
	private static final String KEY_BUTTON_TEXT_COLOR = "button_text_color";
	private static final String KEY_PRIMARY_COLOR = "primary_color";
	private static final String KEY_SECONDARY_COLOR = "secondary_color";
	private static final String KEY_ACTIONS = "actions";

	public static final String EVENT_NAME_LAUNCH = "launch";
	public static final String EVENT_NAME_CANCEL = "cancel";
	public static final String EVENT_NAME_DISMISS = "dismiss";
	public static final String EVENT_NAME_INTERACTION = "interaction";

	public TextModalInteraction(String json) throws JSONException {
		super(json);
	}

	public static enum Layout {
		center,
		bottom,
		unknown;

		public static Layout parse(String name) {
			try {
				return Layout.valueOf(name);
			} catch (IllegalArgumentException e) {
				Log.v("Error parsing unknown TextModalInteraction.Layout: " + name);
			}
			return unknown;
		}
	}

	public Layout getLayout() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_LAYOUT)) {
				return Layout.parse(configuration.getString(KEY_LAYOUT));
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public String getTitle() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_TITLE)) {
				return configuration.getString(KEY_TITLE);
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public String getBody() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_BODY)) {
				return configuration.getString(KEY_BODY);
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public Integer getTextColor() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_TEXT_COLOR)) {
				try {
					return Util.parseWebColorAsAndroidColor(configuration.getString(KEY_TEXT_COLOR));
				} catch (IllegalArgumentException e) {
					// Return null
				}
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public Integer getButtonTextColor() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_BUTTON_TEXT_COLOR)) {
				try {
					return Util.parseWebColorAsAndroidColor(configuration.getString(KEY_BUTTON_TEXT_COLOR));
				} catch (IllegalArgumentException e) {
					// Return null
				}
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public Integer getPrimaryColor() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_PRIMARY_COLOR)) {
				try {
					return Util.parseWebColorAsAndroidColor(configuration.getString(KEY_PRIMARY_COLOR));
				} catch (IllegalArgumentException e) {
					// Return null
				}
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public Integer getSecondaryColor() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_SECONDARY_COLOR)) {
				try {
					return Util.parseWebColorAsAndroidColor(configuration.getString(KEY_SECONDARY_COLOR));
				} catch (IllegalArgumentException e) {
					// Return null
				}
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}

	public Actions getActions() {
		try {
			InteractionConfiguration configuration = getConfiguration();
			if (configuration != null && configuration.has(KEY_ACTIONS)) {
				return new Actions(configuration.getString(KEY_ACTIONS));
			}
		} catch (JSONException e) {
			// Ignore
		}
		return null;
	}


}