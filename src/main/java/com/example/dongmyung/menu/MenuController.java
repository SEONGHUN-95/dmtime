package com.example.dongmyung.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("/menu")
@RequiredArgsConstructor
@Controller
public class MenuController {
    private final MenuService menuService;
    private final MenuRepository menuRepository;

//    @GetMapping("/")
//    public String view(Model model) {
//        List<Menu> files = menuRepository.findAll();
//        model.addAttribute("all",files);
//        return "menu_list";
//    }

    @GetMapping("")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Menu> paging = this.menuService.getList(page);
        model.addAttribute("paging", paging);
        return "menu_list";
    }

    @GetMapping("/upload")
    public String UploadForm(){
        return "menu_form";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
        menuService.saveFile(file);
        for (MultipartFile multipartFile : files) {
            menuService.saveFile(multipartFile);
        }
        return "redirect:";
    }

    @GetMapping("/images/{fileId}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException{
        Menu file = menuRepository.findById(id).orElse(null);
        return new UrlResource("file:" + file.getSavedPath());
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {

        Menu file = menuRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + file.getSavedPath());

        String encodedFileName = UriUtils.encode(file.getOriginFileName(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
}
