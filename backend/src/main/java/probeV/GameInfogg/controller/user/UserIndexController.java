package probeV.GameInfogg.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserIndexController {

    @GetMapping("/user/task")
    public String getUserTask(){
        return "pages/tasklist/user_tasklist";
    }

    @GetMapping("/user/task/setting")
    public String getUserTaskSetting(){
        return "pages/tasklist/user_tasklist_setting";
    }

    @GetMapping("/user/item")
    public String getUserItem(){
        return "pages/itemlist/user_itemlist";
    }

    @GetMapping("/user/item/setting")
    public String getUserItemSetting(){
        return "pages/itemlist/user_itemlist_setting";
    }
}



