package cn.gjw.firday.exception;

import cn.gjw.firday.result.Results;
import lombok.Getter;

/**
 * @author Joetao
 * Created at 2018/8/24.
 */
@Getter
public class CustomException extends RuntimeException {
    private Results results;

    public CustomException(Results resultJson) {
        this.results = resultJson;
    }
}
