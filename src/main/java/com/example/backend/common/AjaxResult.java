package com.example.backend.common;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回实体")
public class AjaxResult {

    private static final String ERROR = "error";
    private static final String WARNING = "warning";
    private static final String SUCCESS = "success";

    private static final AjaxResult result = new AjaxResult();
    private static final JSONObject object = new JSONObject();

    @ApiModelProperty(
            value = "是否成功", required = true,
            example = "error/warning/success"
    )
    private String type;

    @ApiModelProperty(
            value = "返回数据", required = true,
            example = "{token: '****'}"
    )
    private JSONObject data;

    @ApiModelProperty(
            value = "返回前端消息", required = true,
            example = "登录成功"
    )
    private String message;

    public static AjaxResult error(String message) {
        object.clear();
        result.setType(ERROR);
        result.setData(object);
        result.setMessage(message);
        return result;
    }

    public static AjaxResult warning(String message) {
        object.clear();
        result.setType(WARNING);
        result.setData(object);
        result.setMessage(message);
        return result;
    }

    public static AjaxResult success(String message) {
        object.clear();
        result.setType(SUCCESS);
        result.setData(object);
        result.setMessage(message);
        return result;
    }

    public AjaxResult put(String key, Object value) {
        result.getData().put(key, value);
        return result;
    }
}
