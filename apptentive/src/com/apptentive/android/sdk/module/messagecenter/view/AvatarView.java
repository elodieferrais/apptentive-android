/*
 * Copyright (c) 2015, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.sdk.module.messagecenter.view;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.apptentive.android.sdk.Log;
import com.apptentive.android.sdk.module.metric.MetricModule;

import java.io.IOException;
import java.net.URL;


/**
 * @author Sky Kelsey
 */
public class AvatarView extends ImageView {

	Bitmap avatar;
	int avatarWidth;
	int avatarHeight;
	Matrix shaderMatrix;
	BitmapShader shader;
	Paint shaderPaint;
	Rect viewRect;
	int imageRadius;

	public AvatarView(Context context) {
		super(context);
	}

	public AvatarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (getDrawable() != null) {
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, imageRadius, shaderPaint);
		}
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(bm);
		avatar = bm;
		setup();
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		super.setImageDrawable(drawable);
		avatar = getBitmapFromDrawable(drawable);
		setup();
	}

	@Override
	public void setImageResource(int resId) {
		super.setImageResource(resId);
		avatar = getBitmapFromDrawable(getDrawable());
		setup();
	}

	@Override
	public void setImageURI(Uri uri) {
		super.setImageURI(uri);
		avatar = getBitmapFromDrawable(getDrawable());
		setup();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		setup();
	}

	private Bitmap getBitmapFromDrawable(Drawable d) {
		if (d == null) {
			return null;
		}

		if (d instanceof BitmapDrawable) {
			return ((BitmapDrawable) d).getBitmap();
		} else {
			try {
				Bitmap b;

				if (d instanceof ColorDrawable) {
					b = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
				} else {
					b = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
				}

				Canvas canvas = new Canvas(b);
				d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
				d.draw(canvas);
				return b;
			} catch (OutOfMemoryError e) {
				Log.w("Error creating bitmap.", e);
				return null;
			}
		}
	}

	protected void setup() {
		if (avatar == null) {
			return;
		}
		avatarWidth = avatar.getWidth();
		avatarHeight = avatar.getHeight();
		shader = new BitmapShader(avatar, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		if (shaderPaint == null) {
			shaderPaint = new Paint();
		}
		shaderPaint.setAntiAlias(true);
		shaderPaint.setShader(shader);

		if (viewRect == null) {
			viewRect = new Rect();
		}
		viewRect.set(0, 0, getWidth(), getHeight());
		imageRadius = Math.min(viewRect.width() / 2, viewRect.height() / 2);

		// setup the matrix
		if (shaderMatrix == null) {
			shaderMatrix = new Matrix();
		}
		shaderMatrix.set(null);

		float scale;
		float deltaX = 0.0f;
		float deltaY = 0.0f;
		if (avatarWidth * viewRect.height() > avatarHeight * viewRect.width()) {
			scale = (float) viewRect.height() / avatarHeight;
			deltaX = (viewRect.height() - avatarWidth * scale) / 2.0f;
		} else {
			scale = (float) viewRect.width() / avatarWidth;
			deltaY = (viewRect.width() - avatarHeight * scale) / 2.0f;
		}
		shaderMatrix.setScale(scale, scale);
		shaderMatrix.postTranslate(deltaX + 0.5f, deltaY + 0.5f);

		shader.setLocalMatrix(shaderMatrix);
		invalidate();
	}

}
