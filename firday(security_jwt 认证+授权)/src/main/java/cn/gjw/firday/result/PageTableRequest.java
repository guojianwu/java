package cn.gjw.firday.result;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 分页查询参数
 */
@Slf4j
@Data
@ToString
public class PageTableRequest implements Serializable {

    private static final long serialVersionUID = 7328071045193618467L;
    private Integer pageNum;
    private Integer pageSize;
    private Integer offset;
    private String name;

    public void countOffset() {
        if (null == this.pageNum || null == this.pageSize) {
            this.offset = 0;
            return;
        }
        this.offset = (this.pageNum - 1) * this.pageSize;
    }


}
