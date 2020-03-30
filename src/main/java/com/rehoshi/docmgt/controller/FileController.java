package com.rehoshi.docmgt.controller;


import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.FileWrapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;


@RestController
@RequestMapping("/file")
/**
 * 功能：文档上传和解析doc/docx文档
 * 方法:load()
 *      analysis()
 * @author：SQY
 * @dataTime:2020.03.28
 */
public class FileController extends HoshiController{
    /**
     * 文件上传
     * @param file
     * @return
     * @author：SQY
     * @dataTime:2020.03.28
     */
    @PostMapping("/load")
    public RespData<FileWrapper> load(@RequestParam("file") MultipartFile file){
        return $(resp -> {
            try {
                if (file.isEmpty()){
                    RespData.succeed(false).msg("file is empty");
                }
                //获取文件名
                String fileName = file.getOriginalFilename();
                fileName = URLDecoder.decode(fileName, "UTF-8") ;
                //存储路径
                String filePath = "D:\\docTemp\\";
                String path = filePath + fileName;
                File newFile = new File(path);
                //判断是否存在根目录
                if(!newFile.getParentFile().exists()){
                    newFile.getParentFile().mkdirs();
                }
                //上传
                file.transferTo(newFile);
                FileWrapper wrapper = new FileWrapper() ;
                wrapper.setContent(analysis(path));
                wrapper.setPathAtServer(path);
                resp.success(true).data(wrapper).msg("文件上传成功！");
            } catch (IOException e) {
                e.printStackTrace();
                resp.success(false).msg("上传失败");
            }

        });
    }
    public static String analysis(String path){
        String buff = null;
        InputStream is = null;
        File file = new File(path);
        try {

            if (path.endsWith(".doc")) {
                is = new FileInputStream(file);
                WordExtractor extractor = new WordExtractor(is);
                buff = extractor.getText();
                extractor.close();
                return buff ;
            } else if (path.endsWith(".docx")) {
                is = new FileInputStream(file);
                XWPFDocument Xdoc = new XWPFDocument(is);
                XWPFWordExtractor XWE = new XWPFWordExtractor(Xdoc);
                buff = XWE.getText();
                XWE.close();
                return buff ;
            }
        }catch (Exception e){
        }finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
        //不是doc 或者docx的文件 直接读取内容
        try {
            buff = FileUtils.readFileToString(file) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff ;
    }
}
