package cn.gjw.firday.service;

import cn.gjw.firday.dao.PermissionDao;
import cn.gjw.firday.domain.SysPermission;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.util.TreeUtils;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PermissionService {
    @Autowired
    PermissionDao permissionDao;

    public Results findAll(){
        List<SysPermission> datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        TreeUtils.setPermissionsTree(0,datas,array);
        JSONArray root = new JSONArray();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","root");
        map.put("id",0);
        map.put("child",array);
        root.add(map);
        return Results.success(root);
    }


    public Results save(SysPermission sysPermission) {
        Results results= permissionDao.save(sysPermission)>0? Results.success("新增成功"):Results.failure(400,"新增失败");
        return results;
    }

    public Results findById(Integer id) {
        SysPermission permission = permissionDao.findById(id);
        return Results.success(permission);
    }

    public Results update(SysPermission sysPermission) {

        if(sysPermission.getId()==sysPermission.getParentId()){
            return Results.failure(400,"上级菜单不能为本身");
        }
        List<SysPermission> datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        TreeUtils.isHas=false;
        TreeUtils.setPermissionsTree(1,datas,array);
        boolean hasChild = TreeUtils.isHasChild(array, sysPermission.getParentId());
        if(hasChild){
            return Results.failure(400,"上级菜单不能为本身的子菜单");
        }
        return permissionDao.update(sysPermission)>0?Results.success("修改成功"):Results.failure(400,"修改失败");
    }

    public Results delate(Integer id) {
        permissionDao.deleteById(id);
        return permissionDao.deleteByParentId(id)>0?Results.success("删除成功"):Results.failure(400,"删除失败");
    }
}
