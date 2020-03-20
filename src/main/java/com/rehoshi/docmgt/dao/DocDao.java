package com.rehoshi.docmgt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rehoshi.docmgt.domain.entities.Doc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocDao extends BaseMapper<Doc> {
}
