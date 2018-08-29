package com.js_ku.zentao.api.util;


import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ZenTaoHttpClient {

	private ZenTaoHttpClient() {

	}

	/**
	 * 发送 get请求
	 */
	public static String get(String url) {
		return get(url, null, null);
	}
	public static String get(String url, Map<String, String> params) {
		return get(url, params, null);
	}

	private static String get(String url, Map<String, String> params, String charset) {

		try (CloseableHttpClient httpClient = HttpClients.createDefault();) {

			List<NameValuePair> nameValuePairParams = buildNameValuePairParams(params);

			String newUrl = url;
			if (null != nameValuePairParams && !nameValuePairParams.isEmpty()) {

				String urlParam = URLEncodedUtils.format(nameValuePairParams, charset);

				if (url.indexOf("?") > -1) {
					newUrl = url + "&" + urlParam;
				} else {
					newUrl = url + "?" + urlParam;
				}
			}

			HttpGet httpGet = new HttpGet(newUrl);

			try (CloseableHttpResponse response = httpClient.execute(httpGet);) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	private static List<NameValuePair> buildNameValuePairParams(Map<String, String> params) {

		List<NameValuePair> nameValuePairParams = new ArrayList<NameValuePair>();

		// 组装POST参数
		if (null != params && !params.isEmpty()) {
			Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = iter.next();
				nameValuePairParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		return nameValuePairParams;
	}


}  