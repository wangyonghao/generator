package org.joy.generator.engine;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * AbstractTemplateEngine
 *
 * @Author wyh
 * @Date 2017/2/27.
 */
public abstract class AbstractTemplateEngine implements TemplateEngine {
    protected String inputEncoding = DEFAULT_ENCODING;
    protected String outputEncoding = DEFAULT_ENCODING;

    protected String templatePath = "";


    @Override
    public void generate(Object model, String inputFile, String outputFile) throws TemplateEngineException {
        Writer out = null;
        try {
            outputFile = this.makeOutputDirectory(outputFile,model);
            System.out.println(outputFile.replaceAll("/","\\\\"));

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), this.outputEncoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //TODO
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.generate(model,inputFile,out);
    }

    @Override
    public abstract void generate(Object model, String inputFile, Writer out) throws TemplateEngineException;

    /**
     * 解析${}表达式
     * @param dataModel 值
     * @param expression 表达式字符串
     * @return
     * @throws TemplateEngineException
     */
    protected abstract String evaluate(Object dataModel, String expression) throws TemplateEngineException;

    public void setDefaultEncoding(String encoding) {
        this.inputEncoding = encoding;
        this.outputEncoding = encoding;
    }

    @Override
    public void setInputEncoding(String encoding) {
        this.inputEncoding = encoding;
    }

    @Override
    public void setOutputEncoding(String encoding) {
        this.outputEncoding = encoding;
    }

    /**
     * 生成输出目录
     * @param outputFile 输出文件(包含目录和文件名)
     * @param dataModel 解析${}表达式的参数
     */
    private String makeOutputDirectory(String outputFile,Object dataModel) throws TemplateEngineException {
        //解析路径中的${}表达式
        String file = this.evaluate(dataModel, outputFile);
        //将包路径中的"."转换为"\"
        String strBefore = StringUtils.substringBeforeLast(file, ".");
        String strAfter = StringUtils.substringAfterLast(file, ".");

        strBefore = StringUtils.replace(strBefore,".","/");

        //创建输出目录
        String filePath = strBefore + "." + strAfter;
        this.mkParentDirs(filePath);

        return filePath;
    }

    private void mkParentDirs(String file){
        File f = new File(file);
        f.getParentFile().mkdirs();
    }

    @Override
    public void setTemplatePath(String path) {
        this.templatePath = path;
    }
}
