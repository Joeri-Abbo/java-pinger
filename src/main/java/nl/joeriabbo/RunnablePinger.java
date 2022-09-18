package nl.joeriabbo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

public class RunnablePinger implements Runnable {
    private String url;
    private Integer timeout;

    public RunnablePinger() {
    }

    public RunnablePinger(String url, Integer timeout) {
        this.url = url;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int i = 0;
        while (true) {
            i = i + 1;
            try {
                URL obj = new URL(getUrl());
                System.out.println(threadName + " : Starting connection " + i);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(getTimeout() * 1000);

                int responseCode = con.getResponseCode();
                System.out.println(threadName + " : Status code " + responseCode);
                System.out.println(threadName + " : content " + con.getContent());
            } catch (java.net.SocketTimeoutException e) {
                System.out.println(threadName + "Something went wrong socket timeout");
            } catch (java.io.IOException e) {
                System.out.println(threadName + "Io exception is thrown");
            }
            System.out.println(threadName + " : Disconnection connection " + i);

        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
