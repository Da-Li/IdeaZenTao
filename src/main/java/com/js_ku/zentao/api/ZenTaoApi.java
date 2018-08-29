package com.js_ku.zentao.api;


import com.alibaba.fastjson.JSON;
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

	public static boolean login(){ //TODO 判断是否已经登录

		prop.getValue(ZenTaoConstant.ZEN_TAO_URL);
		prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT);
		prop.getValue(ZenTaoConstant.ZEN_TAO_PSD);

		String getSessionJson = ZenTaoHttpClient.get(prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+"/api-getsessionid.json");

		SessionData sessionData = getData(getSessionJson,SessionData.class);
		if (sessionData == null){
			return false;
		}
		Map<String,String> params = new HashMap<>();
		params.put(ZenTaoConstant.ZEN_TAO_API_PARAM_ACCOUNT,prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT));
		params.put(ZenTaoConstant.ZEN_TAO_API_PARAM_PASSWORD,prop.getValue(ZenTaoConstant.ZEN_TAO_PSD));
		params.put(sessionData.getSessionName(),sessionData.getSessionID());

		String responseJson = ZenTaoHttpClient.get(
				prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ZenTaoConstant.ZEN_TAO_API_LOGIN_URL,params
		);


		UserData userData = getData(responseJson,UserData.class);
		if (userData == null){
			return false;
		} else {
			prop.setValue(ZenTaoConstant.ZEN_TAO_SESSION_NAME,sessionData.getSessionName());
			prop.setValue(ZenTaoConstant.ZEN_TAO_SESSION_ID,sessionData.getSessionID());
		}
		return true;
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


		MyBugData myBugData = getData(responseJson, MyBugData.class);
		System.out.println(myBugData.getBugs().size());
		return myBugData.getBugs().size();

	}
	private static <T> T getData(String json, Class<T> clazz){

//        Type t = new TypeReference<A>(){}.getType();
//		Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
//		Result<A> userResult = JSON.parseObject(json,type);
//		if (userResult != null && userResult.isSuccess()){
//			return userResult.getData();
//		}

        Result<T> userResult = JSON.parseObject(json,new TypeReference<Result<T>>(clazz) {});

		return userResult.getData();
	}



}
