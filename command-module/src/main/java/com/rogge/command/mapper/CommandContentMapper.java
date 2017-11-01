package com.rogge.command.mapper;

import com.rogge.command.model.CommandContent;
import com.rogge.command.model.vo.CommentVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommandContentMapper extends Mapper<CommandContent> {
    List<CommentVO> selectLeftJoinCommand();
}