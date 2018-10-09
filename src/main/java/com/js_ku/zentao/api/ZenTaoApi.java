package com.js_ku.zentao.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.util.text.StringUtil;
import com.js_ku.zentao.IdeaZenTao;
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

		if (StringUtil.isEmpty(prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT)) ||
				StringUtil.isEmpty(prop.getValue(ZenTaoConstant.ZEN_TAO_PSD)) ||
				StringUtil.isEmpty(prop.getValue(ZenTaoConstant.ZEN_TAO_URL))
			){
			return false;
		}

		String getSessionJson = ZenTaoHttpClient.get(prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ZenTaoConstant.ZEN_TAO_API_GET_SESSION_ID_URL);
		if (StringUtil.isEmpty(getSessionJson)){
			IdeaZenTao.setlogin(false);
			return false;
		}
		Result<SessionData> sessionDataResult= getData(getSessionJson,SessionData.class);
		if (!sessionDataResult.isSuccess()){
			IdeaZenTao.setlogin(false);
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
		IdeaZenTao.setlogin(userDataResult.isSuccess());
		return userDataResult.isSuccess();
	}

	public static Integer getSizeBugs(){
		Result<MyBugData> bugDataResult = getBugs();
		if (bugDataResult.isSuccess() && bugDataResult.getData().getBugs() != null && !bugDataResult.getData().getBugs().isEmpty()){
			return bugDataResult.getData().getBugs().size();
		}
		return 0;
	}

	public static Result<MyBugData> getBugs(){
		if (!login()){
			return new Result();
		}
		Map<String,String> param = new HashMap<>();
		param.put(prop.getValue(ZenTaoConstant.ZEN_TAO_SESSION_NAME),prop.getValue(ZenTaoConstant.ZEN_TAO_SESSION_ID));
		String responseJson = ZenTaoHttpClient.get(
				prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ZenTaoConstant.ZEN_TAO_API_MY_BUG_URL,param
		);
		return getData(responseJson, MyBugData.class);
	}

	private static <T> Result getData(String json, Class<T> clazz){

		JSONObject jsonObject = JSON.parseObject(json);
		JSONObject jsonObjectData = JSON.parseObject(jsonObject.getString("data"));

		jsonObject.put("data",jsonObjectData);
		json  = jsonObject.toJSONString();

        Result<T> userResult = JSON.parseObject(json,new TypeReference<Result<T>>(clazz) {});

		return userResult;
	}

	public static String getBugUrlById(Integer id){

		return prop.getValue(ZenTaoConstant.ZEN_TAO_URL)+ String.format(ZenTaoConstant.ZEN_TAO_API_BUG_DETAIL_URL,id);
	}


}
