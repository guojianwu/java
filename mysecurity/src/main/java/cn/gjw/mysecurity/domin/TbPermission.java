package cn.gjw.mysecurity.domin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbPermission {
    private Integer id;
    private Integer parent_id;
    private String name;
    private String enname;
    private String url;
    private String description;
    private Date created;
    private Date updated;
}
