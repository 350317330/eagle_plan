package com.example.demo.netty;

import lombok.Data;

/**
 * @author shisi
 * @date 2021/06/02 09:54
 **/
@Data
public abstract class Message {
    private int sequenceId;

    private int messageType;
}
