package com.languagehelper.ui.home;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.common.utils.FileUtils;
import com.common.utils.UIUtils;
import com.languagehelper.R;
import com.languagehelper.base.MActivity;
import com.languagehelper.features.webview.WebActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends MActivity {
    String path = "http://cx.ikanshu.cn/pages/flindex.jsp?cnid=1272&umeng=FreeShu_qq&version=3.0.1&vercode=46&imei=354910071135497&imsi=460003481821802&uid=14804341&packname=com.mianfeia.book&oscode=22&model=SM-A8000&other=a&vcode=46&channelId=1272&mac=3549100711354977C0BC6CA73CC&platform=android&appname=cxb&brand=samsung";

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, path);
        startActivity(intent);
        new Thread(new Runnable() {
            @Override
            public void run() {

                InputStream inputStream = getResources().openRawResource(R.raw.xiaoshuo01);
                try {
                    File test =newFileInStream(inputStream,"test.txt");
                    readFileByLines(test.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 从steam中写到文件中
     *
     * @param stream
     * @param name
     * @return
     * @throws IOException
     */
    public static File newFileInStream(InputStream stream, String name) throws IOException {
        if (stream == null) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(),name);
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = stream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.close();
        return file;
    }
    @Override
    public View initActionBar() {
        return null;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
