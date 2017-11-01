package com.rogge.command.service;


import com.rogge.command.model.CommandContent;
import com.rogge.command.model.vo.CommentVO;
import com.rogge.common.core.Service;

import java.util.List;

/**
* [Description]
* <p>
* [How to use]
* <p>
* [Tips]
*
* @author Created by Rogge on 2017/10/06
* @since 1.0.0
*/
public interface CommandContentService extends Service<CommandContent> {
    List<CommentVO> getCommandLeftJoin();
}
