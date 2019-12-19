package com.dmsdbj.itoo.netbug.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ASUS
 * @Class_NAME downloadFile
 * @Auther 张明慧
 * @Date 2019/3/12 14:36
 */
@Slf4j
public class downloadFile {


    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        //前端页面将自己需要的文件名字拿过来。这个名字直接拼接到文件所在服务器的相对路径。这里为便于测试。我直接把名字写死，以后使用的时候
        //根据实际业务进行修改。
        //String fileName = request.getParameter("file");
        System.out.println(fileName);
        try {
            //mac系统，所以路径是这样子的。win系统就是D盘什么什么的
            //String path = "D:/test/a.zip";
            String path = filePath;
            log.info("路径名称为:{},文件名称为:{}", path, fileName);
            //这里是下载以后的文件叫做什么名字。我这里是以时间来定义名字的。
            downCfg(fileName + ".zip", request, response);
            OutputStream out;
            FileInputStream inputStream = new FileInputStream(path);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downCfg(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 判断浏览器，进行不同的加密，这样下载的时候保存的文件名就不会乱码
        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            log.info("这个文件名为i:{}", fileName);
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "gbk");
            //fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
        }
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setCharacterEncoding("gbk");
    }

    /**
     * s
     * 压缩文件
     *
     * @param srcFilePath  压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath) {
        File src = new File(srcFilePath);
        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);
        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     *
     * @param src
     * @param zos
     * @param baseDir
     */
    private static void compressbyType(File src, ZipOutputStream zos, String baseDir) {
        if (!src.exists()) {
            return;
        }
        System.out.println("压缩路径" + baseDir + src.getName());
        //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
        if (src.isFile()) {
            //src是文件，调用此方法
            compressFile(src, zos, baseDir);
        } else if (src.isDirectory()) {
            //src是文件夹，调用此方法
            compressDir(src, zos, baseDir);
        }
    }

    /**
     * 压缩文件
     */
    private static void compressFile(File file, ZipOutputStream zos, String baseDir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zos.putNextEntry(entry);
            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                zos.write(buf, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 压缩文件夹
     */
    private static void compressDir(File dir, ZipOutputStream zos, String baseDir) {
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files.length == 0) {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName() + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }
}
