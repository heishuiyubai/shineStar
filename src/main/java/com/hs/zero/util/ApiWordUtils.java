package com.hs.zero.util;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @author fq
 * api文档生成
 * 工具只是人用的东西，真正厉害的是人，而不是那种工具
 * 我不想和你争吵，主要目的是为了解决问题，而不是来争论谁的声音大，你怎么说我都行，但是不能说我的父母，
 * 他们不欠你的，你没资格去数落她们，你没资格，同样的，我也不会去议论你的家人，这是基本的尊重
 */
public class ApiWordUtils {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\fq_learn\\work\\zero-hs\\zero_hs"); // 项目根目录
        config.setProjectName("zero_hs"); // 项目名称
        config.setApiVersion("1.0");       // 声明该API的版本
        config.setDocsPath("C:\\Users\\heishui\\Desktop\\log"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
