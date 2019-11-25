package cn.gjw.firday;

import cn.gjw.firday.dao.PermissionDao;
import cn.gjw.firday.domain.SysPermission;
import cn.gjw.firday.dto.RoleDto;
import cn.gjw.firday.result.PageTableRequest;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.service.RoleService;
import cn.gjw.firday.util.TreeUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class FirdayApplicationTests {
    @Autowired
    RoleService roleService;

    @Autowired
    PermissionDao permissionDao;

    private boolean isHas = false;

    @Test
    public void test2() {
        List<SysPermission> datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        TreeUtils.isHas = false;
        TreeUtils.setPermissionsTree(1, datas, array);
        System.out.println(array);

        System.out.println(TreeUtils.isHasChild(array, 3));
    }

//    public boolean isHasChild(JSONArray array,Integer id){
//        List<SysPermission> sysPermissions = JSONObject.parseArray(array.toJSONString(), SysPermission.class);
//        for (SysPermission sysPermission:sysPermissions) {
//            if(sysPermission.getId()==id){
//
//                isHas=true;
//                break;
//            }else {
//                if(sysPermission.getChild().size()>0){
//                    JSONArray array1 = JSONArray.parseArray(JSON.toJSONString(sysPermission.getChild()));
//                    System.out.println("=========================");
//                    System.out.println(array1);
//                    System.out.println("=========================");
//                    isHasChild(array1,id);
//                }
//
//            }
//        }
//        return  isHas;
//    }

    @Test
    void test() {
        List<Integer> ids = permissionDao.getIdsByparentId(0);
        boolean contains = ids.contains(6);
        System.out.println(ids);
        System.out.println(contains);
//        RoleDto roleById = roleService.getRoleById(12);
//        System.out.println(roleById);
    }

    @Test
    void contextLoads() {
        PageTableRequest request = new PageTableRequest();
        request.setName("a");
        request.setPageNum(1);
        request.setPageSize(10);
        request.countOffset();
        Results roleByPage = roleService.getRoleByPage(request);
        System.out.println(roleByPage);
    }

    @Test
    public void test22() {
//        Results<Object> failure = Results.failure();
//        String s = JSONArray.toJSON(failure).toString();
//        System.out.println(s);
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);

    }

}
