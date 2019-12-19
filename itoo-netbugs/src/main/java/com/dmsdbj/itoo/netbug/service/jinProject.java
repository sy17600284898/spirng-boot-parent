package com.dmsdbj.itoo.netbug.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.netbug.util.HttpClientUtil;
import com.dmsdbj.itoo.netbug.util.downloadFile;
import lombok.extern.slf4j.Slf4j;
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
 * @Class_NAME jinProject
 * @Auther 张明慧
 * @Date 2019/3/6 21:21
 */
@Slf4j
public class jinProject {

    public static void getProject(String jsessionID, HttpServletRequest request, HttpServletResponse response) {
        //创建文件夹的名称
        String fileName = "";
        Date date = new Date();
        //获取时间戳,用来补充请求中的参数
        String timeStamp = String.valueOf(date.getTime());

        //1.得到用户的用户名和手机号
        String userUrl = "https://web.jingoal.com/mgt/workbench/v1/get_userconfig?b" + timeStamp + "=1";
        CloseableHttpResponse urlResponse = HttpClientUtil.doHttpsGet(userUrl, jsessionID);
        HttpEntity urlResponseEntity = urlResponse.getEntity();
        try {
            String s = EntityUtils.toString(urlResponseEntity);
            JSONObject jsonObjectUser = JSON.parseObject(s);
            String userName = jsonObjectUser.getJSONObject("data").get("user_name").toString();
            if (jsonObjectUser.getJSONObject("data").get("user_name") == null) {
                return;
            }
            String userId = jsonObjectUser.getJSONObject("data").get("user_id").toString();
            fileName = userName + userId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.创建project文件夹
        //设定为当前文件夹
        File directory = new File("");
        //获取绝对路径
        File file = new File(directory.getAbsolutePath() + "/" + "project");
        log.info("创建project文件夹的路径为:{}", directory.getAbsolutePath());
        if (!file.exists()) {
            file.mkdir();
            log.info("开始创建--");
        }
        log.info("创建完成--");
        //在当前目录的project文件夹下创建每一个人的文件夹
        //获取绝对路径
        File file1 = new File(directory.getAbsolutePath() + "/" + "project" + "/" + fileName);
        log.info("创建每一个人的文件夹的路径为:{}", file1);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //3.获得该人的所有的一级项目
        String projectUrl = "https://web.jingoal.com/project/v1/query?pageNo=1&pageSize=50&role=1&status=-1&dayType=1&startDay=0&endDay=0&orderAscTime=false&orderByTime=2&orderByProcess=false&orderAscProcess=false&name=&attention=false&userIds=&showSubProject=false&priority=-1&b" + timeStamp + "=1";
        CloseableHttpResponse closeableHttpResponseProject = HttpClientUtil.doHttpsGet(projectUrl, jsessionID);
        HttpEntity entityProject = closeableHttpResponseProject.getEntity();
        try {
            String contentProject = EntityUtils.toString(entityProject);
            JSONObject jsonObject = JSON.parseObject(contentProject);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("obj_list");
            if (jsonArray == null) {
                return;
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                Object id = jsonArray.getJSONObject(i).get("id");
                //3.获得该人的一级项目的描述
                String url = "https://web.jingoal.com/project/v1/" + id + "?b" + timeStamp + "=1";
                CloseableHttpResponse closeableHttpResponse1 = HttpClientUtil.doHttpsGet(url, jsessionID);
                HttpEntity httpEntity = closeableHttpResponse1.getEntity();
                String content = EntityUtils.toString(httpEntity);
                JSONObject jsonObject1 = JSON.parseObject(content);
                String firstContext = jsonObject1.getJSONObject("data").get("content").toString();
                String firstName = jsonObject1.getJSONObject("data").get("name").toString().replaceAll("/", "-");
                String firstId = jsonObject1.getJSONObject("data").get("id").toString();
                String firstFileName = "一级--" + firstName + firstId + ".html";
                //创建一级文件夹
                //获取绝对路径
                File f = new File(file1.getAbsolutePath() + "/" + firstFileName.replaceAll(".html", ""));
                if (!f.exists()) {
                    f.mkdir();
                }
                //写入一级项目html
                File firstFile = new File(f.getAbsolutePath(), firstFileName);
                if (!firstFile.exists()) {
                    firstFile.createNewFile();
                }
                /**用来写入文件的便捷类*/
                FileWriter fileWriter = new FileWriter(firstFile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(firstContext);
                bw.close();
                // 获取一级项目下的二级项目
                String secondProject = "https://web.jingoal.com/project/v1/" + firstId + "/tree?b" + timeStamp + "=1";
                CloseableHttpResponse secondhttpResponse = HttpClientUtil.doHttpsGet(secondProject, jsessionID);
                HttpEntity secondHttpEntity = secondhttpResponse.getEntity();
                String secondContent = EntityUtils.toString(secondHttpEntity);
                JSONObject secondjsonObject = JSON.parseObject(secondContent);
                JSONArray secondjsonArray = secondjsonObject.getJSONObject("data").getJSONArray("children");
                for (int i1 = 0; i1 < secondjsonArray.size(); i1++) {
                    String id1Second = secondjsonArray.getJSONObject(i1).get("id").toString();
                    //3.获得该人的二级项目的描述
                    String secondUrl = "https://web.jingoal.com/project/v1/" + id1Second + "?b" + timeStamp + "=1";
                    CloseableHttpResponse secondHttpResponse1 = HttpClientUtil.doHttpsGet(secondUrl, jsessionID);
                    HttpEntity httpEntity1 = secondHttpResponse1.getEntity();
                    String content1 = EntityUtils.toString(httpEntity1);
                    JSONObject jsonObject2 = JSON.parseObject(content1);
                    String secondContent1 = "";
                    try {
                        secondContent1 = jsonObject2.getJSONObject("data").get("content").toString();
                    } catch (Exception e) {
                        continue;
                    }
                    String secondName = jsonObject2.getJSONObject("data").get("name").toString().replaceAll("/", "-");
                    String secondId = jsonObject2.getJSONObject("data").get("id").toString();
                    String secondFileName = "二级--" + secondName + secondId + ".html";

                    //写入二级项目html
                    File secondFile = new File(f.getAbsolutePath(), secondFileName);
                    if (!secondFile.exists()) {
                        secondFile.createNewFile();
                    }
                    /**用来写入文件的便捷类*/
                    FileWriter fileWriter1 = new FileWriter(secondFile.getAbsoluteFile());
                    BufferedWriter bw1 = new BufferedWriter(fileWriter1);
                    bw1.write(secondContent1);
                    bw1.close();

                    //获得三级项目内容
                    JSONArray thirdjsonArray = secondjsonArray.getJSONObject(i1).getJSONArray("children");
                    for (int i2 = 0; i2 < thirdjsonArray.size(); i2++) {
                        String id1third = thirdjsonArray.getJSONObject(i2).get("id").toString();
                        //3.获得该人的二级项目的描述
                        String thirdUrl = "https://web.jingoal.com/project/v1/" + id1third + "?b" + timeStamp + "=1";
                        CloseableHttpResponse thirdHttpResponse = HttpClientUtil.doHttpsGet(thirdUrl, jsessionID);
                        HttpEntity thirdhttpEntity = thirdHttpResponse.getEntity();
                        //zmh
                        if (thirdhttpEntity == null) {

                        }
                        String thirdcontent = EntityUtils.toString(thirdhttpEntity);
                        JSONObject thirdjsonObject = JSON.parseObject(thirdcontent);
                        String thirdContent = thirdjsonObject.getJSONObject("data").get("content").toString();
                        String thirdName = thirdjsonObject.getJSONObject("data").get("name").toString().replaceAll("/", "-");
                        String thirdId = thirdjsonObject.getJSONObject("data").get("id").toString();
                        String thirdFileName = "三级--" + thirdName + thirdId + ".html";
                        //写入三级项目html
                        File thirdFile = new File(f.getAbsolutePath(), thirdFileName);
                        if (!thirdFile.exists()) {
                            thirdFile.createNewFile();
                        }
                        /**用来写入文件的便捷类*/
                        FileWriter thirdfileWriter = new FileWriter(thirdFile.getAbsoluteFile());
                        BufferedWriter thirdbw = new BufferedWriter(thirdfileWriter);
                        thirdbw.write(thirdContent);
                        thirdbw.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadFile.compress(directory.getAbsolutePath() + "/" + "project" + "/" + fileName, directory.getAbsolutePath() + "/" + "project" + "/" + fileName + ".zip");
        downloadFile.download(request, response, directory.getAbsolutePath() + "/" + "project" + "/" + fileName + ".zip", fileName);
    }
}
