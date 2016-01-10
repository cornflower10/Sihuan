package com.sihuan.communication;

/**
 * Created by admin on 2016/1/10.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.os.SystemClock;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.RedirectError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.PoolingByteArrayOutputStream;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.RedirectError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.PoolingByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
//import org.apache.http.impl.cookie.DateUtils;

public class   MMMNetwork implements Network {
    protected static final boolean DEBUG;
    private static int SLOW_REQUEST_THRESHOLD_MS;
    private static int DEFAULT_POOL_SIZE;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public MMMNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public MMMNetwork(HttpStack httpStack, ByteArrayPool pool) {
        this.mHttpStack = httpStack;
        this.mPool = pool;
    }

    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        long requestStart = SystemClock.elapsedRealtime();

        while(true) {
            HttpResponse httpResponse = null;
            Object responseContents = null;
            Map responseHeaders = Collections.emptyMap();

            try {
                HashMap e = new HashMap();
                this.addCacheHeaders(e, request.getCacheEntry());
                httpResponse = this.mHttpStack.performRequest(request, e);
                StatusLine statusCode2 = httpResponse.getStatusLine();
                int networkResponse1 = statusCode2.getStatusCode();
                responseHeaders = convertHeaders(httpResponse.getAllHeaders());
                if(networkResponse1 == 304) {
                    Cache.Entry requestLifetime2 = request.getCacheEntry();
                    if(requestLifetime2 == null) {
                        return new NetworkResponse(networkResponse1, (byte[])null, responseHeaders, true, SystemClock.elapsedRealtime() - requestStart);
                    }

                    requestLifetime2.responseHeaders.putAll(responseHeaders);
                    return new NetworkResponse(networkResponse1, requestLifetime2.data, requestLifetime2.responseHeaders, true, SystemClock.elapsedRealtime() - requestStart);
                }

                if(networkResponse1 == 301 || networkResponse1 == 302) {
                    String requestLifetime = (String)responseHeaders.get("Location");
                    request.setRedirectUrl(requestLifetime);
                }

                byte[] responseContents1;
                if(httpResponse.getEntity() != null) {
                    responseContents1 = this.entityToBytes(httpResponse.getEntity());
                } else {
                    responseContents1 = new byte[0];
                }

                long requestLifetime1 = SystemClock.elapsedRealtime() - requestStart;
                this.logSlowRequests(requestLifetime1, request, responseContents1, statusCode2);
                if(networkResponse1 >= 200 && networkResponse1 <= 299 || networkResponse1==503) {
                    return new NetworkResponse(networkResponse1, responseContents1, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                }

                throw new IOException();
            } catch (SocketTimeoutException var12) {
                attemptRetryOnException("socket", request, new TimeoutError());
            } catch (ConnectTimeoutException var13) {
                attemptRetryOnException("connection", request, new TimeoutError());
            } catch (MalformedURLException var14) {
                throw new RuntimeException("Bad URL " + request.getUrl(), var14);
            } catch (IOException var15) {
                boolean statusCode = false;
                NetworkResponse networkResponse = null;
                if(httpResponse == null) {
                    throw new NoConnectionError(var15);
                }

                int statusCode1 = httpResponse.getStatusLine().getStatusCode();
                if(statusCode1 != 301 && statusCode1 != 302) {
                    VolleyLog.e("Unexpected response code %d for %s", new Object[]{Integer.valueOf(statusCode1), request.getUrl()});
                } else {
                    VolleyLog.e("Request at %s has been redirected to %s", new Object[]{request.getOriginUrl(), request.getUrl()});
                }

                if(responseContents == null) {
                    throw new NetworkError(var15);
                }

                networkResponse = new NetworkResponse(statusCode1, (byte[])responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                if(statusCode1 != 401 && statusCode1 != 403) {
                    if(statusCode1 != 301 && statusCode1 != 302) {
                        throw new ServerError(networkResponse);
                    }

                    attemptRetryOnException("redirect", request, new RedirectError(networkResponse));
                } else {
                    attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
                }
            }
        }
    }

    private void logSlowRequests(long requestLifetime, Request<?> request, byte[] responseContents, StatusLine statusLine) {
        if(DEBUG || requestLifetime > (long)SLOW_REQUEST_THRESHOLD_MS) {
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[]{request, Long.valueOf(requestLifetime), responseContents != null?Integer.valueOf(responseContents.length):"null", Integer.valueOf(statusLine.getStatusCode()), Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount())});
        }

    }

    private static void attemptRetryOnException(String logPrefix, Request<?> request, VolleyError exception) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();

        try {
            retryPolicy.retry(exception);
        } catch (VolleyError var6) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
            throw var6;
        }

        request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
    }

    private void addCacheHeaders(Map<String, String> headers, Cache.Entry entry) {
        if(entry != null) {
            if(entry.etag != null) {
                headers.put("If-None-Match", entry.etag);
            }

            if(entry.lastModified > 0L) {
                Date refTime = new Date(entry.lastModified);
                headers.put("If-Modified-Since",refTime.toString()); // DateUtils.formatDate(refTime)
            }

        }
    }

    protected void logError(String what, String url, long start) {
        long now = SystemClock.elapsedRealtime();
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", new Object[]{what, Long.valueOf(now - start), url});
    }

    private byte[] entityToBytes(HttpEntity entity) throws IOException, ServerError {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(this.mPool, (int)entity.getContentLength());
        byte[] buffer = null;

        try {
            InputStream in = entity.getContent();
            if(in == null) {
                throw new ServerError();
            } else {
                buffer = this.mPool.getBuf(1024);

                int count;
                while((count = in.read(buffer)) != -1) {
                    bytes.write(buffer, 0, count);
                }

                byte[] var6 = bytes.toByteArray();
                return var6;
            }
        } finally {
            try {
                entity.consumeContent();
            } catch (IOException var13) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }

            this.mPool.returnBuf(buffer);
            bytes.close();
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headers) {
        TreeMap result = new TreeMap(String.CASE_INSENSITIVE_ORDER);

        for(int i = 0; i < headers.length; ++i) {
            result.put(headers[i].getName(), headers[i].getValue());
        }

        return result;
    }

    static {
        DEBUG = VolleyLog.DEBUG;
        SLOW_REQUEST_THRESHOLD_MS = 3000;
        DEFAULT_POOL_SIZE = 4096;
    }
}
