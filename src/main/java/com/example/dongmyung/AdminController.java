package com.example.dongmyung;

import com.example.dongmyung.user.SiteUser;
import com.example.dongmyung.user.UserSecurityService;
import com.example.dongmyung.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/preMemberList")
    public String memberAdmin(Model model){
        try{
            Collection<? extends GrantedAuthority> authorities
                    = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            // 관리자가 아닌데 접근할 수 있었다면 권한없음 에러페이지를 띄워준다.
            if(!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                return "basic_error";
            }
            List<SiteUser> preMembers = userService.findAllPreMembers();
            model.addAttribute("preMembers", preMembers);
        } catch(Exception e){
            e.printStackTrace();
            return "basic_error";
        }
        return "premember_list";
    }

    @GetMapping("/edit/{id}")
    public String changeMember(@PathVariable("id") Long id)
    {
        System.out.println("change member 실행");
        userService.changePreMemberToMember(id);
        return "premember_list";
    }

}
