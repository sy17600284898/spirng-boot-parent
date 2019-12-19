package com.dmsdbj.itoo.netbug.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.netbug.util.HttpClientUtil;
import com.dmsdbj.itoo.netbug.util.downloadFile;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @author ASUS
 * @Class_NAME jinMainLine
 * @Auther 侯旭日
 * @Date 2019/3/8 14:36
 */
//今目标主线
public class jinMainLine {


    public static void getMainLine(String jsessionID, HttpServletRequest request, HttpServletResponse response) {

        //2.创建主线文件夹
        //设定为当前文件夹
        File directory = new File("");
        String path = directory.getAbsolutePath() + "/" + "mainLine";
        //获取绝对路径
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        Date date = new Date();
        //获取时间戳,用来补充请求中的参数
        String timeStamp = String.valueOf(date.getTime());

        //1.得到用户的用户名和手机号
        String userUrl = "https://uatcommercial.lenovo.com/api-asset/products/getproducttypeview";
        CloseableHttpResponse urlResponse = HttpClientUtil.doHttpsGet(userUrl, jsessionID);
        HttpEntity urlResponseEntity = urlResponse.getEntity();
        String fileName = "";
        try {
            String s = EntityUtils.toString(urlResponseEntity);
            JSONObject jsonObjectUser = JSON.parseObject(s);
            JSONObject data = jsonObjectUser.getJSONObject("data");
            if (data.get("productTypeCountVO") == null) {
                return;
            }
            String userName = data.get("productTypeCountVO").toString();
            String userId = data.get("productsForSearch").toString();
            fileName = userName + userId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.创建project文件夹
        path = path + "/" + fileName;
        //获取绝对路径
        File filew = new File(path);
        if (!filew.exists()) {
            filew.mkdir();
        }

        //1.请求得到所有的主线
        String url = "https://uatcommercial.lenovo.com/api-asset/products/search?pageIndex=1&pageSize=15";
        CloseableHttpResponse urlResponssde = HttpClientUtil.doHttpsGet(url, jsessionID);
        HttpEntity urlResponseEntityd = urlResponssde.getEntity();
        try {
            String s = EntityUtils.toString(urlResponseEntityd, "UTF-8");
            JSONObject jsonObjectUser = JSON.parseObject(s);
            JSONArray jsonArray = jsonObjectUser.getJSONArray("list");
            if (jsonArray == null) {
                return;
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                /**
                 * 2.创建每一个主线的文件夹
                 * 获取绝对路径
                 */
                File f = new File(path + "/" + jsonArray.getJSONObject(i).get("name"));
                if (!f.exists()) {
                    f.mkdir();
                }
                /**
                 * 3.得到一级主线文本框的说明
                 */
                String urll = "https://web.jingoal.com/janus/igoal/v1/nodes?parentId=" + jsonArray.getJSONObject(i).get("id") + "&parentType=1&" + timeStamp + "=1";
                CloseableHttpResponse urlResponsee = HttpClientUtil.doHttpsGet(urll, jsessionID);
                HttpEntity urlResponseEntityy = urlResponsee.getEntity();
                if (urlResponseEntityy == null) {
                    return;
                }

                String ss = EntityUtils.toString(urlResponseEntityy, "UTF-8");
                JSONObject jsonObjectUserr = JSON.parseObject(ss);
                JSONArray jsonArr = jsonObjectUserr.getJSONArray("frames");
                for (int j = 0; j < jsonArr.size(); j++) {
                    //得到二级文本框的说明并创建文件
                    File secondFile = new File(f.getAbsolutePath(), jsonArr.getJSONObject(j).get("title") + ".html");
                    if (!secondFile.exists()) {
                        secondFile.createNewFile();
                    }
                    String urlll = "https://web.jingoal.com/janus/igoal/v1/frames/" + jsonArr.getJSONObject(j).get("id") + "?b" + timeStamp + "=1";
                    CloseableHttpResponse urlResponsees = HttpClientUtil.doHttpsGet(urlll, jsessionID);
                    HttpEntity urlResponseEntityyy = urlResponsees.getEntity();
                    String sss = EntityUtils.toString(urlResponseEntityyy, "UTF-8");
                    JSONObject jsonObjectUserrr = JSON.parseObject(sss);
                    String content = "";
                    if (jsonObjectUserrr.containsKey("content")) {
                        content = jsonObjectUserrr.get("content").toString();
                    }

                    /**用来写入文件的便捷类*/
                    FileWriter fileWriter1 = new FileWriter(secondFile.getAbsoluteFile());
                    BufferedWriter bw1 = new BufferedWriter(fileWriter1);
                    bw1.write(content);
                    bw1.close();

                    /**
                     * 写入三级html
                     */

                    String urlllL = "https://web.jingoal.com/janus/igoal/v1/nodes?parentId=" + jsonArr.getJSONObject(j).get("id") + "&parentType=2&b" + timeStamp + "=1";
                    CloseableHttpResponse urlResponseess = HttpClientUtil.doHttpsGet(urlllL, jsessionID);
                    HttpEntity urlResponseEntityyyy = urlResponseess.getEntity();
                    String ssss = EntityUtils.toString(urlResponseEntityyyy, "UTF-8");
                    JSONObject jsonObjectUserrrr = JSON.parseObject(ssss);
                    JSONArray jsonArrr = jsonObjectUserrrr.getJSONArray("frames");
                    if (jsonArrr != null) {
                        for (int k = 0; k < jsonArrr.size(); k++) {
                            File secondFilee = new File(f.getAbsolutePath(), jsonArrr.getJSONObject(k).get("title") + ".html");
                            if (!secondFilee.exists()) {
                                secondFilee.createNewFile();
                            }
                            String urlllll = "https://web.jingoal.com/janus/igoal/v1/frames/" + jsonArrr.getJSONObject(k).get("id") + "?b" + timeStamp + "=1";
                            CloseableHttpResponse urlResponseesss = HttpClientUtil.doHttpsGet(urlllll, jsessionID);
                            HttpEntity urlResponseEntityyyyy = urlResponseesss.getEntity();
                            String sssss = EntityUtils.toString(urlResponseEntityyyyy, "UTF-8");
                            JSONObject jsonObjectUserrrrr = JSON.parseObject(sssss);
                            String contentt = "";
                            if (jsonObjectUserrrrr.containsKey("content")) {
                                contentt = jsonObjectUserrrrr.get("content").toString();
                            }
                            /**用来写入文件的便捷类*/
                            FileWriter fileWriter1r = new FileWriter(secondFilee.getAbsoluteFile());
                            BufferedWriter bw1w = new BufferedWriter(fileWriter1r);
                            bw1w.write(contentt);
                            bw1w.close();

                            /**
                             * 写入四级html
                             */
                            String urlllLll = "https://web.jingoal.com/janus/igoal/v1/nodes?parentId=" + jsonArrr.getJSONObject(k).get("id") + "&parentType=2&b" + timeStamp + "=1";
                            CloseableHttpResponse urlResponseessss = HttpClientUtil.doHttpsGet(urlllLll, jsessionID);
                            HttpEntity urlResponseEntityyyyyy = urlResponseessss.getEntity();
                            String ssssss = EntityUtils.toString(urlResponseEntityyyyyy, "UTF-8");
                            JSONObject jsonObjectUserrrrrr = JSON.parseObject(ssssss);
                            JSONArray jsonArrrr = jsonObjectUserrrrrr.getJSONArray("items");
                            if (jsonArrrr != null) {
                                for (int l = 0; l < jsonArrrr.size(); l++) {
                                    File secondFileee = new File(f.getAbsolutePath(), jsonArrrr.getJSONObject(l).get("title") + ".html");
                                    if (!secondFileee.exists()) {
                                        secondFileee.createNewFile();
                                    }

                                    String urlllLlllll = "https://web.jingoal.com/janus/igoal/v1/approvals/" + jsonArrrr.getJSONObject(l).get("referId") + "?itemId=" + jsonArrrr.getJSONObject(l).get("id") + "&" + timeStamp + "=1";
                                    CloseableHttpResponse urlResponseesssss = HttpClientUtil.doHttpsGet(urlllLlllll, jsessionID);
                                    HttpEntity urlResponseEntityyyyyyy = urlResponseesssss.getEntity();
                                    String sssssss = EntityUtils.toString(urlResponseEntityyyyyyy, "UTF-8");
                                    JSONObject jsonObjectUserrrrrrr = JSON.parseObject(sssssss);
                                    String jsonArrrrr = "";
                                    if (jsonObjectUserrrrrrr.containsKey("content")) {
                                        jsonArrrrr = jsonObjectUserrrrrrr.get("content").toString();
                                    }
                                    /**用来写入文件的便捷类*/
                                    FileWriter fileWriter1rrr = new FileWriter(secondFileee.getAbsoluteFile());
                                    BufferedWriter bw1www = new BufferedWriter(fileWriter1rrr);
                                    bw1www.write(jsonArrrrr);
                                    bw1www.close();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadFile.compress(directory.getAbsolutePath() + "/" + "mainLine" + "/" + fileName, directory.getAbsolutePath() + "/" + "mainLine" + "/" + fileName + ".zip");
        downloadFile.download(request, response, directory.getAbsolutePath() + "/" + "mainLine" + "/" + fileName + ".zip", fileName);
    }
}
