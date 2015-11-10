/*
 * Copyright (c) 2015, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.sdk.module.messagecenter.view.holder;

import android.view.View;
import android.widget.TextView;

import com.apptentive.android.sdk.R;

import com.apptentive.android.sdk.model.StoredFile;
import com.apptentive.android.sdk.module.messagecenter.view.ApptentiveAvatarView;
import com.apptentive.android.sdk.module.messagecenter.view.CompoundMessageView;
import com.apptentive.android.sdk.util.image.ApptentiveImageGridView;
import com.apptentive.android.sdk.util.image.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Barry Li
 */
public class IncomingCompoundMessageHolder extends MessageHolder {

	public ApptentiveAvatarView avatar;
	private TextView messageBodyView;
	private TextView nameView;
	private ApptentiveImageGridView imageBandView;

	public IncomingCompoundMessageHolder(CompoundMessageView view) {
		super(view);
		avatar = (ApptentiveAvatarView) view.findViewById(R.id.avatar);
		nameView = (TextView) view.findViewById(R.id.sender_name);
		messageBodyView = (TextView) view.findViewById(R.id.apptentive_compound_message_body);
		imageBandView =(ApptentiveImageGridView) view.findViewById(R.id.grid);
	}

	public void updateMessage(String name, String datestamp, String text, final List<StoredFile> imagesToAttach) {
		super.updateMessage(datestamp, 0, null);

		if (messageBodyView != null) {
			messageBodyView.setText(text);
		}

		if (name != null && !name.isEmpty()) {
			nameView.setVisibility(View.VISIBLE);
			nameView.setText(name);
		} else {
			nameView.setVisibility(View.GONE);
		}

		// Set up attahments view
		if (imageBandView != null) {
			if (imagesToAttach == null || imagesToAttach.size() == 0) {
				imageBandView.setVisibility(View.GONE);
			} else {
				imageBandView.setVisibility(View.VISIBLE);
				List<ImageItem> images = new ArrayList<ImageItem>();
				for (StoredFile file: imagesToAttach) {
					images.add(new ImageItem(file.getOriginalUriOrPath(), file.getLocalFilePath(), file.getCreationTime()));
				}
				imageBandView.setData(images);
			}
		}
	}
}