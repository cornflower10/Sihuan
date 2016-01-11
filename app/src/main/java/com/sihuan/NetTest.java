package com.sihuan;



import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;



/**
 * Created by admin on 2016/1/8.
 */
public class NetTest {
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        System.out.print("证书路径");
        String path = System.getProperty("user.dir");
        System.out.println(path+"\\app\\src\\main\\assets\\sihuan.cer");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        InputStream certificate = new FileInputStream(new File(path+"\\app\\src\\main\\assets\\sihuan.cer"));
           // InputStream certificate = ApplicationVariable.getInstance().getAssets().open("sihuan.cer");
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        int index = 0;
         //   for (InputStream certificate : certificates) {
        String certificateAlias = Integer.toString(index++);
        keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
        try {
               if (certificate != null)
                   certificate.close();
        } catch (IOException e) {
        }
           // }

        SSLContext sslContext = SSLContext.getInstance("TLS");

        TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        trustManagerFactory.init(keyStore);
        sslContext.init(null, trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
        );
       // }
        mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        Response response =  mOkHttpClient.newCall(new Request.Builder().url("https://mmmoffice.com").build()).execute();
       // String a =  response.message();
        ResponseBody body = response.body();
        String a  =  body.string();
           // HttpURLConnection n;
        System.out.print("MMMGLOBLE:RESPONSE"+a);



    }

}
