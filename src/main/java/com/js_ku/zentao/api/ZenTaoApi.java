package com.js_ku.zentao.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.intellij.ide.util.PropertiesComponent;
import com.js_ku.zentao.api.model.*;
import com.js_ku.zentao.api.util.ZenTaoHttpClient;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by da-li on 2018/2/22.
 */
public class ZenTaoApi {

	private static PropertiesComponent prop = PropertiesComponent.getInstance();

	public ZenTaoApi() {

	}

	public static boolean login(){

		if (isLoginBefore()){
			return true;
		}

		prop.getValue(ZenTaoConstant.ZEN_TAO_URL);
		prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT);
		prop.getValue(ZenTaoConstant.ZEN_TAO_PSD);

		String getSessionJson = ZenTaoHttpClient.get(prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+"/api-getsessionid.json");

		Result<SessionData> sessionDataResult= getData(getSessionJson,SessionData.class);
		if (!sessionDataResult.isSuccess()){
			return false;
		}

		Map<String,String> params = new HashMap<>();
		params.put(ZenTaoConstant.ZEN_TAO_API_PARAM_ACCOUNT,prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT));
		params.put(ZenTaoConstant.ZEN_TAO_API_PARAM_PASSWORD,prop.getValue(ZenTaoConstant.ZEN_TAO_PSD));
		params.put(sessionDataResult.getData().getSessionName(),sessionDataResult.getData().getSessionID());

		String responseJson = ZenTaoHttpClient.get(
				prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ZenTaoConstant.ZEN_TAO_API_LOGIN_URL,params
		);


		Result<UserData> userDataResult= getData(responseJson,UserData.class);
		if (userDataResult.isSuccess()){
			prop.setValue(ZenTaoConstant.ZEN_TAO_SESSION_NAME,sessionDataResult.getData().getSessionName());
			prop.setValue(ZenTaoConstant.ZEN_TAO_SESSION_ID,sessionDataResult.getData().getSessionID());
		}
		return userDataResult.isSuccess();
	}


	public static Integer getBugs(){
		if (!login()){
			return null;
		}
		Map<String,String> param = new HashMap<>();
		param.put(prop.getValue(ZenTaoConstant.ZEN_TAO_SESSION_NAME),prop.getValue(ZenTaoConstant.ZEN_TAO_SESSION_ID));
		String responseJson = ZenTaoHttpClient.get(
				prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ZenTaoConstant.ZEN_TAO_API_MY_BUG_URL,param
		);


		Result<MyBugData> result = getData(responseJson, MyBugData.class);
		if (!result.isSuccess()){
			return 0;
		}
		System.out.println(result.getData().getBugs().size());
		return result.getData().getBugs().size();
	}

	private static <T> Result getData(String json, Class<T> clazz){

		JSONObject jsonObject = JSON.parseObject(json);
		JSONObject jsonObjectData = JSON.parseObject(jsonObject.getString("data"));
		//SOME TIME IS USER SOME TIMES IS DATA ...
		jsonObject.put("data",jsonObjectData);
		json  = jsonObject.toJSONString();

        Result<T> userResult = JSON.parseObject(json,new TypeReference<Result<T>>(clazz) {});

		return userResult;
	}

	//TODO 判断是否已经登录
	private static boolean isLoginBefore(){

		return false;
	}


}
