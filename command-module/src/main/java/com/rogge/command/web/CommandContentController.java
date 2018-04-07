package com.rogge.command.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rogge.command.model.CommandContent;
import com.rogge.command.model.vo.CommentVO;
import com.rogge.command.service.CommandContentService;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RestController
public class CommandContentController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(CommandContentController.class);

    @Resource
    private CommandContentService commandContentService;

    @PostMapping("/add")
    public ApiResponse add(CommandContent commandContent) {
        commandContentService.save(commandContent);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam Integer id) {
        commandContentService.deleteById(id);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/update")
    public ApiResponse update(CommandContent commandContent) {
        commandContentService.update(commandContent);
        return ApiResponse.creatSuccess();
    }

    @PostMapping("/detail")
    public ApiResponse detail(@RequestParam Integer id) {
        CommandContent commandContent = commandContentService.findById(id);
        return ApiResponse.creatSuccess(commandContent);
    }

    @PostMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<CommandContent> list = commandContentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.creatSuccess(pageInfo);
    }

    @GetMapping("/cc/{id}")
    public ApiResponse getCommandContentByOther(@PathVariable("id") int id) {
        CommandContent lCommandContent = commandContentService.findBy("commandId", id);
        return ApiResponse.creatSuccess(lCommandContent);
    }

    @Cacheable(value = "get_join_list")
    @GetMapping("/getLeftJoin")
    public ApiResponse getLeftJoin() {
        List<CommentVO> list = commandContentService.getCommandLeftJoin();
        logger.info("这句话只会在缓存失效的时候才会出现");
        return ApiResponse.creatSuccess(list);
    }
}
