package com.hui.news.utils;

public interface HttpCallbackListener {

	void onFinish(String response);

	void onError(Exception e);

}
