package com.example.ancient_gs;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileSaveQQ {
    public static boolean saveUserInfo(Context context,
                                       String account, String password) throws IOException {
        FileOutputStream fos = null;
        try {
            // : 补全保存代码
            // 创建一个文件输出流，用于将用户信息写入文件
            fos = context.openFileOutput("data.txt", Context.MODE_APPEND);


            // 将用户信息以字符串形式写入文件
            String userInfo = account + "|" + password+ "\n";
            fos.write(userInfo.getBytes());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, String> getUserInfo(Context context) {
//        String content = "";
        FileInputStream fis = null;

        HashMap<String, String> userMap = new HashMap<String, String>();//--
        try {
            // 打开文件输入流，读取用户信息
            fis = context.openFileInput("data.txt");

            // 读取文件内容并解析用户信息
            byte[] buffer = new byte[1024];
            int length;
            StringBuilder content = new StringBuilder();

            while ((length = fis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, length));
            }

            String userInfo = content.toString();
            if (!userInfo.isEmpty() && userInfo.contains("|")) {
                String[] infoArray = userInfo.split("\\|");
                if (infoArray.length == 2) {
                    userMap.put("account", infoArray[0]);
                    userMap.put("password", infoArray[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userMap;
    }
    public static boolean checkUserExists(Context context, String account) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                // 分割每一行，判断是否存在指定账号
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].equals(account)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
