package com.rogge.command.model;

import javax.persistence.*;

@Table(name = "command_content")
public class CommandContent {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 指令内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 指令主键
     */
    @Column(name = "COMMAND_ID")
    private Integer commandId;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取指令内容
     *
     * @return CONTENT - 指令内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置指令内容
     *
     * @param content 指令内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取指令主键
     *
     * @return COMMAND_ID - 指令主键
     */
    public Integer getCommandId() {
        return commandId;
    }

    /**
     * 设置指令主键
     *
     * @param commandId 指令主键
     */
    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }
}