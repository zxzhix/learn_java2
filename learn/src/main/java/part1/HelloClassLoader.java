package part1;

import java.io.*;
import java.lang.reflect.Method;


public class HelloClassLoader extends  ClassLoader{

    protected Class<?> findClass (String tName)
    {
        HelloClassLoader tclassLoader= new HelloClassLoader();
        String url ="D:\\learn_java\\Hello.xlass\\Hello.xlass";
        File f = new File(url);
        FileInputStream inputStream = null;
        byte[] classBytes = null;
        try {
            inputStream = new FileInputStream(f);
            byte b[]=new byte[inputStream.available()];


            inputStream.read(b);
            classBytes = tclassLoader.decode(b);

            inputStream.close();
            System.out.println(classBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  defineClass(tName,classBytes,0,classBytes.length);

    }

    public static void main(String[] args) {

         String className = "Hello";
         String methodName = "hello";

        // 创建类加载器
        ClassLoader ClassLoader = new HelloClassLoader();
        // 加载相应的类
        Class<?> clazz;
        try {
            clazz = ClassLoader.loadClass(className);

            // 创建对象
            Object instance = clazz.getDeclaredConstructor().newInstance();
            // 调用实例方法
            Method method = clazz.getMethod(methodName);
            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private  byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }

        return targetArray;
    }
}
