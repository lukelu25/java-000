import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends  ClassLoader{

     static final String filePath = "E:\\JProject\\Test\\src\\main\\java\\Hello.xlass";

    public static void main(String[] args) {
        try {
            Class<?> targetClass = new MyClassLoader().findClass("Hello");
            Object obj = targetClass.newInstance();
            Method method = targetClass.getMethod("hello");
            method.invoke(obj);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(filePath);
        int length = (int)file.length();
        byte[] bytes = new byte[length];
        try (FileInputStream inputStream = new FileInputStream(file)){
            inputStream.read(bytes);
        } catch (IOException e) {
            System.out.println("read file failed, exception: " + e);
            return super.findClass(name);
        }

        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
