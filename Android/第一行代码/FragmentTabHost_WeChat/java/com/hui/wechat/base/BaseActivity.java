package com.hui.wechat.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hui.wechat.ChatApplication;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		((ChatApplication) getApplication()).addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		((ChatApplication) getApplication()).removeActivity(this);
	}
}
