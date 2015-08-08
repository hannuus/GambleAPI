package com.hannuus.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpClientUtils {
	private static ClientConnectionManager connectionManager = null;
	private static HttpParams httpParams = null;

	static {
		httpParams = new BasicHttpParams();
		ConnManagerParams.setMaxTotalConnections(httpParams, 100);
		ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(50));
		HttpConnectionParams.setConnectionTimeout(httpParams, 50000);
		HttpConnectionParams.setSoTimeout(httpParams, 60000000);
		httpParams.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8"); 
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schreg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		connectionManager = new ThreadSafeClientConnManager(httpParams, schreg);
	}
    
    /**
     * Reset connection timeout and socket timeout for new http connections.
     * @param timeout the timeout value in millisecond.
     */
    public static void setTimeout(int timeout) {
        HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
        HttpConnectionParams.setSoTimeout(httpParams, timeout);
    }
    
    /**
     * Return a new httpClient using default configuration parameters.
     * @return A new HttpClient object.
     */
    public static HttpClient getHttpClient() {
        return new DefaultHttpClient(connectionManager, httpParams);
    }
    
    /**
     * Send a http 'get' request to given url and return the response content.
     * @param url the url that the request will be sent to.
     * @return the response content got from the specified url. 
     */
    public static String getResponseByGet(String url) {
        return getResponseByGet(new DefaultHttpClient(connectionManager, httpParams), url);
    }
    
    /**
     * Send a http 'get' request to given url and return the response content.
     * it uses the apache http single thread model for the connection management. 
     * @param url the url that will send request to.
     * @return  
     */
    public static String getResponseByGetAndSingleThread(String url) {
        return getResponseByGet(new DefaultHttpClient(), url);
    }
    
    /**
     * Send a http 'get' request to given url via the given httpClient object,
     * and return the response content.
     * @param httpClient the apache httpClient object that manages the request.
     * @param url the url that the request will be sent to.
     * @return the response content got from the specified url. 
     */
	public static String getResponseByGet(HttpClient httpClient, String url) {
		HttpGet httpGet = null;
		try {
            httpGet = new HttpGet(url);
            httpGet.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");  
            HttpResponse response = httpClient.execute(httpGet);
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            String output = "";
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toString(entity, "UTF-8");
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (ClientProtocolException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            throw new RuntimeException("Get URL content HTTP error", e);
        } catch (IOException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            throw new RuntimeException("Get URL content IO error", e);
        } finally {
            //httpClient.getConnectionManager().shutdown(); 
        }
    }
    
    /**
     * Send a http 'post' request to given url and return the response content.
     * @param url the url that the request will be sent to.
     * @param paramsMap the parameters will be sent to given url.
     * @return the response content got from the specified url.
     */
	public static String getResponseByPost(HttpClient httpClient, String url, List<BasicNameValuePair> params) {
		if (CollectionUtils.isEmpty(params)) {
			return getResponseByGet(httpClient, url);
		} else {
			return getResponseByPost(httpClient, url, params, null, null);
		}
	}
    
    /**
     * Send a http 'post' request to given url and return the response content.
     * @param url the url that the request will be sent to.
     * @param paramsMap the parameters will be sent to given url.
     * @return the response content got from the specified url.
     */
    public static String getResponseByPost(String url, List<BasicNameValuePair> params) {
        if (CollectionUtils.isEmpty(params)) {
            return getResponseByGet(getHttpClient(), url);
        } else {
            return getResponseByPost(getHttpClient(), url, params, null, null);
        }
    }
    
    /**
     * @param url
     * @param requestXML
     * @param headerMap
     * @return
     */
	public static String getResponseByPost(String url, String requestXML, Map<String, String> headerMap) {
		if (StringUtils.isBlank(requestXML)) {
			return getResponseByGet(getHttpClient(), url);
		} else {
			return getResponseByPost(getHttpClient(), url, null, requestXML, headerMap);
		}
	}
    
    /**
     * get response by post method
     * @param httpClient
     * @param url
     * @param paramsMap
     * @return
     */
	private static String getResponseByPost(HttpClient httpClient, String url, List<BasicNameValuePair> params, 
			String requestXML, Map<String, String> headerMap) {
		HttpPost httpPost = new HttpPost(url);
		try {
            //add params
            if (CollectionUtils.isNotEmpty(params)) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>(params);
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            } else {
                StringEntity reqEntity = new StringEntity(requestXML, "UTF-8");
                httpPost.setEntity(reqEntity);
            }
            
            //add http header
            if (MapUtils.isNotEmpty(headerMap)) {
                List<Header> headers = new ArrayList<Header>();
                for (String name : headerMap.keySet()) {
                    Header header = new BasicHeader(name, headerMap.get(name));
                    headers.add(header);
                }
                httpPost.setHeaders(headers.toArray(headers.toArray(new Header[headers.size()])));
            }
            httpPost.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");  
            HttpResponse response = httpClient.execute(httpPost);
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            String output = "";
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toString(entity, "UTF-8");
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (UnsupportedEncodingException e) {
            httpPost.abort();
            throw new RuntimeException("URL encoding error", e);
        } catch (ClientProtocolException e) {
            httpPost.abort();
            throw new RuntimeException("Get URL content HTTP error", e);
        } catch (IOException e) {
            httpPost.abort();
            throw new RuntimeException("Get URL content IO error", e);
        }
    }
	
	public static void downloadFile(String url, String savePath) throws IOException {
		HttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(url);
		FileOutputStream output = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			File storeFile = new File(savePath);
			output = new FileOutputStream(storeFile);
			InputStream read = response.getEntity().getContent();
			IOUtils.copy(read, output);
			output.flush();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
	
	public static void main(String[] args) {
		String res = HttpClientUtils.getResponseByGet("http://www.baidu.com");
		System.out.println(res);
	}

}
