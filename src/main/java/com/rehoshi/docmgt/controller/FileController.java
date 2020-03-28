package com.rehoshi.docmgt.controller;



import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.Doc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


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
    public RespData<Boolean> load(MultipartFile file){
        return $(booleanRespData -> {
            try {
                if (file.isEmpty()){
                    RespData.succeed(false).msg("file is empty");
                }
                //获取文件名
                String fileName = file.getOriginalFilename();
                //存储路径
                String filePath = "src/main";
                String path = filePath + fileName;
                File newFile = new File(path);
                //判断是否存在根目录
                if(!newFile.getParentFile().exists()){
                    newFile.getParentFile().mkdirs();
                }
                //上传
                file.transferTo(newFile);
                RespData.succeed(true).msg("文件上传成功！");
            } catch (IOException e) {
                e.printStackTrace();
                RespData.succeed(false).msg("上传失败");
            }

        });
    }
    @RequestMapping("/analysis")
    public RespData<Doc> analysis(){
        return $(docRespData -> {

        });
    }
}
