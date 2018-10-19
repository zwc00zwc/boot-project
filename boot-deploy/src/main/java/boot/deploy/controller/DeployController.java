package boot.deploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @auther a-de
 * @date 2018/10/19 15:58
 */
@Controller
public class DeployController {
    @ResponseBody
    @RequestMapping(value = "/deploy")
    public String deploy(){
        String fileUrl = "";
        download(fileUrl);
        return "deploy";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(){
        return "";
    }

    private void download(String fileurl){
        try {
            //new一个URL对象
            URL url = new URL(fileurl);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while( (len=inStream.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = outStream.toByteArray();
            String savepath= "/usr/local/openlottery-project/";
            //后缀
            String suffix = "openlottery-web.war";
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File warfile = saveFile(savepath,suffix);
            //创建输出流
            FileOutputStream fileoutStream = new FileOutputStream(warfile);
            //写入数据
            fileoutStream.write(data);
            //关闭输出流
            fileoutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File saveFile(String path,String filename){
        File file = new File(path);
        //检查文件路径是否存在
        if(!file.exists()){file.mkdirs();}
        boolean outFileExists = true;
        File outFile = null;
        //确保文件名称唯一性
        do{
            String filePath = file.getPath()+filename;
            outFile = new File(filePath);
            outFileExists = outFile.exists();
        }while(outFileExists);
        return outFile;
    }
}
