/*
 * Copyright (c) 2015, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.sdk.module.messagecenter.view;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;

import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;


import com.apptentive.android.sdk.R;
import com.apptentive.android.sdk.module.messagecenter.MessageManager;
import com.apptentive.android.sdk.util.Util;


/**
 * @author Barry Li
 */
public class MessageCenterComposingView extends FrameLayout implements MessageCenterListItemView {

	private EditText et;

	public MessageCenterComposingView(Context context, final MessageAdapter.OnComposingActionListener listener) {
		super(context);

		LayoutInflater inflater = LayoutInflater.from(context);
		View parentView = inflater.inflate(R.layout.apptentive_message_center_composing_area, this);
		et = (EditText) parentView.findViewById(R.id.composing_et);

		et.addTextChangedListener(new TextWatcher() {
			private boolean doScroll = false;

			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				listener.onComposing(editable.toString(), doScroll);
			}
		});

	}


	public EditText getEditText() {
		return et;
	}
}