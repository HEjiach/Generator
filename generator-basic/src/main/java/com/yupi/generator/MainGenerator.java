package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static com.yupi.generator.DynamicGenerator.doGenerate;
import static com.yupi.generator.StaticGenerator.copyFileByRecursive;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        //1.静态文件生成
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:" + projectPath);
        String inputPath = projectPath + File.separator + "generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        //copyFilesByHutool(inputPath, outputPath);
        copyFileByRecursive(inputPath,outputPath);

       // 2.动态文件生成
        String dynamicinputPath = projectPath + File.separator +"generator-basic"+ File.separator+ "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicoutputPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        //3.数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(dynamicinputPath,dynamicoutputPath, mainTemplateConfig);
    }
}
