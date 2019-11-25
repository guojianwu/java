package cn.gjw.firday.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SysPermission extends BaseEntity<Integer> {

    private static final long serialVersionUID = -6525908145032868837L;
    @NotNull(message = "上级菜单名不能为空！")
    private Integer parentId;
    @NotBlank(message = "菜单名名不能为空！")
    private String name;
    private String css;
    private String href;
    private Integer type;
    private String permission;
    private Integer sort;

    private List<SysPermission> child;

//	@Override
//	public String toString() {
//		return "SysPermission{" +
//				"parentId=" + parentId +
//				", name='" + name + '\'' +
//				", css='" + css + '\'' +
//				", href='" + href + '\'' +
//				", type=" + type +
//				", permission='" + permission + '\'' +
//				", sort=" + sort +
//				'}';
//	}
}
