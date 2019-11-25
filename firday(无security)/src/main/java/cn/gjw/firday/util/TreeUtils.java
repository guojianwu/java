package cn.gjw.firday.util;

import cn.gjw.firday.domain.SysPermission;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class TreeUtils {
    public static boolean isHas = false;

    public static boolean isHasChild(JSONArray array, Integer id) {
        List<SysPermission> sysPermissions = JSONObject.parseArray(array.toJSONString(), SysPermission.class);
        for (SysPermission sysPermission : sysPermissions) {
            if (sysPermission.getId() == id) {

                isHas = true;
                break;
            } else {
                if (sysPermission.getChild().size() > 0) {
                    JSONArray array1 = JSONArray.parseArray(JSON.toJSONString(sysPermission.getChild()));
                    isHasChild(array1, id);
                }

            }
        }
        return isHas;
    }

    /**
     * 菜单树
     *
     * @param parentId
     * @param permissionsAll
     * @param array
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array) {
        for (SysPermission per : permissionsAll) {
            if (per.getParentId().equals(parentId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }
}
