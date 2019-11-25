package cn.gjw.mybatisplus2.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@TableName("user3")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField(select = false)
    private Integer age;
    private String email;
}