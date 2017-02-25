package lambdasinaction.chap1;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangyingjie1 on 2017/2/24.
 */
public class FileDemo {

    public static void main(String[] args) {

        //java8 之前获取目录下面所有隐藏文件列表的实现方式
        File[] hiddenFile = getHiddenFile();
        Arrays.stream(hiddenFile).forEach(file -> System.out.println(file.getName()));

        //use java8 refactor over code
        File[] files = new File(".").listFiles(File::isHidden);//方法引用
        Arrays.stream(hiddenFile).forEach(file -> System.out.println(file.getName()));

        List<Student> list = Stream.of(new Student()).collect(Collectors.toList());
        list.stream().forEach(s -> System.out.println(s));
    }

    private static File[] getHiddenFile() {
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();//核心方法
            }
        });
        return files;
    }

    public static class Student{
        public String print() {
            return "xxx";
        }
    }
}
