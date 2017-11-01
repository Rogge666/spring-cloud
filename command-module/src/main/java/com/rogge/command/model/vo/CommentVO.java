package com.rogge.command.model.vo;


import com.rogge.command.model.CommandContent;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/10/6 0006.
 * @since 1.0.0
 */
public class CommentVO extends CommandContent {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
