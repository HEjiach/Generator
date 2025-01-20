package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
静态文化生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:" + projectPath);
        String inputPath = projectPath + File.separator + "generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        //copyFilesByHutool(inputPath, outputPath);
        copyFileByRecursive(inputPath,outputPath);
    }

    /**
     * 拷贝文件(hutool工具包实现输入目录拷贝到输出目录)
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
    public static void copyFileByRecursive(String inputPath,String outputPath){
        File inputFile  = new File(inputPath);
        File outputFile  = new File(outputPath);
//        FileUtil.copy(inputPath, outputPath, false);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (IOException e) {
            System.err.println("文件复制失败!");
            e.printStackTrace();
        }
    }

    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            // 根据输出目录和输入文件名创建目标输出文件路径
            File destOutputFile = new File(outputFile, inputFile.getName());
            System.out.println("目标输出文件路径:" + destOutputFile);
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和目录
            File[] files = inputFile.listFiles();
            System.out.println("所有文件和目录:" + files);
            //无子文件直接返回
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files){
                //递归到下一层
                copyFileByRecursive(file,destOutputFile);
            }
        }else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
