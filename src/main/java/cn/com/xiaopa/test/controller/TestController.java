package cn.com.xiaopa.test.controller;

import cn.com.xiaopa.common.api.R;
import cn.com.xiaopa.common.unit.OkHttpUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/one")
    public R one(){
        return R.data(LocalDate.now().toString());
    }

    @GetMapping("/everyDay")
    public R everyDay(){
        String url="http://api.tianapi.com/zaoan/index?key=cc886f5420ae701d30a9fa6bffcec7e5";
        String result = OkHttpUtils.builder().url(url).get().sync();
        Gson gson=new Gson();
        Map map = gson.fromJson(result, Map.class);
        List list = (List) map.get("newslist");
        Map map2 = (Map) list.get(0);
        String content = map2.get("content").toString();
        return R.data(content);
    }
}
