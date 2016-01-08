package com.sihuan.communication;

import com.android.volley.toolbox.HurlStack;
import com.sihuan.application.ApplicationVariable;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class OkHttpStack extends HurlStack {

   private OkHttpClient okHttpClient;
    private  OkUrlFactory okUrlFactory;

    public OkHttpStack() {

          okHttpClient =  new OkHttpClient();
        try {
            setCertificates(ApplicationVariable.getInstance().getAssets().open("sihuan.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    okUrlFactory =  new OkUrlFactory(okHttpClient);


    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {

            return   okUrlFactory.open(url);


    }

    public void setCertificates(InputStream... certificates)
    {
        try
        {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e)
                {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());


        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

