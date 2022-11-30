package com.yijyun.phantommask.pojo.vo;

import com.yijyun.phantommask.component.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageVo {
    Integer errorCode;
    String errorMessage;
    Object Data;

    public MessageVo(StatusEnum Status, Object data) {
        this.errorCode = Status.getCode();
        this.errorMessage = Status.getMessage();
        Data = data;
    }
}
